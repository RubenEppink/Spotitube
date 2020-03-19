package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.domains.interfaces.LoginDomain;
import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.domains.LoginDomainImpl;
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
        mockedLoginDomain = mock(LoginDomainImpl.class);
        loginControllerUnderTest.setLoginDomain(mockedLoginDomain);
    }

    @Test
    void testValidateLogin() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "3l1t3h4ckz0rz");
        when(mockedLoginDomain.validateCredentials(loginDTO)).thenReturn(userDTO);

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