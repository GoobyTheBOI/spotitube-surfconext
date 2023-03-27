package dea.spotitube.mappers;

import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.data.mappers.LoginResponseMapper;
import dea.spotitube.CCC.DTO.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginResponeMapperTest {
    private LoginResponseMapper sut;
    private ResultSet resultSetMock;

    @BeforeEach
    void setup() {
        sut = new LoginResponseMapper();
        resultSetMock = mock(ResultSet.class);
    }

    @Test
    void mapperShouldReturnRightValues() throws SQLException {
        String token = "token";
        String username = "username";
        var expected = new LoginResponseDTO(token, username);
        when(resultSetMock.next()).thenReturn(true, false);
        when(resultSetMock.getString(token)).thenReturn(token);
        when(resultSetMock.getString("fullname")).thenReturn(username);

        var actual = sut.toMapper(resultSetMock);

        assertEquals(expected.getToken(), actual.getToken());
        assertEquals(expected.getUser(), actual.getUser());
    }

    @Test
    void mapperShouldReturnExceptionWhenThereIsSQLError() throws SQLException {
        doThrow(SQLException.class).when(resultSetMock).next();
        assertThrows(InternalServerException.class, () -> sut.toMapper(resultSetMock));
    }



}
