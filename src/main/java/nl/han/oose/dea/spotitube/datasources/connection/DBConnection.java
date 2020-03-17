package nl.han.oose.dea.spotitube.datasources.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
    public Connection getConnection() throws SQLException;
    public void closeConnection();
}
