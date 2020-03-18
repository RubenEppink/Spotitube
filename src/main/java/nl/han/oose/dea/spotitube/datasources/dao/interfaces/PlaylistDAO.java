package nl.han.oose.dea.spotitube.datasources.dao.interfaces;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;

public interface PlaylistDAO {
    public PlaylistsDTO getAll();
    public PlaylistsDTO delete(int id);
}
