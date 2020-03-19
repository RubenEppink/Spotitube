package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;
import nl.han.oose.dea.spotitube.domains.interfaces.TrackDomain;

import javax.inject.Inject;

public class TrackDomainImpl implements TrackDomain {
   private TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksDTO getAllTracksInPlaylist(String token, int playlistId) {
        return trackDAO.getAllInPlaylist(token, playlistId);
    }

    @Override
    public TracksDTO getAllTracksNotInPlaylist(String token, int playlistId) {
        return trackDAO.getAllNotInPlaylist(token, playlistId);
    }

    @Override
    public TracksDTO deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        trackDAO.delete(token, playlistId, trackId);
        return getAllTracksInPlaylist(token, playlistId);
    }

    @Override
    public TracksDTO addTrackToPlaylist(String token, int playlistId, TrackDTO trackDTO) {
        TrackDTO trackDTOInDB = trackDAO.get(trackDTO.getId());

        if (trackDTOInDB != null && trackDTOInDB.isOfflineAvailable() == trackDTO.isOfflineAvailable()) {
            trackDAO.addToPlaylist(token, playlistId, trackDTO);
        } else if (trackDTOInDB != null) {
            trackDAO.update(token, playlistId, trackDTO);
            trackDAO.addToPlaylist(token, playlistId, trackDTO);
        }

        return getAllTracksInPlaylist(token, playlistId);
    }
}
