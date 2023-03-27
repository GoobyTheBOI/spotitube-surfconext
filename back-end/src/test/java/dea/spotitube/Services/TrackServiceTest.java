package dea.spotitube.Services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.AuthenticationService;
import dea.spotitube.business.services.TrackService;
import dea.spotitube.data.DAO.TrackDAO;
import dea.spotitube.CCC.DTO.TrackDTO;
import dea.spotitube.CCC.DTO.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackServiceTest {
    private TrackService sut;
    private AuthenticationService authenticationServiceMock;
    private TrackDAO trackDAOMock;
    private final String token = "123";
    private final int trackIdTest = 1;
    private final int playlistIdTest = 1;

    private TrackDTO trackDTO;
    List<TrackDTO> tracks;

    @BeforeEach
    public void setup()  {
        sut = new TrackService();
        trackDTO = new TrackDTO();
        authenticationServiceMock = mock(AuthenticationService.class);
        trackDAOMock = mock(TrackDAO.class);
        sut.setIsTokenValid(authenticationServiceMock);
        sut.setTrackDAO(trackDAOMock);
        tracks = new ArrayList<>();
    }


    @Test
    void getTracksForPlaylistThrowsException() {
        assertThrows(UnauthorizedException.class, () -> sut.getTracksForPlaylist(token, playlistIdTest));
    }

    @Test
    void deleteTrackThrowsException() {
        assertThrows(UnauthorizedException.class, () -> sut.deleteTrack(trackIdTest, playlistIdTest, token));
    }

    @Test
    void addTrackThrowsException() {
        assertThrows(UnauthorizedException.class, () -> sut.addTrack(trackIdTest, token, trackDTO));
    }

    @Test
    void getAllTracksThrowsException() {
        assertThrows(UnauthorizedException.class, () -> sut.getAllTracks(token));
    }

    @Test
    void getTracksForPlaylist() {
        tracks.add(new TrackDTO(1, "test", "test", 1, "test", 1,"test", "test",false));
        var expected = new TracksDTO(tracks);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(trackDAOMock.getTracksForPlaylist(playlistIdTest)).thenReturn(new TracksDTO(tracks));

        var actual = sut.getTracksForPlaylist(token, playlistIdTest);

        assertEquals(expected.getTracks().contains("test"), actual.getTracks().contains("test"));
    }

    @Test
    void deleteTrack() {
        var expected = new TracksDTO(tracks);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(trackDAOMock.getTracksForPlaylist(playlistIdTest)).thenReturn(new TracksDTO(tracks));
        var actual = sut.deleteTrack(trackIdTest, playlistIdTest, token);

        assertEquals(expected.getTracks().size(), actual.getTracks().size());
    }

    @Test
    void addTrack() {
        tracks.add(new TrackDTO(1, "test", "test", 1, "test", 1,"test", "test",false));
        var expected = new TracksDTO(tracks);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(trackDAOMock.getTracksForPlaylist(playlistIdTest)).thenReturn(new TracksDTO(tracks));
        var actual = sut.addTrack(trackIdTest, token, trackDTO);

        assertEquals(expected.getTracks().size(), actual.getTracks().size());
    }

    @Test
    void getAllTracks() {
        tracks.add(new TrackDTO(1, "test", "test", 1, "test", 1,"test", "test",false));
        var expected = new TracksDTO(tracks);
        when(authenticationServiceMock.checkIfTokenIsValid(token)).thenReturn(true);
        when(trackDAOMock.getAll()).thenReturn(tracks);
        var actual = sut.getAllTracks(token);

        assertEquals(expected.getTracks().size(), actual.getTracks().size());
    }
}
