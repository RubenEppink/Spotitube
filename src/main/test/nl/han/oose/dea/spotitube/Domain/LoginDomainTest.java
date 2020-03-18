package nl.han.oose.dea.spotitube.Domain;

import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dto.UserDTO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

class LoginDomainTest {
    private LoginDomain loginDomainUnderTest;
    private LoginDAO mockedLoginDAO;
    private UserDAO mockedUserDAO;

    @BeforeEach
    void setUp() {
        loginDomainUnderTest = new LoginDomain();
        mockedLoginDAO = mock(LoginDAO.class);
        mockedUserDAO = mock(UserDAO.class);
        loginDomainUnderTest.setLoginDAO(mockedLoginDAO);
        loginDomainUnderTest.setUserDAO(mockedUserDAO);
    }

    @Test
    void testValidateCredentialsCallsLoginRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedLoginDAO).read("ruben");
    }

    @Test
    void testLogincallsUserRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginDomainUnderTest.login(loginDTO);

        // Verify the results
        verify(mockedUserDAO).read("ruben");
    }

    @Test
    void testLoginResponseStatus() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginDomainUnderTest.login(loginDTO);

        // Verify the results
        Assertions.assertEquals(201, result.getStatus());

    }

    @Test
    void testLoginResponseEntity() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginDomainUnderTest.login(loginDTO);

        // Verify the results
        Assertions.assertEquals(userDTO, result.getEntity());
    }

    @Test
    void testLoginThrowsException() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(new LoginDTO("nebur", "droowthcaw"));
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        // Verify the results
        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginDomainUnderTest.login(loginDTO));
    }
}
