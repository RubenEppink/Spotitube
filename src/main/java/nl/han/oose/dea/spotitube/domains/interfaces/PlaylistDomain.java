package nl.han.oose.dea.spotitube.domains.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

public interface PlaylistDomain {
    PlaylistsDTO getAllPlaylists(String token);

    PlaylistsDTO deletePlaylist(int playlistId, String token);

    PlaylistsDTO addPlaylist(String token, PlaylistDTO playlistDTO);

    PlaylistsDTO editPlaylistName(String token, int playlistId, PlaylistDTO playlistDTO);
}
