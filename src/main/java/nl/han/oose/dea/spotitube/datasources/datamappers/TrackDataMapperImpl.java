package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDataMapperImpl implements DataMapper<TrackDTO> {
    @Override
    public TrackDTO toDTO(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            return new TrackDTO(
                    resultSet.getInt("track_id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("play_count"),
                    resultSet.getString("publication_date"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("offline_available")
            );
        }
        return null;
    }
}
