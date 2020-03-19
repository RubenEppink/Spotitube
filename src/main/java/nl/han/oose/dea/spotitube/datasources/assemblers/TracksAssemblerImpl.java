package nl.han.oose.dea.spotitube.datasources.assemblers;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TracksAssemblerImpl implements Assembler<TracksDTO> {
    @Override
    public TracksDTO toDTO(ResultSet resultSet) throws SQLException {
        TracksDTO tracksDTO = new TracksDTO();
        while (resultSet.next()) {
            tracksDTO.getTracks().add(new TrackDTO(
                    resultSet.getInt("track_id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("play_count"),
                    resultSet.getString("publication_date"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("offline_available")
            ));
        }

        return tracksDTO;
    }
}
