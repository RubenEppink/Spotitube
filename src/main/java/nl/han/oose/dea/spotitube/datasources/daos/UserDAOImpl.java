package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.datasources.datamappers.DataMapper;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.UserDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private DBConnection dbConnection;
    private DataMapper<UserDTO> userDataMapper;
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    @Inject
    public void setUserDataMapper(DataMapper<UserDTO> userDataMapper) {
        this.userDataMapper = userDataMapper;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void makeConnection() {
        try {
            connection = dbConnection.getConnection();
        } catch (SQLException e) {

        }
    }

    @Override
    public UserDTO read(String userLogin) {
        try {
            return userDataMapper.toDTO(getUserResultSet(userLogin));
        } catch (SQLException e) {
            LOGGER.warning(e.getSQLState());
        } finally {
            dbConnection.closeConnection();
        }
        return null;
    }

    private ResultSet getUserResultSet(String userLogin) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_login = ?");
        preparedStatement.setString(1, userLogin);
        return preparedStatement.executeQuery();
    }
}
