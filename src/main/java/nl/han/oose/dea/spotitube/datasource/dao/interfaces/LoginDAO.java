package nl.han.oose.dea.spotitube.datasource.dao.interfaces;

import nl.han.oose.dea.spotitube.controller.dto.LoginDTO;

public interface LoginDAO {
    public LoginDTO read(String userLogin);
}
