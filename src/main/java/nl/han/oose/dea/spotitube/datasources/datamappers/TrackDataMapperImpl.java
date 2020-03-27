package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;

import javax.sound.midi.Track;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDataMapperImpl implements DataMapper<TrackDTO> {
    @Override
    public TrackDTO toDTO(ResultSet resultSet) throws SQLException {
        TrackDTO trackDTO = new TrackDTO();
        while (resultSet.next()) {
            trackDTO = new TrackDTO(
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
        return trackDTO;
    }
}
