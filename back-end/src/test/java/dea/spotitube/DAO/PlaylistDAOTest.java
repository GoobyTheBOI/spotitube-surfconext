package dea.spotitube.DAO;

import dea.spotitube.CCC.exceptions.NotFoundException;
import dea.spotitube.data.DAO.LoginDAO;
import dea.spotitube.data.DAO.PlaylistDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaylistDAOTest {
    private PlaylistDAO sut;
    private LoginDAO loginDAOMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() {
        sut = new PlaylistDAO();
        loginDAOMock = mock(LoginDAO.class);
        resultSetMock = mock(ResultSet.class);
        sut.setLoginDAO(loginDAOMock);
    }

    @Test
    void getOwner() throws SQLException {
        var expected = "test";

        when(loginDAOMock.getUserByToken("123")).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getString("fullname")).thenReturn("test");
        var actual = sut.getOwner("123");

        assertEquals(expected, actual);
    }

    @Test
    void getOwnerThrowsException() throws SQLException {
        when(loginDAOMock.getUserByToken("123")).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(false);
        assertThrows(NotFoundException.class, () -> sut.getOwner("123"));
    }
}
