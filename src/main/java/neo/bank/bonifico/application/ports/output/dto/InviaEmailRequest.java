package neo.bank.bonifico.application.ports.output.dto;

import lombok.Value;
import neo.bank.bonifico.domain.model.enums.TipologiaNotifica;

@Value
public class InviaEmailRequest {
    private String idOperazione;
    private TipologiaNotifica tipoNotifica;
    private String ibanDestinatario;
    private double importo;

}
