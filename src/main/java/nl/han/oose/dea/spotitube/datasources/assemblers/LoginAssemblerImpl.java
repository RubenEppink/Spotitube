package nl.han.oose.dea.spotitube.datasources.assemblers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAssemblerImpl implements Assembler<LoginDTO> {
    @Override
    public LoginDTO toDTO(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            return new LoginDTO(
                    resultSet.getString("user_login"),
                    resultSet.getString("password")
            );
        }
        return null;
    }
}
