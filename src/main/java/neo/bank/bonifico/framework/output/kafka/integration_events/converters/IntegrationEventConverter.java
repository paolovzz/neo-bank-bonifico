package neo.bank.bonifico.framework.output.kafka.integration_events.converters;

import neo.bank.bonifico.domain.model.events.EventPayload;

public interface IntegrationEventConverter<DE extends EventPayload, IE> {
    IE convert(DE domainEvent);
}