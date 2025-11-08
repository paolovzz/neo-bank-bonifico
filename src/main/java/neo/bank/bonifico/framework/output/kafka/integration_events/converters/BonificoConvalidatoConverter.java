package neo.bank.bonifico.framework.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.bonifico.domain.model.events.BonificoConvalidato;
import neo.bank.bonifico.framework.output.kafka.integration_events.IEBonificoConvalidato;

@ApplicationScoped
public class BonificoConvalidatoConverter implements IntegrationEventConverter<BonificoConvalidato, IEBonificoConvalidato>, IntegrationEventConverterMarker {

    @Override
    public IEBonificoConvalidato convert(BonificoConvalidato domainEvent) {
        return new IEBonificoConvalidato();
    }

    @Override
    public Class<BonificoConvalidato> supportedDomainEvent() {
        return BonificoConvalidato.class;
    }

}
