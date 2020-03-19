package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.domains.interfaces.PlaylistDomain;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.domains.PlaylistDomainImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class PlaylistControllerTest {

    private PlaylistController playlistControllerUnderTest;
    private PlaylistDomain mockedPlaylistDomain;
    private String token;

    @BeforeEach
    void setUp() {
        playlistControllerUnderTest = new PlaylistController();
        mockedPlaylistDomain = mock(PlaylistDomainImpl.class);
        playlistControllerUnderTest.setPlaylistDomain(mockedPlaylistDomain);
        token = "123456";
    }

    @Test
    void testGetAllPlaylistsResponseEntity() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists("123456");

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testGetAllPlaylistsResponseStatus() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists("123456");

        // Verify the results
        Assertions.assertEquals(200, result.getStatus());

    }

    @Test
    void testGetAllPlaylistsCallsGetAll() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists("123456");

        // Verify the results
        verify(mockedPlaylistDomain).getAll(token);
    }

    @Test
    void testDeletePlaylistCallsDelete() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456", 0);

        // Verify the results
        verify(mockedPlaylistDomain).delete(0, token);
    }

    @Test
    void testDeletePlaylistResponseEntity() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.delete(0, token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456", 0);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testDeletePlaylistResponseStatus() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO =
                new PlaylistsDTO(Arrays.asList(
                        new PlaylistDTO(4, "name", false, Arrays.asList(
                                new TrackDTO(0, "title", "performer", 0, "album", 0,
                                        "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDomain.delete(0, token)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456", 0);

        // Verify the results
        Assertions.assertEquals(200, result.getStatus());
    }
}
