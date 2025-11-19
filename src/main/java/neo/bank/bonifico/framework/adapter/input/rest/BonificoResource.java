package neo.bank.bonifico.framework.adapter.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.BonificoUseCase;
import neo.bank.bonifico.application.ports.input.commands.RecuperaBonificoDaIdCmd;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.vo.IdOperazione;
import neo.bank.bonifico.framework.adapter.input.rest.api.BonificoApi;
import neo.bank.bonifico.framework.adapter.input.rest.model.BonificoInfoResponse;

@ApplicationScoped
@Slf4j
public class BonificoResource implements BonificoApi{

    @Inject
    private BonificoUseCase app;

    @Override
    public Response recuperaBonificoDaId(String idOperazione) {
        Bonifico bonifico = app.recuperaBonificoDaId(new RecuperaBonificoDaIdCmd(new IdOperazione(idOperazione)));
        BonificoInfoResponse bodyResponse = BonificoInfoResponse.builder()
        .causale(bonifico.getCausale().getCausale())
        .ibanDestinatario(bonifico.getIbanDestinatario().getCodice())
        .ibanMittente(bonifico.getIbanMittente().getCodice())
        .idOperazione(bonifico.getIdOperazione().getId())
        .importo(bonifico.getImporto()).build();
        return Response.ok(bodyResponse).build();
    }
}
