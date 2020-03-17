package nl.han.oose.dea.spotitube.controllers.dto;

import java.util.List;

public class PlaylistsDTO {
    List<PlaylistDTO> playlists;
    int length;

    public PlaylistsDTO(List<PlaylistDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public PlaylistsDTO() {
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
