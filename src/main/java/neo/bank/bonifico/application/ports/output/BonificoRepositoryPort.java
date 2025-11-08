package neo.bank.bonifico.application.ports.output;

import java.util.List;

import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.events.EventPayload;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

public interface BonificoRepositoryPort {
    
    public void save(IdOperazione idOperazione, List<EventPayload> events);
    public Bonifico findById (IdOperazione idOperazione);
}
