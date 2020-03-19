package nl.han.oose.dea.spotitube.domains.interfaces;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

public interface PlaylistDomain {
    PlaylistsDTO getAll(String token);

    PlaylistsDTO delete(int playlistId, String token);

    PlaylistsDTO create(String token, PlaylistDTO playlistDTO);

    PlaylistsDTO update(String token, int playlistId, PlaylistDTO playlistDTO);
}
