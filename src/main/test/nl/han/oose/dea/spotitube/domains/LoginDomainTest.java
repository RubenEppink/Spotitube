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
    private LoginDomainImpl loginDomainUnderTest;
    private LoginDAO mockedLoginDAO;
    private UserDAO mockedUserDAO;

    @BeforeEach
    void setUp() {
        loginDomainUnderTest = new LoginDomainImpl();
        mockedLoginDAO = mock(LoginDAO.class);
        mockedUserDAO = mock(UserDAO.class);
        loginDomainUnderTest.setLoginDAO(mockedLoginDAO);
        loginDomainUnderTest.setUserDAO(mockedUserDAO);
    }

    @Test
    void testValidateCredentialsCallsLoginRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(mockedLoginDAO.read("ruben")).thenReturn(loginDTO);
        when(mockedUserDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedLoginDAO).read("ruben");
    }

    @Test
    void testLogincallsUserRead() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(mockedLoginDAO.read("ruben")).thenReturn(loginDTO);
        when(mockedUserDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedUserDAO).read("ruben");
    }

    @Test
    void testValidateCredentialsThrowsException() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(mockedLoginDAO.read("ruben")).thenReturn(new LoginDTO("nebur", "droowthcaw"));
        when(mockedUserDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        // Verify the results
        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginDomainUnderTest.validateCredentials(loginDTO));
    }

    @Test
    void testValidateCredentialsReturnsUserDTO() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(mockedLoginDAO.read("ruben")).thenReturn(loginDTO);
        when(mockedUserDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        Assertions.assertEquals(userDTO, result);
    }

    @Test
    void testValidateCredentialsLoginReadReturnsLoginDTO() {
        // Setup
        final LoginDTO loginDTO = new LoginDTO("ruben", "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        final UserDTO userDTO = new UserDTO("123456", "lmfao");

        when(mockedLoginDAO.read("ruben")).thenReturn(loginDTO);
        when(mockedUserDAO.read("ruben")).thenReturn(userDTO);

        // Run the test
        final LoginDTO result = mockedLoginDAO.read("ruben");

        // Verify the results
        Assertions.assertEquals(loginDTO, result);
    }


}
