package dea.spotitube.data.DAO;

import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.data.mappers.TrackMapper;
import dea.spotitube.data.database.connection.DbConnection;
import dea.spotitube.CCC.DTO.TrackDTO;
import dea.spotitube.CCC.DTO.TracksDTO;

import java.sql.SQLException;
import java.util.List;

public class TrackDAO extends DbConnection {

    public TracksDTO getTracksForPlaylist(int id) {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT * FROM tracks " +
                    "INNER JOIN playlists ON tracks.playlist_id = playlists.id" +
                    " WHERE playlists.id=" + id;
            var result = connection
                    .prepareStatement(sql)
                    .executeQuery();
            var tracks = new TrackMapper().toMapper(result);

            return new TracksDTO(tracks);
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public List<TrackDTO> getAll() {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT * FROM tracks";
            var result = connection
                    .prepareStatement(sql)
                    .executeQuery();

            return new TrackMapper().toMapper(result);
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public void create(int playlistId, TrackDTO track) {
        try(var connection = this.createConnection()) {
            String sql = "UPDATE tracks SET playlist_id= ?, offlineAvailable = ? WHERE id= ?";
            var stmt = connection
                    .prepareStatement(sql);

            stmt.setInt(1, playlistId);
            stmt.setBoolean(2, track.isOfflineAvailable());
            stmt.setInt(3, track.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public void delete(int playlistId, int trackId) {
        try(var connection = this.createConnection()) {
            final String sql = "UPDATE tracks SET playlist_id = NULL WHERE playlist_id= ? AND id= ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setInt(1, playlistId);
            stmt.setInt(2, trackId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
