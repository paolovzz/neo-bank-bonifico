package neo.bank.bonifico.framework.adapter.output.rest;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import neo.bank.bonifico.application.ports.output.dto.InviaEmailRequest;

@Path("/notifiche")
@RegisterRestClient(configKey = "notifica-client")
public interface NotificaClient {

    @Path("/bonifico/email")
    @POST
    public Response inviaEmail(InviaEmailRequest request);

}
