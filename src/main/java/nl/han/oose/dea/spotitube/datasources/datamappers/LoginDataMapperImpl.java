package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.LoginDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDataMapperImpl implements DataMapper<LoginDTO> {
    @Override
    public LoginDTO toDTO(ResultSet resultSet) throws SQLException {
    LoginDTO loginDTO = new LoginDTO();

        while (resultSet.next()) {
         loginDTO = new LoginDTO(
                    resultSet.getString("user_login"),
                    resultSet.getString("password")
            );
        }
        return loginDTO;
    }
}
