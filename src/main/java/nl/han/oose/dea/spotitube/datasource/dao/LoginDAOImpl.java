package nl.han.oose.dea.spotitube.datasource.dao;

import nl.han.oose.dea.spotitube.controller.dto.LoginDTO;
import nl.han.oose.dea.spotitube.datasource.connection.DBConnection;
import nl.han.oose.dea.spotitube.datasource.dao.interfaces.LoginDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    DBConnection dbConnection;

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public LoginDTO read(String userLogin) {

        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logi" +
                    "n WHERE user_login = ?");
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();

            //kan dit ook anders?
            while (resultSet.next()) {
                return new LoginDTO(
                        resultSet.getString("user_login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return null;
    }
}
