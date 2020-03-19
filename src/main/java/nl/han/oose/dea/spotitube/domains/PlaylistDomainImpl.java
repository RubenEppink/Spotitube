package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;
import nl.han.oose.dea.spotitube.domains.interfaces.PlaylistDomain;

import javax.inject.Inject;

public class PlaylistDomainImpl implements PlaylistDomain {
    private PlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }


    @Override
    public PlaylistsDTO getAll(String token) {
        return playlistDAO.getAll(token);
    }

    @Override
    public PlaylistsDTO delete(int playlistId, String token) {
        playlistDAO.delete(playlistId, token);
        return playlistDAO.getAll(token);
    }

    @Override
    public PlaylistsDTO create(String token, PlaylistDTO playlistDTO) {
        playlistDAO.create(token, playlistDTO);
        return getAll(token);
    }

    @Override
    public PlaylistsDTO update(String token, int playlistId, PlaylistDTO playlistDTO) {
        playlistDAO.update(token, playlistId, playlistDTO);
        return getAll(token);
    }
}


