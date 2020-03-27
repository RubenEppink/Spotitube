package nl.han.oose.dea.spotitube.datasources.datamappers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDataMapperImpl implements DataMapper<PlaylistsDTO> {

    @Override
    public PlaylistsDTO toDTO(ResultSet resultSet) throws SQLException {
        List<PlaylistDTO> playlists = new ArrayList<>();

        while (resultSet.next()) {
            playlists.add(new PlaylistDTO(
                    resultSet.getInt("playlist_id"),
                    resultSet.getString("name"),
                    resultSet.getBoolean("owner"),
                    new ArrayList<>()));
        }
        return new PlaylistsDTO(playlists, 7549);
    }
}
