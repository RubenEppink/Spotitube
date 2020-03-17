package nl.han.oose.dea.spotitube.datasources.dao;

import nl.han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dto.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.connection.DBConnection;
import nl.han.oose.dea.spotitube.datasources.dao.interfaces.PlaylistDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {
    DBConnection dbConnection;

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public PlaylistsDTO getAll() {
        List<PlaylistDTO> playlists = new ArrayList<>();

        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlists.add(new PlaylistDTO(
                        resultSet.getInt("playlist_id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("owner"),
                        new ArrayList<>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return new PlaylistsDTO(playlists, 7549);
    }
}

