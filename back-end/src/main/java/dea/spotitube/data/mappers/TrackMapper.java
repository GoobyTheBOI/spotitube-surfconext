package dea.spotitube.data.mappers;

import dea.spotitube.business.DataMapper;
import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.CCC.DTO.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackMapper implements DataMapper<List<TrackDTO>> {
    @Override
    public List<TrackDTO> toMapper(ResultSet result)  {
        List<TrackDTO> tracks = new ArrayList<>();

        try{
            while (result.next()) {
                var id = result.getInt("id");
                var title = result.getString("title");
                var performer = result.getString("performer");
                var duration = result.getInt("duration");
                var album = result.getString("album");
                var playcount = result.getInt("playcount");
                var publicationDate = result.getString("publicationDate");
                var description = result.getString("description");
                var offlineAvailable = result.getBoolean("offlineAvailable");
                var track = new TrackDTO(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
                tracks.add(track);
            }

            return tracks;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
