package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginDataMapperImplTest {

    private LoginDataMapperImpl loginDataMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        loginDataMapperImplUnderTest = new LoginDataMapperImpl();
    }

    @Test
    void testToDTO() throws Exception {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        // Run the test
        final LoginDTO result = loginDataMapperImplUnderTest.toDTO(resultSet);

        // Verify the results
        assertEquals(LoginDTO.class, result.getClass());
    }

    @Test
    void testToDTO_ThrowsSQLException() throws SQLException {
        // Setup
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> {
            loginDataMapperImplUnderTest.toDTO(resultSet);
        });
    }
}
