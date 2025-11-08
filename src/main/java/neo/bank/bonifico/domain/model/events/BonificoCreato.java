package neo.bank.bonifico.domain.model.events;

import lombok.Value;
import neo.bank.bonifico.domain.model.vo.Causale;
import neo.bank.bonifico.domain.model.vo.DataOperazione;
import neo.bank.bonifico.domain.model.vo.Iban;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

@Value
public class BonificoCreato implements EventPayload {

    private IdOperazione idOperazione;
    private DataOperazione dataOperazione;
    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private Causale causale;
    private double importo;

    @Override
    public String eventType() {
        return "BonificoCreato";
    }
}
