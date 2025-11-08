package neo.bank.bonifico.framework.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.BonificoUseCase;
import neo.bank.bonifico.application.ports.input.commands.RecuperaBonificoDaIdCmd;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.vo.IdOperazione;
import neo.bank.bonifico.framework.input.rest.response.BonificoInfoResponse;

@Path("/bonifici")
@ApplicationScoped
@Slf4j
public class BonificoResource {

    @Inject
    private BonificoUseCase app;

    @Path("/{idOperazione}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaBonificoDaIban(@PathParam(value = "idOperazione") String idOperazione) {

        Bonifico contoCorrente = app.recuperaBonificoDaId(new RecuperaBonificoDaIdCmd(new IdOperazione(idOperazione)));
        BonificoInfoResponse bodyResponse = new BonificoInfoResponse(contoCorrente);
        return Response.ok(bodyResponse).build();
    }
}
