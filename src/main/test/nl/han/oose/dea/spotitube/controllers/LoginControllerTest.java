package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dto.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

class LoginControllerTest {
/*    private LoginController loginControllerUnderTest;
    private LoginDAO mockedLoginDAO;
    private UserDAO mockedUserDAO;

    @BeforeEach
    void setUp() {
        loginControllerUnderTest = new LoginController();
        mockedLoginDAO = mock(LoginDAO.class);
        mockedUserDAO = mock(UserDAO.class);
        loginControllerUnderTest.setLoginDAO(mockedLoginDAO);
        loginControllerUnderTest.setUserDAO(mockedUserDAO);
    }

    @Test
    void testLogincallsLoginRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginControllerUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginControllerUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginControllerUnderTest.login(loginDTO);

        // Verify the results
        verify(mockedLoginDAO).read("ruben");
    }

    @Test
    void testLogincallsUserRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginControllerUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginControllerUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginControllerUnderTest.login(loginDTO);

        // Verify the results
        verify(mockedUserDAO).read("ruben");
    }

    @Test
    void testLoginResponseStatus() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginControllerUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginControllerUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginControllerUnderTest.login(loginDTO);

        // Verify the results
        Assertions.assertEquals(201, result.getStatus());

    }

    @Test
    void testLoginResponseEntity() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginControllerUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginControllerUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final Response result = loginControllerUnderTest.login(loginDTO);

        // Verify the results
        Assertions.assertEquals(userDTO, result.getEntity());
    }

    @Test
    void testLoginThrowsException() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginControllerUnderTest.loginDAO.read("ruben")).thenReturn(new LoginDTO("nebur", "droowthcaw"));
        when(loginControllerUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        // Verify the results
        Assertions.assertThrows(InvalidCredentialsException.class, () ->loginControllerUnderTest.login(loginDTO));
    }*/
}
