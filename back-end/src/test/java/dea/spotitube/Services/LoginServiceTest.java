package dea.spotitube.Services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.AuthenticationService;
import dea.spotitube.business.services.LoginService;
import dea.spotitube.data.DAO.LoginDAO;
import dea.spotitube.data.mappers.LoginResponseMapper;
import dea.spotitube.CCC.DTO.LoginDTO;
import dea.spotitube.CCC.DTO.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    private LoginService sut;
    private LoginDAO loginDAOMock;
    private AuthenticationService authenticationServiceMock;
    private LoginDTO loginDTO;

    private final String username = "user";
    private final String password = "password";
    private final String testToken = "123";
    @BeforeEach
    public void setup() {
        sut = new LoginService();
        loginDAOMock = mock(LoginDAO.class);
        authenticationServiceMock = mock(AuthenticationService.class);
        loginDTO = new LoginDTO(username, password);
        sut.setLoginDAO(loginDAOMock);
        sut.setAuthenticationService(authenticationServiceMock);
    }


    @Test
    void checkUserWithUserThatDoesNotExist() {
        var inValidUser = "invalidUser";
        assertThrows(UnauthorizedException.class, () -> sut.checkValidUser(new LoginDTO(inValidUser, password)));
    }

    @Test
    void checkIfUserReturnsLoginResponseDTO() throws SQLException {
        var expected = new LoginResponseDTO(loginDTO.getUser(), testToken);
        when(loginDAOMock.checkIfUserExits(username)).thenReturn(true);
        when(loginDAOMock.checkValidUser(loginDTO)).thenReturn(expected);

        var actual = sut.checkValidUser(loginDTO);

        assertEquals(expected.getUser(), actual.getUser());
    }
}
