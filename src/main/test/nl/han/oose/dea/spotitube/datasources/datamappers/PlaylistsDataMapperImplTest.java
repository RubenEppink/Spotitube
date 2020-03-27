package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlaylistsDataMapperImplTest {

    private PlaylistsDataMapperImpl playlistsDataMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        playlistsDataMapperImplUnderTest = new PlaylistsDataMapperImpl();
    }

    @Test
    void testToDTO() throws Exception {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        // Run the test
        final PlaylistsDTO result = playlistsDataMapperImplUnderTest.toDTO(resultSet);

        // Verify the results
        assertEquals(PlaylistsDTO.class, result.getClass());
    }

    @Test
    void testToDTO_ThrowsSQLException() throws SQLException {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> {
            playlistsDataMapperImplUnderTest.toDTO(resultSet);
        });
    }
}
