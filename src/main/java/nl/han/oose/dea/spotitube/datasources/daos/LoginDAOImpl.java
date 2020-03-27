package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;
import nl.han.oose.dea.spotitube.datasources.datamappers.DataMapper;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    private DBConnection dbConnection;
    private DataMapper<LoginDTO> loginDataMapper;
    private Connection connection;
    private PreparedStatement preparedStatement;


    @Inject
    public void setLoginDataMapper(DataMapper<LoginDTO> loginDataMapper) {
        this.loginDataMapper = loginDataMapper;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        makeConnection();
    }

    public void makeConnection() {
        try {
            connection = dbConnection.getConnection();
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public LoginDTO getLoginInfo(String userLogin) {

        try {
            return loginDataMapper.toDTO(getLoginResultSet(userLogin));
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    private ResultSet getLoginResultSet(String userLogin) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM logi" +
                "n WHERE user_login = ?");
        preparedStatement.setString(1, userLogin);

        return preparedStatement.executeQuery();
    }
}
