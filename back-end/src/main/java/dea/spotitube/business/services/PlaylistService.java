package dea.spotitube.business.services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.data.DAO.PlaylistDAO;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import dea.spotitube.CCC.DTO.PlaylistsDTO;
import jakarta.inject.Inject;

public class PlaylistService {
    private PlaylistDAO playlistDAO;
    private AuthenticationService isTokenValid;

    public PlaylistsDTO getAllPlaylists(String token) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            var duration = playlistDAO.calculateDuration();
            return new PlaylistsDTO(playlistDAO.getAll(), duration);
        }

        throw new UnauthorizedException();
    }

    public PlaylistsDTO updatePlaylist(String token, int id, PlaylistDTO playlistDTO) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            playlistDAO.update(id, playlistDTO);

            return getAllPlaylists(token);
        }

        throw new UnauthorizedException();
    }

    public PlaylistsDTO addPlaylist(String token, PlaylistDTO playlistDTO) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            playlistDAO.create(playlistDTO, token);

            return getAllPlaylists(token);
        }

        throw new UnauthorizedException();
    }

    public PlaylistsDTO deletePlaylist(String token, int id) {
        if (isTokenValid.checkIfTokenIsValid(token)) {
            playlistDAO.delete(id);

            return getAllPlaylists(token);
        }

        throw new UnauthorizedException();
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
    @Inject
    public void setTokenValid(AuthenticationService tokenValid) {
        isTokenValid = tokenValid;
    }
}
