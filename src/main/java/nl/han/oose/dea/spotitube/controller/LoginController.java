package nl.han.oose.dea.spotitube.controller;

import nl.han.oose.dea.spotitube.controller.dto.LoginDTO;
import nl.han.oose.dea.spotitube.controller.exception.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.datasource.dao.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasource.dao.interfaces.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    LoginDAO loginDAO;
    UserDAO userDAO;

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        if (loginDTO.getPassword().equals(loginDAO.read(loginDTO.getUser()).getPassword())) {
            return Response.status(201).entity(userDAO.read(loginDTO.getUser())).build();
        } else {
            throw new InvalidCredentialsException();
        }
    }
}