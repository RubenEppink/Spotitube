package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataMapperImpl implements DataMapper<UserDTO> {
    @Override
    public UserDTO toDTO(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            return new UserDTO(
                    resultSet.getString("token"),
                    resultSet.getString("username")
            );
        }

        return null;
    }
}
