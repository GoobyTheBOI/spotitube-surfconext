package dea.spotitube.DAO;

import dea.spotitube.data.DAO.TokenDAO;
import dea.spotitube.data.database.connection.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TokenDAOTest {
    private TokenDAO sut;
    private final String token = "123";
    private DbConnection dbConnectionMock;
    private Connection connectionMock;
    private Statement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setup() {
        sut = new TokenDAO();
        dbConnectionMock = mock(DbConnection.class);
        statementMock = mock(Statement.class);
        connectionMock = mock(Connection.class);
        resultSetMock = mock(ResultSet.class);
    }

    @Test
    void checkIfTokenIsCorrect() {
        var expected = true;
        var actual = sut.checkIfTokenIsValid(token);

        assertEquals(expected, actual);
    }

    @Test
    void checkIfTokenIsIncorrect() {
        var expected = false;
        var inCorrectToken = "1234";
        var actual = sut.checkIfTokenIsValid(inCorrectToken);

        assertEquals(expected, actual);
    }
}
