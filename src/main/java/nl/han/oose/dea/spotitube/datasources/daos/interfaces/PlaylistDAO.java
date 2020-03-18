package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

public interface PlaylistDAO {
    public PlaylistsDTO getAll(String token);
    public void delete(int id, String token);
    public void create(String token, PlaylistDTO playlistDTO);
    public void update(String token, int id, PlaylistDTO playlistDTO);
}
