package dea.spotitube.data.mappers;

import dea.spotitube.business.DataMapper;
import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper implements DataMapper<List<PlaylistDTO>> {
    @Override
    public List<PlaylistDTO> toMapper(ResultSet resultSet) {
        List<PlaylistDTO> playlists = new ArrayList<>();

        try {
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var owner = resultSet.getString("fullname") != null;
                var playlist = new PlaylistDTO(id, name, owner);
                playlists.add(playlist);
            }

            return playlists;

        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
