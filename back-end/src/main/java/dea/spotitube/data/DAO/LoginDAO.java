package dea.spotitube.data.DAO;

import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.data.mappers.LoginResponseMapper;
import dea.spotitube.data.database.connection.DbConnection;
import dea.spotitube.CCC.DTO.LoginDTO;
import dea.spotitube.CCC.DTO.LoginResponseDTO;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO extends DbConnection {
    private LoginResponseMapper loginResponseMapper;

    public LoginResponseDTO checkValidUser(LoginDTO loginDTO) {
        try {
            return loginResponseMapper.toMapper(getUser(loginDTO));
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public void generateToken(LoginDTO loginDTO) {
        try(var connection = this.createConnection()) {
            var sql = "UPDATE users " +
                    "SET token =  (SELECT UUID()) " +
                    "WHERE username = ?";
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, loginDTO.getUser());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public boolean checkIfUserExits(String user)  {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT * FROM users WHERE username  = ?";
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, user);
            var result = stmt.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public ResultSet getUser(LoginDTO loginDTO) {
        try {
            var connection = this.createConnection();
            final String sql = "SELECT * FROM users WHERE username = ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, loginDTO.getUser());

            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public ResultSet getUserByToken(String token) {
        try {
            var connection = this.createConnection();
            final String sql = "SELECT token, fullname FROM users WHERE token = ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, token);


            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Inject
    public void setLoginResponseMapper(LoginResponseMapper loginResponseMapper) {
        this.loginResponseMapper = loginResponseMapper;
    }
}
