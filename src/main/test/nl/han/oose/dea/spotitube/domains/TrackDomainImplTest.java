package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.daos.TrackDAOImpl;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrackDomainImplTest {
    private final static String TOKEN = "123456";
    private final static int PLAYLIST_ID = 1;
    private final static int TRACK_ID = 5;

    private TrackDomainImpl trackDomainImplUnderTest;
    private TrackDAO mockedTrackDAO;
    private TracksDTO tracksDTO;
    private TrackDTO trackDTO;

    @BeforeEach
    void setUp() {
        trackDomainImplUnderTest = new TrackDomainImpl();
        mockedTrackDAO = mock(TrackDAOImpl.class);
        trackDomainImplUnderTest.setTrackDAO(mockedTrackDAO);
        tracksDTO = new TracksDTO(new ArrayList<>());
        trackDTO = new TrackDTO(0, "title", "performer",
                0, "album", 0, "publicationDate",
                "description", false);

    }

    @Test
    void testGetTracksFromPlaylistReturnsTracksDTO() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        assertEquals(result, tracksDTO);
    }

    @Test
    void testGetTracksFromPlaylistCallsGetAllInPlaylist() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.getTracksFromPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedTrackDAO).getAllInPlaylist(TOKEN, PLAYLIST_ID);
    }

    @Test
    void testGetAllTracksNotInPlaylistReturnsTracksDTO() {
        // Setup
        when(mockedTrackDAO.getAllNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        assertEquals(result, tracksDTO);
    }

    @Test
    void testGetAllTracksNotInPlaylistCallsGetAllNotInPlaylist() {
        // Setup
        when(mockedTrackDAO.getAllNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.getAllTracksNotInPlaylist(TOKEN, PLAYLIST_ID);

        // Verify the results
        verify(mockedTrackDAO).getAllNotInPlaylist(TOKEN, PLAYLIST_ID);
    }

    @Test
    void testDeleteTrackFromPlaylistReturnsTracksDTO() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

        // Verify the results
        assertEquals(result, tracksDTO);

    }

    @Test
    void testDeleteTrackFromPlaylistCallsDelete() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

        // Verify the results
        verify(mockedTrackDAO).delete(TOKEN, PLAYLIST_ID, TRACK_ID);
    }

    @Test
    void testAddTrackToPlaylistReturnsTracksDTO() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        assertEquals(result, tracksDTO);
    }

    @Test
    void testAddTrackToPlaylistCallsAddTrackToPlaylistWithoutUpdate() {
        // Setup
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);
        when(mockedTrackDAO.get(0)).thenReturn(trackDTO);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        verify(mockedTrackDAO).addToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);
    }

    @Test
    void testAddTrackToPlaylistCallsUpdateAndAddToPlaylist() {
        // Setup
         TrackDTO trackDTO1 = new TrackDTO(0, "title", "performer",
                0, "album", 0, "publicationDate",
                "description", true);
        when(mockedTrackDAO.getAllInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(tracksDTO);
        when(mockedTrackDAO.get(0)).thenReturn(trackDTO1);

        // Run the test
        final TracksDTO result = trackDomainImplUnderTest.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);

        // Verify the results
        verify(mockedTrackDAO).update(TOKEN, PLAYLIST_ID, trackDTO);
        verify(mockedTrackDAO).addToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);
    }
}
