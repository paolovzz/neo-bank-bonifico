package neo.bank.bonifico.application.ports.output;

import neo.bank.bonifico.domain.model.enums.TipologiaNotifica;
import neo.bank.bonifico.domain.model.vo.Iban;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

public interface NotificaService {
    
    public void inviaEmail(Iban iban, double importo, IdOperazione idOperazione, TipologiaNotifica tipologiaNotifica);
}
