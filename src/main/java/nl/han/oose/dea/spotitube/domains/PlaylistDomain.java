package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;

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

    public PlaylistsDTO create(String token, PlaylistDTO playlistDTO) {
        playlistDAO.create(token, playlistDTO);
        return getAll(token);
    }
}


