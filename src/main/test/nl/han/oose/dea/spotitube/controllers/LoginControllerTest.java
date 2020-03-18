package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.Domain.LoginDomain;
import nl.han.oose.dea.spotitube.controllers.dto.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dto.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginController loginControllerUnderTest;
    private LoginDomain mockedLoginDomain;

    @BeforeEach
    void setUp() {
        loginControllerUnderTest = new LoginController();
        mockedLoginDomain = mock(LoginDomain.class);
        loginControllerUnderTest.setLoginDomain(mockedLoginDomain);
    }

    @Test
    void testValidateLogin() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "3l1t3h4ckz0rz");
        when(loginControllerUnderTest.loginDomain.validateCredentials(loginDTO)).thenReturn(userDTO);

        // Run the test
        final Response result = loginControllerUnderTest.login(loginDTO);

        // Verify the results
        Assertions.assertEquals(201, result.getStatus());
        Assertions.assertEquals(userDTO, result.getEntity());
        verify(mockedLoginDomain).validateCredentials(loginDTO);
    }

    @Test
    void testValidateLoginThrowsException() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        doThrow(InvalidCredentialsException.class).when(mockedLoginDomain).validateCredentials(loginDTO);

        // Run the tes
        // Verify the results
        Assertions.assertThrows(InvalidCredentialsException.class,
                () -> loginControllerUnderTest.login(loginDTO));
    }
}