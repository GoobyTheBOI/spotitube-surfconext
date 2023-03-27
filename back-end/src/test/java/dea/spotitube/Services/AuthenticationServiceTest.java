package dea.spotitube.Services;

import dea.spotitube.business.StringEncoder;
import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.business.services.AuthenticationService;
import dea.spotitube.data.DAO.TokenDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    private AuthenticationService sut;
    private TokenDAO tokenDAOMock;
    private StringEncoder stringEncoderMock;
    private final String token = "token";

    @BeforeEach
    void setUp() {
        sut = new AuthenticationService();
        tokenDAOMock = mock(TokenDAO.class);
        stringEncoderMock = mock(StringEncoder.class);
        sut.setStringEncoder(stringEncoderMock);
        sut.setTokenDAO(tokenDAOMock);
    }
    @Test
    void generateHashStringReturnHashString() {
        String input = "test";
        String expected = "hash";

        when(stringEncoderMock.stringToHash(input)).thenReturn("hash");
        String actual = sut.generateHashString(input);

        assertEquals(expected, actual);
    }

    @Test
    void checkIfTokenIsValid() {
        boolean expected = true;

        when(tokenDAOMock.checkIfTokenIsValid(token)).thenReturn(true);
        boolean actual = sut.checkIfTokenIsValid(token);

        assertEquals(expected, actual);
    }

    @Test
    void checkIfTokenIsInvalid() {
        boolean expected = false;

        when(tokenDAOMock.checkIfTokenIsValid(token)).thenReturn(false);
        boolean actual = sut.checkIfTokenIsValid(token);

        assertEquals(expected, actual);
    }

    @Test
    void checkIfTokenIsValidThrowsException() {
        when(tokenDAOMock.checkIfTokenIsValid(token)).thenThrow(new InternalServerException());
        assertThrows(InternalServerException.class, () -> sut.checkIfTokenIsValid(token));
    }
}
