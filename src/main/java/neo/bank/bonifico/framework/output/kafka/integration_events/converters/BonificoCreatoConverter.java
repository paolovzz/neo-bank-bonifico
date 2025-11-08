package neo.bank.bonifico.framework.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.bonifico.domain.model.events.BonificoCreato;
import neo.bank.bonifico.framework.output.kafka.integration_events.IEBonificoCreato;

@ApplicationScoped
public class BonificoCreatoConverter implements IntegrationEventConverter<BonificoCreato, IEBonificoCreato>, IntegrationEventConverterMarker{

    @Override
    public IEBonificoCreato convert(BonificoCreato ev) {
        return new IEBonificoCreato(ev.getIbanMittente().getCodice(), ev.getIbanDestinatario().getCodice(), ev.getImporto(), ev.getCausale().getCausale(), ev.getIdOperazione().getId(), ev.getDataOperazione().getDataOra());
    }

    @Override
    public Class<BonificoCreato> supportedDomainEvent() {
        return BonificoCreato.class;
    }
    
}
