package dea.spotitube.controllers;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.LoginService;
import dea.spotitube.CCC.DTO.LoginDTO;
import dea.spotitube.CCC.DTO.LoginResponseDTO;
import dea.spotitube.presentatie.controllers.LoginController;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    private LoginController sut;
    private LoginService loginServiceMock;
    private LoginDTO loginDTO;

    private final String token = "123";
    private final String username = "username";
    private final String password = "password";


    @BeforeEach
    void setup() {
       sut  = new LoginController();
       loginServiceMock = mock(LoginService.class);
       loginDTO = new LoginDTO(username, password);
       sut.setLoginService(loginServiceMock);
    }

    @Test
    void succesfullLogin() {
        // Arrange
        var expected = Response
                .status(Response.Status.OK)
                .entity(loginServiceMock.checkValidUser(loginDTO))
                .build();
        // Act
        var actual = sut.login(loginDTO);
        when(loginServiceMock.checkValidUser(loginDTO)).thenReturn(new LoginResponseDTO(token, username));

        // Assert
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void unsuccessfulLogin() {
        when(loginServiceMock.checkValidUser(any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () -> loginServiceMock.checkValidUser(loginDTO));
    }
}
