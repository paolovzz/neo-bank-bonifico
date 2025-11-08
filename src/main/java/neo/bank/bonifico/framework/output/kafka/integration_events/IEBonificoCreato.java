package neo.bank.bonifico.framework.output.kafka.integration_events;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Value;

@Value
public class IEBonificoCreato implements Serializable {
    private String ibanMittente;
    private String ibanDestinatario;
    private double importo;
    private String causale;
    private String idOperazione;
    private LocalDateTime dataOperazione;
}
