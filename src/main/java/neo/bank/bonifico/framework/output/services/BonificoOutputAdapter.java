package neo.bank.bonifico.framework.output.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import neo.bank.bonifico.application.exceptions.BonificoNonTrovatoException;
import neo.bank.bonifico.application.ports.output.BonificoOutputPort;
import neo.bank.bonifico.application.ports.output.BonificoRepositoryPort;
import neo.bank.bonifico.application.ports.output.EventsPublisherPort;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.events.EventPayload;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BonificoOutputAdapter  implements BonificoOutputPort{

    private final BonificoRepositoryPort bonificoRepo;
    private final EventsPublisherPort publisherPort;

    @Override
    public void salva(Bonifico bonifico) {

        List<EventPayload> events = bonifico.popChanges();
        bonificoRepo.save(bonifico.getIdOperazione(), events);
        publisherPort.publish(Bonifico.AGGREGATE_NAME, bonifico.getIdOperazione().getId(), events);
    }

    @Override
    public Bonifico recuperaDaId(IdOperazione idBonifico) {
        Bonifico bonifico = bonificoRepo.findById(idBonifico);
        if(bonifico == null) {
            throw new BonificoNonTrovatoException(idBonifico.getId());
        }
       return bonifico;
    }
    
}
