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
    public static final int TRACK_ID = 1;
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
        when(mockedPlaylistDomain.getAllPlaylists(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testGetAllPlaylistsResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.getAllPlaylists(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());

    }

    @Test
    void testGetAllPlaylistsCallsGetAll() {
        // Setup
        when(mockedPlaylistDomain.getAllPlaylists(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getAllPlaylists(TOKEN);

        // Verify the results
        verify(mockedPlaylistDomain).getAllPlaylists(TOKEN);
    }

    @Test
    void testDeletePlaylistCallsDelete() {
        // Setup
        when(mockedPlaylistDomain.getAllPlaylists(TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedPlaylistDomain).deletePlaylist(PLAYLIST_ID, TOKEN);
    }

    @Test
    void testDeletePlaylistResponseEntity() {
        // Setup
        when(mockedPlaylistDomain.deletePlaylist(PLAYLIST_ID, TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testDeletePlaylistResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.deletePlaylist(PLAYLIST_ID, TOKEN)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deletePlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }

    @Test
    void testAddPlaylistResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.addPlaylist(TOKEN, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addPlaylist(playlistDTO, TOKEN);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_CREATED, result.getStatus());

    }

    @Test
    void testAddPlaylistResponseEntity() {
        // Setup
        when(mockedPlaylistDomain.addPlaylist(TOKEN, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addPlaylist(playlistDTO, TOKEN);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testAddPlaylistResponseCallsAddPlaylist() {
        // Setup
        when(mockedPlaylistDomain.addPlaylist(TOKEN, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addPlaylist(playlistDTO, TOKEN);

        // Verify the results
        verify(mockedPlaylistDomain).addPlaylist(TOKEN, playlistDTO);
    }


    @Test
    void testDeleteTrackFromPlaylistResponseStatus() {
        // Setup
        when(mockedTrackDomain.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());

    }

    @Test
    void testDeleteTrackFromPlaylistCallsDeleteTrackFromPlaylist() {
        // Setup
        when(mockedTrackDomain.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, PLAYLIST_ID);

        // Verify the results
        verify(mockedTrackDomain).deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, PLAYLIST_ID);
    }


    @Test
    void testAddTrackToPlaylistResponseStatus() {
        // Setup
        when(mockedTrackDomain.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_CREATED, result.getStatus());
    }

    @Test
    void testAddTrackToPlaylistResponseEntity() {
        // Setup
        when(mockedTrackDomain.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        Assertions.assertEquals(tracksDTO, result.getEntity());
    }

    @Test
    void testAddTrackToPlaylistCallsAddTrackToPlaylist() {
        // Setup
        when(mockedTrackDomain.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        verify(mockedTrackDomain).addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);
    }

    @Test
    void testEditPlaylistNameResponseStatus() {
        // Setup
        when(mockedPlaylistDomain.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }

    @Test
    void testEditPlaylistNameResponseEntity() {
        // Setup
        when(mockedPlaylistDomain.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        Assertions.assertEquals(playlistsDTO, result.getEntity());
    }

    @Test
    void testEditPlaylistNameCallsEditPlaylistName() {
        // Setup
        when(mockedPlaylistDomain.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO)).thenReturn(playlistsDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);

        // Verify the results
        verify(mockedPlaylistDomain).editPlaylistName(TOKEN, PLAYLIST_ID, playlistDTO);
    }

    @Test
    void testGetTracksFromPlaylistResponseStatus() {
        // Setup
        when(mockedTrackDomain.getTracksFromPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(HTTP_STATUS_OK, result.getStatus());
    }

    @Test
    void testGetTracksFromPlaylistResponseEntity() {
        // Setup
        when(mockedTrackDomain.getTracksFromPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        Assertions.assertEquals(tracksDTO, result.getEntity());
    }

    @Test
    void testGetTracksFromPlaylistCallsGetTracksFromPlaylist() {
        // Setup
        when(mockedTrackDomain.getTracksFromPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = playlistControllerUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedTrackDomain).getTracksFromPlaylist(TOKEN, PLAYLIST_ID);
    }
}
