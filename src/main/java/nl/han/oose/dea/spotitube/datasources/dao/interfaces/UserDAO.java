package nl.han.oose.dea.spotitube.datasources.dao.interfaces;

import nl.han.oose.dea.spotitube.controllers.dto.UserDTO;

public interface UserDAO {
    public UserDTO read(String userLogin);
}
