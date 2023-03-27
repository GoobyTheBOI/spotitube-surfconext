package dea.spotitube.data.mappers;

import dea.spotitube.business.DataMapper;
import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.CCC.DTO.LoginResponseDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginResponseMapper implements DataMapper<LoginResponseDTO> {
    @Override
    public LoginResponseDTO toMapper(ResultSet resultSet) {
        try {
            resultSet.next();
            return new LoginResponseDTO(resultSet.getString("token"), resultSet.getString("fullname"));
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
