package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.domains.interfaces.TrackDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrackControllerTest {


    private static final int PLAYLIST_ID = 0;
    private static final String TOKEN = "123456";

    private TrackController trackControllerUnderTest;
    private TrackDomain mockedTrackDomain;
    private TracksDTO tracksDTO;

    @BeforeEach
    void setUp() {
        trackControllerUnderTest = new TrackController();
        mockedTrackDomain = mock(TrackDomain.class);
        trackControllerUnderTest.setTrackDomain(mockedTrackDomain);
        tracksDTO = new TracksDTO(new ArrayList<>());
    }

    @Test
    void testAllTracksNotInPlaylistResponseEntity() {
        // Setup
        when(mockedTrackDomain.getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = trackControllerUnderTest.allTracksNotInPlaylist(PLAYLIST_ID, TOKEN);

        // Verify the results
        assertEquals(tracksDTO, result.getEntity());
    }

    @Test
    void testAllTracksNotInPlaylistResponseStatus() {
        // Setup
        when(mockedTrackDomain.getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = trackControllerUnderTest.allTracksNotInPlaylist(PLAYLIST_ID, TOKEN);

        // Verify the results
        assertEquals(200, result.getStatus());
    }

    @Test
    void testAllTracksNotInPlaylistCallsGetAllTracksNotInPlaylist() {
        // Setup
        when(mockedTrackDomain.getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final Response result = trackControllerUnderTest.allTracksNotInPlaylist(PLAYLIST_ID, TOKEN);

        // Verify the results
        verify(mockedTrackDomain).getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID);
    }
}
