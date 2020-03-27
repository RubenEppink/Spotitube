package nl.han.oose.dea.spotitube.datasources.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
     Connection getConnection() throws SQLException;
     void closeConnection();
}
