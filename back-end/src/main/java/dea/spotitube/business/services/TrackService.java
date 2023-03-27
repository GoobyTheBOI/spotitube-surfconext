package dea.spotitube.business.services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.data.DAO.TrackDAO;
import dea.spotitube.CCC.DTO.TrackDTO;
import dea.spotitube.CCC.DTO.TracksDTO;
import jakarta.inject.Inject;

public class TrackService {
    private TrackDAO trackDAO;
    private AuthenticationService isTokenValid;



    public TracksDTO getTracksForPlaylist(String token, int id) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            return trackDAO.getTracksForPlaylist(id);
        }

        throw new UnauthorizedException();
    }

    public TracksDTO deleteTrack(int playlistId, int trackId, String token) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            trackDAO.delete(playlistId, trackId);

            return getTracksForPlaylist(token, playlistId);
        }

        throw new UnauthorizedException();
    }

    public TracksDTO addTrack(int playlistId, String token, TrackDTO track) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            trackDAO.create(playlistId, track);

            return getTracksForPlaylist(token, playlistId);
        }

        throw new UnauthorizedException();
    }

    public TracksDTO getAllTracks(String token) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            var result = trackDAO.getAll();

            return new TracksDTO(result);
        }

        throw new UnauthorizedException();
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setIsTokenValid(AuthenticationService isTokenValid) {
        this.isTokenValid = isTokenValid;
    }
}
