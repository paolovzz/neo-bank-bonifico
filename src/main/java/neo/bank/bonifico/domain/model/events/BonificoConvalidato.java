package neo.bank.bonifico.domain.model.events;

import lombok.Value;

@Value
public class BonificoConvalidato implements EventPayload {

    @Override
    public String eventType() {
        return "BonificoConvalidato";
    }
}
