package nl.han.oose.dea.spotitube.controllers.exceptionmappers;

import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCredentialsExceptionMapper implements ExceptionMapper<InvalidCredentialsException> {

    @Override
    public Response toResponse(InvalidCredentialsException e) {
        return Response.status(401).build();
    }
}
