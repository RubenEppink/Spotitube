package nl.han.oose.dea.spotitube.datasources.assemblers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Assembler<T> {
    T toDTO(ResultSet resultSet) throws SQLException;
}
