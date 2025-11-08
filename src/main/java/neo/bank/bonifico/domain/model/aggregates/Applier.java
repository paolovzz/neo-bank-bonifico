package neo.bank.bonifico.domain.model.aggregates;

import neo.bank.bonifico.domain.model.events.EventPayload;

public interface Applier {
    void apply(EventPayload event);
}