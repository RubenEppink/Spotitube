package nl.han.oose.dea.spotitube.domains;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotAuthorizedException;

import static org.mockito.Mockito.*;

class LoginDomainImplTest {
    private LoginDomainImpl loginDomainUnderTest;
    private LoginDAO mockedLoginDAO;
    private UserDAO mockedUserDAO;

    private LoginDTO loginDTO;
    private UserDTO userDTO;
    private LoginDTO loginDatabaseDTO;

    @BeforeEach
    void setUp() {
        loginDomainUnderTest = new LoginDomainImpl();
        mockedLoginDAO = mock(LoginDAO.class);
        mockedUserDAO = mock(UserDAO.class);
        loginDomainUnderTest.setLoginDAO(mockedLoginDAO);
        loginDomainUnderTest.setUserDAO(mockedUserDAO);

        userDTO = new UserDTO("123456", "3l1t3h4ckz0rz");
        loginDTO = new LoginDTO("ruben", "wachtwoord");
        loginDatabaseDTO = new LoginDTO("ruben",
                "dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");

    }

    @Test
    void testValidateCredentialsCallsLoginRead() {
        // Setup
        when(mockedLoginDAO.getLoginInfo(loginDTO.getUser())).thenReturn(loginDatabaseDTO);
        when(mockedUserDAO.getUserInfo(loginDTO.getUser())).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedLoginDAO).getLoginInfo("ruben");
    }

    @Test
    void testLogincallsUserRead() {
        // Setup
        when(mockedLoginDAO.getLoginInfo("ruben")).thenReturn(loginDatabaseDTO);
        when(mockedUserDAO.getUserInfo("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        verify(mockedUserDAO).getUserInfo("ruben");
    }

    @Test
    void testValidateCredentialsThrowsException() {
        // Setup
        when(mockedLoginDAO.getLoginInfo("ruben")).thenReturn(new LoginDTO("nebur", "droowthcaw"));
        when(mockedUserDAO.getUserInfo("ruben")).thenReturn(userDTO);

        // Run the test
        // Verify the results
        Assertions.assertThrows(NotAuthorizedException.class, () -> loginDomainUnderTest.validateCredentials(loginDTO));
    }

    @Test
    void testValidateCredentialsReturnsUserDTO() {
        // Setup
        when(mockedLoginDAO.getLoginInfo("ruben")).thenReturn(loginDatabaseDTO);
        when(mockedUserDAO.getUserInfo("ruben")).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginDomainUnderTest.validateCredentials(loginDTO);

        // Verify the results
        Assertions.assertEquals(userDTO, result);
    }

    @Test
    void testValidateCredentialsGetLoginInfoReturnsLoginDTO() {
        // Setup
        when(mockedLoginDAO.getLoginInfo("ruben")).thenReturn(loginDatabaseDTO);
        when(mockedUserDAO.getUserInfo("ruben")).thenReturn(userDTO);

        // Run the test
        final LoginDTO result = mockedLoginDAO.getLoginInfo("ruben");

        // Verify the results
        Assertions.assertEquals(loginDatabaseDTO, result);
    }


}
