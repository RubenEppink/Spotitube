package nl.han.oose.dea.spotitube.Domain;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.PlaylistDAO;

import javax.inject.Inject;

public class PlaylistDomain {

    PlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public PlaylistsDTO getAll() {
        return playlistDAO.getAll();
    }

    public PlaylistsDTO delete(int id) {
        playlistDAO.delete(id);
        return playlistDAO.getAll();
    }
}


