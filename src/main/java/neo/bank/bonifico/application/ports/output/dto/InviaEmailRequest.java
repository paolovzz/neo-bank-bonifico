package neo.bank.bonifico.application.ports.output.dto;

import lombok.Value;
import neo.bank.bonifico.domain.model.enums.TipologiaNotifica;

@Value
public class InviaEmailRequest {
    
    private String ibanDestinatario;
    private String idOperazione;
    private double importo;
    private TipologiaNotifica tipoNotifica;
}
