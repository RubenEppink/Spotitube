package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDataMapperImplTest {

    private UserDataMapperImpl userDataMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        userDataMapperImplUnderTest = new UserDataMapperImpl();
    }

    @Test
    void testToDTO() throws Exception {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        // Run the test
        final UserDTO result = userDataMapperImplUnderTest.toDTO(resultSet);

        // Verify the results
        assertEquals(UserDTO.class, result.getClass());
    }

    @Test
    void testToDTO_ThrowsSQLException() throws SQLException {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> {
            userDataMapperImplUnderTest.toDTO(resultSet);
        });
    }
}
