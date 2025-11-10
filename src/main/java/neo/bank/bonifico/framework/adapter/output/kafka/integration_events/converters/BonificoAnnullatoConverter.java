package neo.bank.bonifico.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.bonifico.domain.model.events.BonificoAnnullato;
import neo.bank.bonifico.framework.adapter.output.kafka.integration_events.IEBonificoAnnullato;

@ApplicationScoped
public class BonificoAnnullatoConverter implements IntegrationEventConverter<BonificoAnnullato, IEBonificoAnnullato>, IntegrationEventConverterMarker {

    @Override
    public IEBonificoAnnullato convert(BonificoAnnullato domainEvent) {
        return new IEBonificoAnnullato();
    }

    @Override
    public Class<BonificoAnnullato> supportedDomainEvent() {
        return BonificoAnnullato.class;
    }

}
