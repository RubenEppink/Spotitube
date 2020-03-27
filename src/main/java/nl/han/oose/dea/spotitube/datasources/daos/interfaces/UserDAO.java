package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;

public interface UserDAO {
     UserDTO getUserInfo(String userLogin);
     void makeConnection();

}
