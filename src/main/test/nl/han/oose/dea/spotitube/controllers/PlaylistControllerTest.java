package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.domains.interfaces.PlaylistDomain;
import nl.han.oose.dea.spotitube.domains.interfaces.TrackDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class PlaylistControllerTest {
    public static final int PLAYLIST_ID = 0;
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final String TOKEN = "123456";


    private PlaylistController playlistControllerUnderTest;
    private PlaylistDomain mockedPlaylistDomain;
    private TrackDomain mockedTrackDomain;

    private PlaylistsDTO playlistsDTO;
    private PlaylistDTO playlistDTO;

    private TrackDTO trackDTO;
    private TracksDTO tracksDTO;


    @BeforeEach
    void setUp() {
        playlistControllerUnderTest = new PlaylistController();
        mockedPlaylistDomain = mock(PlaylistDomain.class);
        mockedTrackDomain = mock(TrackDomain.class);
        playlistControllerUnderTest.setPlaylistDomain(mockedPlaylistDomain);
        playlistControllerUnderTest.setTrackDomain(mockedTrackDomain);

        trackDTO = new TrackDTO(PLAYLIST_ID, "title", "performer", PLAYLIST_ID, "album", PLAYLIST_ID,
                "publicationDate", "description", false);
        tracksDTO = new TracksDTO(Arrays.asList(trackDTO));
        playlistDTO = new PlaylistDTO(PLAYLIST_ID, "name", false, Arrays.asList(trackDTO));
        playlistsDTO = new PlaylistsDTO(Arrays.asList(playlistDTO), 5678);

    }

    @Test
    void testGetAllPlaylistsResponseEntity() {
        // Setup
        when(mockedPlaylistDomain.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testGetAllPlaylistsResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());

    }

    @Test
    void testGetAllPlaylistsCallsGetAll() {
        // Setup
        when(mockedPlaylistDomain.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        verify(mockedPlaylistDomain).getAll(TOKEN);
    }

    @Test
    void testDeletePlaylistCallsDelete() {
        // Setup
        when(mockedPlaylistDomain.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedPlaylistDomain).delete(PLAYLIST_ID, TOKEN);
    }

    @Test
    void testDeletePlaylistResponseEntity() {
        // Setup
        when(mockedPlaylistDomain.delete(PLAYLIST_ID, TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testDeletePlaylistResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.delete(PLAYLIST_ID, TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }

    @Test
    void testAddPlaylist() {
        // Setup
        when(mockedPlaylistDomain.create(TOKEN, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addPlaylist(playlistDTO, TOKEN);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_CREATED, result.getStatus());
        Assertions.assertEquals(playlistsDTO, result.getEntity());
        verify(mockedPlaylistDomain).create(TOKEN, playlistDTO);

    }

    @Test
    void testDeletePlaylist() {
        // Setup

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Setup

        // Run the test
        final Response result = playlistControllerUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, PLAYLIST_ID);

        // Verify the results
    }

    @Test
    void testDeleteTrackFromPlaylist1() {
        // Setup
        final TrackDTO trackDTO = new TrackDTO(PLAYLIST_ID, "title", "performer", PLAYLIST_ID, "album", PLAYLIST_ID, "publicationDate", "description", false);

        // Run the test
        final Response result = playlistControllerUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
    }

    @Test
    void testEditPlaylistName() {
        // Setup
        when(mockedPlaylistDomain.update(TOKEN, PLAYLIST_ID, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        verify(mockedPlaylistDomain).update(TOKEN, PLAYLIST_ID, playlistDTO);
        Assertions.assertEquals(playlistsDTO, result.getEntity());
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());

    }

    @Test
    void testGetAllPlaylists() {
        // Setup
        when(mockedPlaylistDomain.getAll(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        verify(mockedPlaylistDomain).getAll(TOKEN);
        Assertions.assertEquals(playlistsDTO, result.getEntity());
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }

    @Test
    void testGetTracksFromPlaylist() {
        // Setup
        when(mockedTrackDomain.getAllTracksInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedTrackDomain).getAllTracksInPlaylist(TOKEN, PLAYLIST_ID);
        Assertions.assertEquals(tracksDTO, result.getEntity());
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }
}
