package dea.spotitube.data.database.connection;

import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
    private final Properties properties = new Properties();
    public DatabaseProperties() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String connectionString()
    {
        return properties.getProperty("db.connectionString");
    }

    public String getDriver(){
        return properties.getProperty("db.driver");
    }
}
