package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class PlaylistDomainTest {

    private PlaylistDomainImpl playlistDomainUnderTest;
    private PlaylistDAO mockedPlaylistDAO;
    private String token;

    @BeforeEach
    void setUp() {
        playlistDomainUnderTest = new PlaylistDomainImpl();
        mockedPlaylistDAO = mock(PlaylistDAO.class);
        playlistDomainUnderTest.setPlaylistDAO(mockedPlaylistDAO);
        token = "123456";
    }

    @Test
    void testGetAllReturnsPlaylistsDTO() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDAO.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAllPlaylists(token);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }

    @Test
    void testGetAllCallsGetAll() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDAO.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAllPlaylists(token);

        // Verify the results
        verify(mockedPlaylistDAO).getAll(token);
    }



    @Test
    void testDeleteCallsPlaylistDAODelete() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDAO.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.deletePlaylist(0, token);

        // Verify the results
        verify(mockedPlaylistDAO).delete(0, token);
    }

    @Test
    void testDeleteReturnsPlaylistsDTO() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(mockedPlaylistDAO.getAll(token)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.deletePlaylist(0, token);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }
}
