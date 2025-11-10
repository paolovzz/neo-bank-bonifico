package neo.bank.bonifico.framework.adapter.output.mongodb.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.ports.output.BonificoRepositoryPort;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.events.EventPayload;
import neo.bank.bonifico.domain.model.vo.IdOperazione;
import neo.bank.bonifico.framework.adapter.output.mongodb.entities.EventStoreEntity;

@ApplicationScoped
@Slf4j
public class BonificoRepositoryImpl implements PanacheMongoRepository<EventStoreEntity>, BonificoRepositoryPort {

    @Inject
    private ObjectMapper mapper;

    @Override
    public void save(IdOperazione idOperazione, List<EventPayload> events) {
        long nextSequence = getNextSequence(idOperazione.getId());
        try {
            for (EventPayload ev : events) {
                EventStoreEntity entity;
                entity = new EventStoreEntity(idOperazione.getId(), ev.eventType(), mapper.writeValueAsString(ev),
                        nextSequence);
                entity.persist();
                nextSequence += 1;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bonifico findById(IdOperazione idOperazione) {
        
        List<EventPayload> eventPayloads = EventStoreEntity.find("aggregateId = ?1", idOperazione.getId())
                .stream()
                .sorted(Comparator.comparingLong(e -> ((EventStoreEntity) e).getSequence()))
                .map(e -> deserializeEvent((EventStoreEntity) e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        
        if(eventPayloads == null || eventPayloads.isEmpty()) {
            return null;
        }
        Bonifico cc = new Bonifico();
        for (EventPayload ev : eventPayloads) {
            cc.apply(ev);
        }
        return cc;
    }

    private EventPayload deserializeEvent(EventStoreEntity e) {
        try {
            Class<?> clazz = Class.forName(EventPayload.class.getPackageName().concat(".").concat( e.getEventType()));
            return (EventPayload) mapper.readValue(e.getPayload(), clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private long getNextSequence(String aggregateId) {
        EventStoreEntity last = EventStoreEntity.find("aggregateId = ?1", aggregateId)
                .stream()
                .map(e -> (EventStoreEntity) e)
                .max(Comparator.comparingLong(se -> se.getSequence()))
                .orElse(null);
        return last != null ? last.getSequence() + 1 : 1;
    }
}
