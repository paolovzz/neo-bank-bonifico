package neo.bank.bonifico.framework.input.rest.mappers;

    
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.bonifico.application.exceptions.BonificoNonTrovatoException;
import neo.bank.bonifico.framework.input.rest.response.ErrorResponse;

@Provider
public class BonificoNonTrovatoExceptionMapper implements ExceptionMapper<BonificoNonTrovatoException> {

    @Override
    public Response toResponse(BonificoNonTrovatoException exception) {
        ErrorResponse errore = new ErrorResponse(exception.getMessage());
        return Response.status(404).entity(errore).build();
    }

}

