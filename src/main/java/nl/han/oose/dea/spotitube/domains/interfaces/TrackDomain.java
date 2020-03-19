package nl.han.oose.dea.spotitube.domains.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;

import javax.inject.Inject;

public interface TrackDomain {

    TracksDTO getAllTracksInPlaylist(String token, int playlistId);

    TracksDTO getAllTracksNotInPlaylist(String token, int playlistId);

    TracksDTO deleteTrackFromPlaylist(String token, int playlistId, int trackId);

    TracksDTO addTrackToPlaylist(String token, int playlistId, TrackDTO trackDTO);
}
