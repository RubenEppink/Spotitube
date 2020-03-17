package nl.han.oose.dea.spotitube.datasources.dao.interfaces;

import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;

public interface LoginDAO {
    public LoginDTO read(String userLogin);
}
