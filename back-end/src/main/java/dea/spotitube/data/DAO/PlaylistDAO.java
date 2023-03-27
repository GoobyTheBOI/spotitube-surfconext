package dea.spotitube.data.DAO;

import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.CCC.exceptions.NotFoundException;
import dea.spotitube.data.mappers.PlaylistMapper;
import dea.spotitube.data.mappers.TrackMapper;
import dea.spotitube.data.database.connection.DbConnection;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import dea.spotitube.CCC.DTO.TrackDTO;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DbConnection {
    private LoginDAO loginDAO;
    public int calculateDuration() {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT tracks.id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable FROM tracks " +
                    "INNER JOIN playlists ON tracks.playlist_id = playlists.id";
            var result = connection
                    .prepareStatement(sql)
                    .executeQuery();

            var tracks = new TrackMapper().toMapper(result);
            int duration = 0;
            for (TrackDTO track : tracks) {
                duration += track.getDuration();
            }

            return duration;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public List<PlaylistDTO> getAll() {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT playlists.id, name, fullname FROM playlists LEFT JOIN users ON playlists.owner = users.fullname";
            var result = connection
                    .prepareStatement(sql)
                    .executeQuery();

            return new PlaylistMapper().toMapper(result);
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public void create(PlaylistDTO playlist, String token) {
        try {
            var connection = this.createConnection();
            String owner = getOwner(token);
            final String sql = "INSERT INTO playlists (name, owner) VALUES (?, ?)";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, playlist.getName());
            stmt.setString(2, owner);

            stmt.execute();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public String getOwner(String token) throws SQLException {
        try {
            var result = loginDAO.getUserByToken(token);
            if (!result.next()) {
                throw new NotFoundException();
            }

            return result.getString("fullname");
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public void update(int id, PlaylistDTO playlist) {
        try(var connection = this.createConnection()) {
            final String sql = "UPDATE playlists SET name = ? WHERE id = ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public void delete(int id) {
        try(var connection = this.createConnection()) {
            final String sql = "DELETE FROM playlists WHERE id = ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
