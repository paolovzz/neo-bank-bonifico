package neo.bank.bonifico.application.ports.output;

import java.util.List;

import neo.bank.bonifico.domain.model.events.EventPayload;

public interface EventsPublisherPort {
    void publish(String aggregateName, String aggregateId, List<EventPayload> events);
}
