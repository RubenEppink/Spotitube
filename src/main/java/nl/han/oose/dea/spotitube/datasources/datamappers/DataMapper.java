package nl.han.oose.dea.spotitube.datasources.datamappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
    T toDTO(ResultSet resultSet) throws SQLException;
}
