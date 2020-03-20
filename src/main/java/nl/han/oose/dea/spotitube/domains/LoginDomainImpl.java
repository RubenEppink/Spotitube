package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.UserDAO;
import nl.han.oose.dea.spotitube.domains.interfaces.LoginDomain;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

public class LoginDomainImpl implements LoginDomain {
    private LoginDAO loginDAO;
    private UserDAO userDAO;

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public UserDTO validateCredentials(LoginDTO loginDTO) {
        if (DigestUtils.sha256Hex(loginDTO.getPassword()).equals(loginDAO.read(loginDTO.getUser()).getPassword())) {
            return userDAO.read(loginDTO.getUser());
        } else {
            throw new NotAuthorizedException("Either the username and/or password wasn't correct!");
        }
    }

}
