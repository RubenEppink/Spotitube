package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class LoginDAOImplTest {

    private LoginDAOImpl loginDAOImplUnderTest;
    private DBConnection mockedDBConnection;

    @BeforeEach
    void setUp() {
        loginDAOImplUnderTest = new LoginDAOImpl();
        mockedDBConnection =  mock(DBConnection.class);
        loginDAOImplUnderTest.setDbConnection(mockedDBConnection);
    }

    @Test
    void testReadCallsGetConnection() throws Exception {
        // Setup

        // Configure DBConnection.getConnection(...).
        final Connection mockConnection = mock(Connection.class);
        when(mockedDBConnection.getConnection()).thenReturn(mockConnection);

        // Run the test
        final LoginDTO result = loginDAOImplUnderTest.read("userLogin");

        // Verify the results
        verify(mockConnection).close();
        verify(mockedDBConnection).closeConnection(connection);
    }

    @Test
    void testRead_DBConnectionThrowsSQLException() throws Exception {
        // Setup
        when(mockedDBConnection.getConnection()).thenThrow(SQLException.class);

        // Run the test
        // Verify the results
        Assertions.assertThrows(SQLException.class,
                () -> loginDAOImplUnderTest.read("userLogin"));
    }
}
