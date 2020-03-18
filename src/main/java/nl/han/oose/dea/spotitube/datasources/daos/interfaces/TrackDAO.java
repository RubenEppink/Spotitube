package nl.han.oose.dea.spotitube.datasources.daos.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;

public interface TrackDAO {
    public TracksDTO getAllNotInPlaylist(String token, int playlistId);
    public TracksDTO getAllInPlaylist(String token, int id);
    public void delete(String token, int playlistId, int trackId);
    public void create(String token, TrackDTO trackDTO);
    public void update(String token, int id, TrackDTO trackDTO);
}
