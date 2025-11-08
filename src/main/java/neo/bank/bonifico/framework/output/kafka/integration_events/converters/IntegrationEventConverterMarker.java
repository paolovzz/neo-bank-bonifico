package neo.bank.bonifico.framework.output.kafka.integration_events.converters;

import neo.bank.bonifico.domain.model.events.EventPayload;

public interface IntegrationEventConverterMarker {
    Class<? extends EventPayload> supportedDomainEvent();
}
