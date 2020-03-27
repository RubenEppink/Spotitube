package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrackDataMapperImplTest {

    private TrackDataMapperImpl trackDataMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        trackDataMapperImplUnderTest = new TrackDataMapperImpl();
    }

    @Test
    void testToDTO() throws Exception {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        // Run the test
        final TrackDTO result = trackDataMapperImplUnderTest.toDTO(resultSet);

        // Verify the results
        assertEquals(TrackDTO.class, result.getClass());
    }

    @Test
    void testToDTO_ThrowsSQLException() throws SQLException {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> {
            trackDataMapperImplUnderTest.toDTO(resultSet);
        });
    }
}
