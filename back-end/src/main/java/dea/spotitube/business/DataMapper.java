package dea.spotitube.business;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<DTO> {
    DTO toMapper(ResultSet resultSet) throws SQLException;
}
