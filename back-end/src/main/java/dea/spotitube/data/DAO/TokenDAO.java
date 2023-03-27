package dea.spotitube.data.DAO;

import dea.spotitube.CCC.exceptions.InternalServerException;
import dea.spotitube.data.database.connection.DbConnection;

import java.sql.SQLException;


public class TokenDAO extends DbConnection {

    public boolean checkIfTokenIsValid(String token)  {
        try(var connection = this.createConnection()) {
            final String sql = "SELECT token FROM users WHERE token = ?";
            var stmt = connection
                    .prepareStatement(sql);
            stmt.setString(1, token);
            var result = stmt.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
