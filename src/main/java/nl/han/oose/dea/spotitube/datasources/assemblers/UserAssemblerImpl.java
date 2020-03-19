package nl.han.oose.dea.spotitube.datasources.assemblers;

import nl.han.oose.dea.spotitube.controllers.dtos.UserDTO;

import java.sql.ResultSet;

public class UserAssemblerImpl implements Assembler<UserDTO> {
    @Override
    public UserDTO toDTO(ResultSet resultSet) {
        return null;
    }
}
