package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;
import nl.han.oose.dea.spotitube.datasources.assemblers.Assembler;
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
    private Assembler<UserDTO> userAssembler;
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    @Inject
    public void setUserAssembler(Assembler<UserDTO> userAssembler) {
        this.userAssembler = userAssembler;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public UserDTO read(String userLogin) {
        try {
            return userAssembler.toDTO(getUserResultSet(userLogin));
        } catch (SQLException e) {
            LOGGER.warning(e.getSQLState());
        } finally {
            dbConnection.closeConnection(connection);
        }
        return null;
    }

    private ResultSet getUserResultSet(String userLogin) throws SQLException {
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_login = ?");
        preparedStatement.setString(1, userLogin);
        return preparedStatement.executeQuery();
    }
}
