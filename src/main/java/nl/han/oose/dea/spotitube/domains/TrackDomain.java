package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;

import javax.inject.Inject;

public class TrackDomain {
    TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public TracksDTO getAllTracksInPlaylist(String token, int playlistId) {
        return trackDAO.getAllInPlaylist(token, playlistId);
    }

    public TracksDTO getAllTracksNotInPlaylist(String token, int playlistId) {
        return trackDAO.getAllNotInPlaylist(token, playlistId);
    }

    public TracksDTO deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        trackDAO.delete(token, playlistId, trackId);
        return getAllTracksInPlaylist(token, playlistId);
    }
}
