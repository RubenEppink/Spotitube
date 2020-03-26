package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;

public interface TrackDAO {
     TrackDTO get(int trackId);
     TracksDTO getAllNotInPlaylist(String token, int playlistId);
     TracksDTO getAllInPlaylist(String token, int playlistId);
     void delete(String token, int playlistId, int trackId);
     void addToPlaylist(String token, int playlistId, TrackDTO trackDTO);
     void update(String token, int playlistId, TrackDTO trackDTO);
     void makeConnection();

}
