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

    public PlaylistsDTO getAll(String token) {
        return playlistDAO.getAll(token);
    }

    public PlaylistsDTO delete(int id, String token) {
        playlistDAO.delete(id, token);
        return playlistDAO.getAll(token);
    }
}


