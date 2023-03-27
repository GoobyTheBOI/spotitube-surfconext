package dea.spotitube.DAO;

import dea.spotitube.data.DAO.LoginDAO;
import dea.spotitube.data.mappers.LoginResponseMapper;
import dea.spotitube.CCC.DTO.LoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginDAOTest {

    @InjectMocks
    private LoginDAO sut;
    private LoginDTO loginDTO;
    private LoginResponseMapper loginResponseMapperMock;
    private ResultSet resultSetMock;
    private final String user = "user";
    private final String password = "password";



    @BeforeEach
    void setup() {
        sut = new LoginDAO();
        loginDTO = new LoginDTO(user, password);
        loginResponseMapperMock = mock(LoginResponseMapper.class);
        resultSetMock = mock(ResultSet.class);
        sut.setLoginResponseMapper(loginResponseMapperMock);
    }


    @Test
    void checkIfUserExitsFromDatabase() throws SQLException {
        var expected = true;
        when(resultSetMock.next()).thenReturn(true);
        var actual = sut.checkIfUserExits(user);

        assertEquals(expected, actual);
    }
    @Test
    void checkIfInvalidUserExitsFromDatabase() throws SQLException {
        var expected = false;
        when(resultSetMock.next()).thenReturn(false);
        var actual = sut.checkIfUserExits("invalid");

        assertEquals(expected, actual);
    }

    @Test
    void getUserFromDatabase() throws SQLException {
        var actual = sut.getUser(loginDTO);
        actual.next();
        assertEquals(user, actual.getString("username"));
    }

}
