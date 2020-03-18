package nl.han.oose.dea.spotitube.datasources.dao.interfaces;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;

public interface PlaylistDAO {
    public PlaylistsDTO getAll(String token);
    public void delete(int id, String token);
}
