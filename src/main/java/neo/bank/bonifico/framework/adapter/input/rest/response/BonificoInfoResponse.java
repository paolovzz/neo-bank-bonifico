package neo.bank.bonifico.framework.adapter.input.rest.response;

import lombok.Getter;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;

@Getter
public class BonificoInfoResponse {
   
    private String idOperazione;
    private String ibanMittente;
    private String ibanDestinatario;
    private double importo;
    private String causale;

    public BonificoInfoResponse(Bonifico bonifico) {
        this.ibanMittente = bonifico.getIbanMittente().getCodice();
        this.ibanDestinatario = bonifico.getIbanDestinatario().getCodice();
        this.importo = bonifico.getImporto();
        this.idOperazione = bonifico.getIdOperazione().getId();
        this.causale = bonifico.getCausale().getCausale();
    }
}
