package dea.spotitube.Services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.AuthenticationService;
import dea.spotitube.business.services.PlaylistService;
import dea.spotitube.data.DAO.PlaylistDAO;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import dea.spotitube.CCC.DTO.PlaylistsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistServiceTest {
    private PlaylistService sut;
    private AuthenticationService authenticationServiceMock;
    private PlaylistDAO playlistDAOMock;
    private final String token = "123";

    @BeforeEach
    void setup() {
        sut = new PlaylistService();
        authenticationServiceMock = mock(AuthenticationService.class);
        playlistDAOMock = mock(PlaylistDAO.class);
        sut.setTokenValid(authenticationServiceMock);
        sut.setPlaylistDAO(playlistDAOMock);
    }

    @Test
    void getPlaylistThrowsExeption() {
        assertThrows(UnauthorizedException.class, () -> sut.getAllPlaylists(token));
    }

    @Test
    void updatePlaylistThrowsExeption() {
        assertThrows(UnauthorizedException.class, () -> sut.updatePlaylist(token, 1, null));
    }

    @Test
    void addPlaylistThrowsExeption() {
        assertThrows(UnauthorizedException.class, () -> sut.addPlaylist(token, null));
    }

    @Test
    void deletePlaylistThrowsExeption() {
        assertThrows(UnauthorizedException.class, () -> sut.deletePlaylist(token, 1));
    }

    @Test
    void getPlaylistReturnsRightPlaylist() {
        var expected = new PlaylistsDTO(List.of(new PlaylistDTO(1, "testPlaylist", false)), 10);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(playlistDAOMock.getAll()).thenReturn(List.of(new PlaylistDTO(1, "testPlaylist", false)));
        when(playlistDAOMock.calculateDuration()).thenReturn(10);
        var actual = sut.getAllPlaylists(token);

        assertEquals(expected.getPlaylists().get(0).getName(), actual.getPlaylists().get(0).getName());
    }

    @Test
    void updatePlaylistReturnsPlaylistsDTO() {
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(playlistDAOMock.getAll()).thenReturn(List.of(new PlaylistDTO()));
        var actual = sut.updatePlaylist(token, anyInt(), null);

        assertInstanceOf(PlaylistsDTO.class, actual);
    }

    @Test
    void addPlaylistReturnsPlaylistsDTO() {
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(playlistDAOMock.getAll()).thenReturn(List.of(new PlaylistDTO()));
        var actual = sut.addPlaylist(token, any());

        assertInstanceOf(PlaylistsDTO.class, actual);
    }

    @Test
    void deletePlaylistReturnsPlaylistsDTO() {
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(playlistDAOMock.getAll()).thenReturn(List.of(new PlaylistDTO()));
        var actual = sut.deletePlaylist(token, anyInt());

        assertInstanceOf(PlaylistsDTO.class, actual);
    }

    @Test
    void getPlaylistReturnsPlaylistsDTOWithCorrectDuration() {
        var playlistDuration = 10;
        var expected = new PlaylistsDTO(null, playlistDuration);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(playlistDAOMock.calculateDuration()).thenReturn(10);
        PlaylistsDTO actual = sut.getAllPlaylists(token);

        assertEquals(expected.getLength(), actual.getLength());
    }

}
