package nl.han.oose.dea.spotitube.domains.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.UserDAO;

import javax.inject.Inject;

public interface LoginDomain {
      UserDTO validateCredentials(LoginDTO loginDTO);
}
