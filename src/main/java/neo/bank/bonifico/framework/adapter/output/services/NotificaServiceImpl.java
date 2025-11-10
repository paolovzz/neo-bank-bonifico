package neo.bank.bonifico.framework.adapter.output.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.ports.output.NotificaService;
import neo.bank.bonifico.application.ports.output.dto.InviaEmailRequest;
import neo.bank.bonifico.domain.model.enums.TipologiaNotifica;
import neo.bank.bonifico.domain.model.vo.Iban;
import neo.bank.bonifico.domain.model.vo.IdOperazione;
import neo.bank.bonifico.framework.adapter.output.rest.NotificaClient;

@Slf4j
@ApplicationScoped
public class NotificaServiceImpl implements NotificaService{

    @RestClient
    private final NotificaClient client;

    @Inject
    public NotificaServiceImpl(@RestClient NotificaClient client) {
        this.client = client;
    }

    @Override
    public void inviaEmail(Iban iban, double importo, IdOperazione idOperazione, TipologiaNotifica tipologiaNotifica) {
        log.info(("Richiesta invio email..."));
        client.inviaEmail(new InviaEmailRequest(iban.getCodice(), idOperazione.getId(), importo, tipologiaNotifica));
    }
    
}
