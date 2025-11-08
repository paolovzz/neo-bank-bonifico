package neo.bank.bonifico.application.ports.output;

import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

public interface BonificoOutputPort {
    
    public void salva(Bonifico bonifico);
    public Bonifico recuperaDaId(IdOperazione idOperazione);
}
