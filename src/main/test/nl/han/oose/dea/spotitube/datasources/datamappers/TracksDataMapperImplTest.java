package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TracksDataMapperImplTest {

    private TracksDataMapperImpl tracksDataMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        tracksDataMapperImplUnderTest = new TracksDataMapperImpl();
    }

    @Test
    void testToDTO() throws Exception {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        // Run the test
        final TracksDTO result = tracksDataMapperImplUnderTest.toDTO(resultSet);

        // Verify the results
        assertEquals(TracksDTO.class, result.getClass());
    }

    @Test
    void testToDTO_ThrowsSQLException() throws SQLException {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> {
            tracksDataMapperImplUnderTest.toDTO(resultSet);
        });
    }
}
