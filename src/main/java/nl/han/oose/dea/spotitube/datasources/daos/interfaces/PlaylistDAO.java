package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

public interface PlaylistDAO {
     PlaylistsDTO getAll(String token);
     void delete(int playlistId, String token);
     void create(String token, PlaylistDTO playlistDTO);
     void update(String token, int playlistId, PlaylistDTO playlistDTO);
     void makeConnection();
}
