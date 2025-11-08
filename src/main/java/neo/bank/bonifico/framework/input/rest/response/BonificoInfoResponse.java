package neo.bank.bonifico.framework.input.rest.response;

import lombok.Getter;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;

@Getter
public class BonificoInfoResponse {
   
    private String ibanMittente;
    private String ibanDestinatario;

    public BonificoInfoResponse(Bonifico bonifico) {
        this.ibanMittente = bonifico.getIbanMittente().getCodice();
        this.ibanDestinatario = bonifico.getIbanDestinatario().getCodice();
    }
}
