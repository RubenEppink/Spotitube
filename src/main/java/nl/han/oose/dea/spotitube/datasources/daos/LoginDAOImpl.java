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
    private Assembler<LoginDTO> assembler;

    @Inject
    public void setAssembler(Assembler<LoginDTO> assembler) {
        this.assembler = assembler;
    }

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
            return assembler.toDTO(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();

        }

        return null;
    }
}
