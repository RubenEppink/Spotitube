package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedUserDAO).read("ruben");
    }

    @Test
    void testValidateCredentialsThrowsException() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(new LoginDTO("nebur", "droowthcaw"));
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        // Verify the results
        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginDomainUnderTest.validateCredentials(loginDTO));
    }

    @Test
    void testValidateCredentialsReturnsUserDTO() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        Assertions.assertEquals(userDTO, result);
    }

    @Test
    void testValidateCredentialsLoginReadReturnsLoginDTO() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "wachtwoord");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(loginDomainUnderTest.loginDAO.read("ruben")).thenReturn(loginDTO);
        when(loginDomainUnderTest.userDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final LoginDTO result = mockedLoginDAO.read("ruben");

        // Verify the results
        Assertions.assertEquals(loginDTO, result);
    }


}
