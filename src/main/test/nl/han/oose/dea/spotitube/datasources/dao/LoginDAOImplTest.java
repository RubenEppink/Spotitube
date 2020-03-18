package nl.han.oose.dea.spotitube.datasources.dao;

import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;
import nl.han.oose.dea.spotitube.datasources.connection.DBConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class LoginDAOImplTest {

    private LoginDAOImpl loginDAOImplUnderTest;

    @BeforeEach
    void setUp() {
        loginDAOImplUnderTest = new LoginDAOImpl();
        loginDAOImplUnderTest.dbConnection = mock(DBConnection.class);
    }

    @Test
    void testReadCallsGetConnection() throws Exception {
        // Setup

        // Configure DBConnection.getConnection(...).
        final Connection mockConnection = mock(Connection.class);
        when(loginDAOImplUnderTest.dbConnection.getConnection()).thenReturn(mockConnection);

        // Run the test
        final LoginDTO result = loginDAOImplUnderTest.read("userLogin");

        // Verify the results
        verify(mockConnection).close();
        verify(loginDAOImplUnderTest.dbConnection).closeConnection();
    }

    @Test
    void testRead_DBConnectionThrowsSQLException() throws Exception {
        // Setup
        when(loginDAOImplUnderTest.dbConnection.getConnection()).thenThrow(SQLException.class);

        // Run the test
        // Verify the results
        Assertions.assertThrows(SQLException.class,
                () -> loginDAOImplUnderTest.read("userLogin"));
    }
}
