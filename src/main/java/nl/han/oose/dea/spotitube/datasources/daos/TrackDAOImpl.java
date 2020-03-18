package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAOImpl implements TrackDAO {
    Connection connection;
    DBConnection dbConnection;

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public TracksDTO getAllNotInPlaylist(String token, int playlistId) {
       TracksDTO tracksDTO = new TracksDTO();

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track T\n" +
                    "WHERE" +
                    " T.track_id NOT IN(\n" +
                    "\tSELECT TIP.track_id\n" +
                    "    FROM playlist P JOIN track_in_playlist TIP \n" +
                    "\t\t\t\t\t\tON p.playlist_id = tip.playlist_id\n" +
                    "                        WHERE p.playlist_id = ? AND p.token = ?\n" +
                    ") ");
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, token);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }

        return tracksDTO;
    }

    @Override
    public TracksDTO getAllInPlaylist(String token, int playlistId) {
        TracksDTO tracksDTO = new TracksDTO();

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track T\n" +
                    "WHERE" +
                    " T.track_id IN(\n" +
                    "\tSELECT TIP.track_id\n" +
                    "    FROM playlist P JOIN track_in_playlist TIP \n" +
                    "\t\t\t\t\t\tON p.playlist_id = tip.playlist_id\n" +
                    "                        WHERE p.playlist_id = ? AND p.token = ?\n" +
                    ") ");
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, token);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }

        return tracksDTO;
    }


    @Override
    public void delete(int id, String token) {

    }

    @Override
    public void create(String token, TrackDTO trackDTO) {

    }

    @Override
    public void update(String token, int id, TrackDTO trackDTO) {

    }
}
