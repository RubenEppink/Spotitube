package nl.han.oose.dea.spotitube.Domain;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dto.TrackDTO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.PlaylistDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class PlaylistDomainTest {

    private PlaylistDomain playlistDomainUnderTest;
    private PlaylistDAO mockedPlaylistDAO;

    @BeforeEach
    void setUp() {
        playlistDomainUnderTest = new PlaylistDomain();
        mockedPlaylistDAO = mock(PlaylistDAO.class);
        playlistDomainUnderTest.setPlaylistDAO(mockedPlaylistDAO);
    }

    @Test
    void testGetAllReturnsPlaylistsDTO() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(playlistDomainUnderTest.playlistDAO.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAll();

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
        when(playlistDomainUnderTest.playlistDAO.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAll();

        // Verify the results
        verify(mockedPlaylistDAO).getAll();
    }



    @Test
    void testDeleteCallsPlaylistDAODelete() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(playlistDomainUnderTest.playlistDAO.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.delete(0);

        // Verify the results
        verify(mockedPlaylistDAO).delete(0);
    }

    @Test
    void testDeleteReturnsPlaylistsDTO() {
        // Setup

        // Configure PlaylistDAO.getAll(...).
        final PlaylistsDTO playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0, "publicationDate", "description", false)))), 0);
        when(playlistDomainUnderTest.playlistDAO.getAll()).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.delete(0);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }
}
