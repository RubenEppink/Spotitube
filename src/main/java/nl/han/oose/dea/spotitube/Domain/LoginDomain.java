package nl.han.oose.dea.spotitube.Domain;

import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dto.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.UserDAO;

import javax.inject.Inject;

public class LoginDomain {
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

    public UserDTO validateCredentials(LoginDTO loginDTO) {
        if (loginDTO.getPassword().equals(loginDAO.read(loginDTO.getUser()).getPassword())) {
            return userDAO.read(loginDTO.getUser());
        } else {
            throw new InvalidCredentialsException();
        }
    }

}
