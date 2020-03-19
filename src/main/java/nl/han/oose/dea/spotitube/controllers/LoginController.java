package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.domains.interfaces.LoginDomain;
import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
   LoginDomain loginDomain;

   @Inject
    public void setLoginDomain(LoginDomain loginDomain) {
        this.loginDomain = loginDomain;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        return Response.status(201).entity(loginDomain.validateCredentials(loginDTO)).build();
    }
}