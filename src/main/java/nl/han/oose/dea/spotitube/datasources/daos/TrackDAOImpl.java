package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TracksDTO;
import nl.han.oose.dea.spotitube.datasources.datamappers.DataMapper;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAOImpl implements TrackDAO {
    private Connection connection;
    private DBConnection dbConnection;
    private DataMapper<TrackDTO> trackDataMapper;
    private DataMapper<TracksDTO> tracksDataMapper;

    @Inject
    public void setTracksDataMapper(DataMapper<TracksDTO> tracksDataMapper) {
        this.tracksDataMapper = tracksDataMapper;
    }

    @Inject
    public void setTrackDataMapper(DataMapper<TrackDTO> trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        makeConnection();
    }

    public void makeConnection() {
        try {
            connection = dbConnection.getConnection();
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public TrackDTO get(int trackId) {
        try {
            return trackDataMapper.toDTO(getTrackResultSet(trackId));
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public TracksDTO getAllNotInPlaylist(String token, int playlistId) {

        try {
            return tracksDataMapper.toDTO(getAllResultSet(token, playlistId, " T.track_id NOT IN(\n"));
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public TracksDTO getAllInPlaylist(String token, int playlistId) {

        try {
            return tracksDataMapper.toDTO(getAllResultSet(token, playlistId, " T.track_id IN(\n"));
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void delete(String token, int playlistId, int trackId) {
        try {
            executeDelete(playlistId, trackId);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void addToPlaylist(String token, int playlistId, TrackDTO trackDTO) {
        try {
            executeAddToPlaylist(playlistId, trackDTO);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }

    }

    @Override
    public void update(String token, int playlistId, TrackDTO trackDTO) {
        try {
            executeUpdate(trackDTO);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    private void executeUpdate(TrackDTO trackDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE track SET offline_available = ? WHERE track_id = ?");
        preparedStatement.setBoolean(1, trackDTO.isOfflineAvailable());
        preparedStatement.setInt(2, trackDTO.getId());
        preparedStatement.executeUpdate();
    }

    private ResultSet getTrackResultSet(int trackId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE track_id = ?");
        preparedStatement.setInt(1, trackId);
        return preparedStatement.executeQuery();
    }

    private void executeAddToPlaylist(int playlistId, TrackDTO trackDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO track_in_playlist(track_id, playlist_id) VALUES(?, ?)");
        preparedStatement.setInt(1, trackDTO.getId());
        preparedStatement.setInt(2, playlistId);
        preparedStatement.executeUpdate();
    }

    private void executeDelete(int playlistId, int trackId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM track_in_playlist " +
                "WHERE playlist_id = ? AND track_id = ?");
        preparedStatement.setInt(1, playlistId);
        preparedStatement.setInt(2, trackId);
        preparedStatement.executeUpdate();
    }

    private ResultSet getAllResultSet(String token, int playlistId, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track T\n" +
                "WHERE" +
                query +
                "\tSELECT TIP.track_id\n" +
                "    FROM playlist P JOIN track_in_playlist TIP \n" +
                "\t\t\t\t\t\tON p.playlist_id = tip.playlist_id\n" +
                "                        WHERE p.playlist_id = ? AND p.token = ?\n" +
                ") ");
        preparedStatement.setInt(1, playlistId);
        preparedStatement.setString(2, token);
        return preparedStatement.executeQuery();
    }
}
