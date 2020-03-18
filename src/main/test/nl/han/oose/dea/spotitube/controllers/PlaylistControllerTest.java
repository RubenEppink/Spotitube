package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dto.TrackDTO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.PlaylistDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class PlaylistControllerTest {

/*    private PlaylistController playlistControllerUnderTest;

    @BeforeEach
    void setUp() {
        playlistControllerUnderTest = new PlaylistController();
        playlistControllerUnderTest.playlistDomain = mock(PlaylistDAO.class);
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
        when(playlistControllerUnderTest.playlistDomain.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456");

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
        when(playlistControllerUnderTest.playlistDomain.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456");

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
        when(playlistControllerUnderTest.playlistDomain.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist("123456");

        // Verify the results
        verify(playlistControllerUnderTest.playlistDomain).getAll();
    }*/
}
