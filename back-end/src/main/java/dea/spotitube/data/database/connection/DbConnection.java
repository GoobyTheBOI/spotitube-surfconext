package dea.spotitube.data.database.connection;

import dea.spotitube.CCC.exceptions.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public Connection createConnection() {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Class.forName(properties.getDriver());
            return DriverManager.getConnection(properties.connectionString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException();
        }
    }
}
