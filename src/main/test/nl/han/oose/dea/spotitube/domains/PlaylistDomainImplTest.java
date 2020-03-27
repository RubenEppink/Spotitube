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

class PlaylistDomainImplTest {

    private static final String TOKEN = "123456";
    public static final int PLAYLIST_ID = 0;
    private PlaylistDomainImpl playlistDomainUnderTest;
    private PlaylistDAO mockedPlaylistDAO;
    private PlaylistsDTO playlistsDTO;
    private PlaylistDTO playlistDTO;


    @BeforeEach
    void setUp() {
        playlistDomainUnderTest = new PlaylistDomainImpl();
        mockedPlaylistDAO = mock(PlaylistDAO.class);
        playlistDomainUnderTest.setPlaylistDAO(mockedPlaylistDAO);


        playlistDTO = new PlaylistDTO(0, "name", false, Arrays.asList(
                new TrackDTO(0, "title", "performer", 0, "album", 0,
                        "publicationDate", "description", false)));

        playlistsDTO = new PlaylistsDTO(Arrays.asList(
                new PlaylistDTO(0, "name", false, Arrays.asList(
                        new TrackDTO(0, "title", "performer", 0, "album", 0,
                                "publicationDate", "description", false)))), 0);
    }

    @Test
    void testGetAllReturnsPlaylistsDTO() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }

    @Test
    void testGetAllCallsGetAll() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        verify(mockedPlaylistDAO).getAll(TOKEN);
    }


    @Test
    void testDeleteCallsPlaylistDAODelete() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.deletePlaylist(0, TOKEN);

        // Verify the results
        verify(mockedPlaylistDAO).delete(PLAYLIST_ID, TOKEN);
    }

    @Test
    void testDeleteReturnsPlaylistsDTO() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.deletePlaylist(0, TOKEN);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }

    @Test
    void testAddPlaylistReturnsPlaylistsDTO() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.addPlaylist(TOKEN, playlistDTO);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }

    @Test
    void testAddPlaylistCallsCreate() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.addPlaylist(TOKEN, playlistDTO);

        // Verify the results
        verify(mockedPlaylistDAO).create(TOKEN, playlistDTO);
    }

    @Test
    void testEditPlaylistCallsUpdate() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        verify(mockedPlaylistDAO).update(TOKEN, PLAYLIST_ID, playlistDTO);
    }

    @Test
    void testEditPlaylistReturnsPlaylistsDTO() {
        // Setup
        when(mockedPlaylistDAO.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final PlaylistsDTO result = playlistDomainUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result);
    }


}
