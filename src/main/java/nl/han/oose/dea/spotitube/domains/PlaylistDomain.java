package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;

import javax.inject.Inject;

public class PlaylistDomain {
    PlaylistDAO playlistDAO;
    TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public TracksDTO getAllTracksInPlaylist(String token, int id) {
        return trackDAO.getAllInPlaylist(token, id);
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


    public PlaylistsDTO update(String token, int id, PlaylistDTO playlistDTO) {
        playlistDAO.update(token, id, playlistDTO);
        return getAll(token);
    }
}


