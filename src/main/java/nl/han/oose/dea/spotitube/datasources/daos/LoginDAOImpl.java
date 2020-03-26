package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.datasources.datamappers.DataMapper;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    private DBConnection dbConnection;
    private DataMapper<LoginDTO> loginDataMapper;
    private Connection connection;


    @Inject
    public void setLoginDataMapper(DataMapper<LoginDTO> loginDataMapper) {
        this.loginDataMapper = loginDataMapper;
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
    public LoginDTO read(String userLogin) {

        try {
            return loginDataMapper.toDTO( getLoginResultSet(userLogin));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();

        }

        return null;
    }

    private ResultSet getLoginResultSet(String userLogin) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logi" +
                "n WHERE user_login = ?");
        preparedStatement.setString(1, userLogin);

        return preparedStatement.executeQuery();
    }
}
