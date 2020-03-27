package nl.han.oose.dea.spotitube.datasources.connections;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDBConnection implements DBConnection {
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.getConnectionString());
    }

    @Override
    public void closeConnection() {
        //connection.close();
        //preparedStatement.close();
    }
}
