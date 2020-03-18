package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;

public interface LoginDAO {
    public LoginDTO read(String userLogin);
}
