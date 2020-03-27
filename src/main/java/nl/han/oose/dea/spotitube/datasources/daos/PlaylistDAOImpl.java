package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.datamappers.DataMapper;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDAOImpl implements PlaylistDAO {
    private Connection connection;
    private DBConnection dbConnection;
    private DataMapper<PlaylistsDTO> playlistDataMapper;

    @Inject
    public void setPlaylistDataMapper(DataMapper<PlaylistsDTO> playlistDataMapper) {
        this.playlistDataMapper = playlistDataMapper;
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
    public PlaylistsDTO getAll(String token) {

        try {
            return playlistDataMapper.toDTO(getAllResultSet(token));
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void delete(int playlistId, String token) {
        try {
            executeDelete(playlistId, token);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void create(String token, PlaylistDTO playlistDTO) {
        try {
            executeCreate(token, playlistDTO);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void update(String token, int playlistId, PlaylistDTO playlistDTO) {
        try {
            executeUpdate(token, playlistId, playlistDTO);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        } finally {
            dbConnection.closeConnection();
        }
    }

    private void executeUpdate(String token, int playlistId, PlaylistDTO playlistDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE playlist_id = ? AND token = ?");
        preparedStatement.setString(1, playlistDTO.getName());
        preparedStatement.setInt(2, playlistId);
        preparedStatement.setString(3, token);
        preparedStatement.executeUpdate();
    }

    private void executeCreate(String token, PlaylistDTO playlistDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO spotitube.playlist(token, name, owner) VALUES(?, ?, true)");
        preparedStatement.setString(1, token);
        preparedStatement.setString(2, playlistDTO.getName());
        preparedStatement.executeUpdate();
    }

    private void executeDelete(int playlistId, String token) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlist WHERE playlist_id = ? AND token = ?");
        preparedStatement.setInt(1, playlistId);
        preparedStatement.setString(2, token);
        preparedStatement.executeUpdate();
    }

    private ResultSet getAllResultSet(String token) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist WHERE token = ?");
        preparedStatement.setString(1, token);
        return preparedStatement.executeQuery();
    }
}

