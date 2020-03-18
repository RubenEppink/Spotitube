package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;

public interface UserDAO {
    public UserDTO read(String userLogin);
}
