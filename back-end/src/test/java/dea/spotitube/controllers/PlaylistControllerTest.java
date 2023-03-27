package dea.spotitube.controllers;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.PlaylistService;
import dea.spotitube.business.services.TrackService;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import dea.spotitube.CCC.DTO.TrackDTO;
import dea.spotitube.presentatie.controllers.PlaylistController;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {
    public final PlaylistDTO PLAYLIST_DTO = new PlaylistDTO();
    private PlaylistController sut;
    private PlaylistService playlistServiceMock;
    private TrackService trackServiceMock;
    private final String token = "123";
    @BeforeEach
    void setup() {
        sut = new PlaylistController();
        playlistServiceMock = mock(PlaylistService.class);
        trackServiceMock = mock(TrackService.class);
        sut.setPlaylistService(playlistServiceMock);
        sut.setTrackService(trackServiceMock);
    }

    @Test
    void getAllPlayLists() {
        var expected = Response
                .status(Response.Status.OK)
                .build();
        var actual = sut.getAllPlayLists(token);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void getAllPlayListsWithEmptyToken() {
        when(playlistServiceMock.getAllPlaylists(any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.getAllPlayLists(null));
    }

    @Test
    void updatePlaylist(){
        var expected = Response
                .status(Response.Status.OK)
                .build();
        var actual = sut.updatePlaylist(1, token, PLAYLIST_DTO);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void updatePlaylistWithEmptyToken() {
        when(playlistServiceMock.updatePlaylist(any(), anyInt(), any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.updatePlaylist(2, null, PLAYLIST_DTO));
    }

    @Test
    void addPlaylist(){
        var expected = Response
                .status(Response.Status.CREATED)
                .build();
        var actual = sut.addPlaylist(token, PLAYLIST_DTO);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void addPlaylistWithEmptyToken() {
        when(playlistServiceMock.addPlaylist(any(), any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.addPlaylist(null, PLAYLIST_DTO));
    }

    @Test
    void deletePlaylist(){
        var expected = Response
                .status(Response.Status.OK)
                .build();
        var actual = sut.deletePlaylist(1, token);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void deletePlaylistWithEmptyToken() {
        when(playlistServiceMock.deletePlaylist(any(), anyInt())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.deletePlaylist(1, null));
    }

    @Test
    void getTracksForPlaylist(){
        var expected = Response
                .status(Response.Status.OK)
                .build();
        var actual = sut.getTracksForPlaylist(1, token);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void getTracksForPlaylistWithEmptyToken() {
        when(trackServiceMock.getTracksForPlaylist(any(), anyInt())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.getTracksForPlaylist(1, null));
    }

    @Test
    void deleteTrackFromPlaylist(){
        var expected = Response
                .status(Response.Status.OK)
                .build();
        var actual = sut.deleteTrack(1, 1, token);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void deleteTrackFromPlaylistWithEmptyToken() {
        when(trackServiceMock.deleteTrack(anyInt(), anyInt(), any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.deleteTrack(1, 1, null));
    }

    @Test
    void addTrackToPlaylist(){
        var expected = Response
                .status(Response.Status.CREATED)
                .build();
        var actual = sut.addTrack(1, new TrackDTO(), token);
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void addTrackToPlaylistWithEmptyToken() {
        when(trackServiceMock.addTrack(anyInt(), any(), any())).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () ->  sut.addTrack(1, new TrackDTO(), null));
    }
}
