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
    Connection connection;
    DBConnection dbConnection;

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public PlaylistsDTO getAll(String token) {
        List<PlaylistDTO> playlists = new ArrayList<>();

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist WHERE token = ?");
            preparedStatement.setString(1, token);
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
        //TODO calculate length (in java or sql?)
        return new PlaylistsDTO(playlists, 7549);
    }

    @Override
    public void delete(int id, String token) {
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlist WHERE playlist_id = ? AND token = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
    }
}

