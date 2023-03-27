package dea.spotitube.controllers;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.business.services.TrackService;
import dea.spotitube.presentatie.controllers.TracksController;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TracksControllerTest {
    private TracksController sut;
    private TrackService trackServiceMock;
    private final String token = "123";

    @BeforeEach
    void setup() {
        sut = new TracksController();
        trackServiceMock = mock(TrackService.class);
        sut.setTrackService(trackServiceMock);
    }

    @Test
    void getAllTracks() {
        var expected = Response
                .status(Response.Status.OK)
                .build();

        var actual = sut.getAllTracks(token);

        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void getAllTracksThrowsException() {
        when(trackServiceMock.getAllTracks(token)).thenThrow(new UnauthorizedException());
        assertThrows(UnauthorizedException.class, () -> sut.getAllTracks(token));
    }
}
