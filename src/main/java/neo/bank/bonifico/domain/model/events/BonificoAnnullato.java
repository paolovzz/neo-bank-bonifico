package neo.bank.bonifico.domain.model.events;

import lombok.Value;

@Value
public class BonificoAnnullato implements EventPayload {

    @Override
    public String eventType() {
        return "BonificoAnnullato";
    }
}
