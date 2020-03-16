package nl.han.oose.dea.spotitube.datasource.dao.interfaces;

import nl.han.oose.dea.spotitube.controller.dto.UserDTO;

public interface UserDAO {
    public UserDTO read(String userLogin);
}
