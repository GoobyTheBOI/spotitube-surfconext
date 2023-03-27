package dea.spotitube.presentatie.exceptions.mappers;

import dea.spotitube.CCC.exceptions.InternalServerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InternalServerExceptionMapper implements ExceptionMapper<InternalServerException> {
    @Override
    public Response toResponse(InternalServerException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
