package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;
import nl.han.oose.dea.spotitube.datasources.assemblers.Assembler;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.LoginDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    private DBConnection dbConnection;
    private Assembler<LoginDTO> loginAssembler;

    @Inject
    public void setLoginAssembler(Assembler<LoginDTO> loginAssembler) {
        this.loginAssembler = loginAssembler;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public LoginDTO read(String userLogin) {

        try {
            return loginAssembler.toDTO( getLoginResultSet(userLogin));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);

        }

        return null;
    }

    private ResultSet getLoginResultSet(String userLogin) throws SQLException {
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logi" +
                "n WHERE user_login = ?");
        preparedStatement.setString(1, userLogin);

        return preparedStatement.executeQuery();
    }
}
