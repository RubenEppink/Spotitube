package nl.han.oose.dea.spotitube.datasources.daos;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.datasources.assemblers.Assembler;
import nl.han.oose.dea.spotitube.datasources.connections.DBConnection;
import nl.han.oose.dea.spotitube.datasources.daos.interfaces.PlaylistDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {
    private Connection connection;
    private DBConnection dbConnection;
    private Assembler<PlaylistsDTO> assembler;

    @Inject
    public void setAssembler(Assembler<PlaylistsDTO> assembler) {
        this.assembler = assembler;
    }

    @Inject
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public PlaylistsDTO getAll(String token) {

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist WHERE token = ?");
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();

            return assembler.toDTO(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return null;
    }

    @Override
    public void delete(int playlistId, String token) {
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlist WHERE playlist_id = ? AND token = ?");
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void create(String token, PlaylistDTO playlistDTO) {
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO spotitube.playlist(token, name, owner) VALUES(?, ?, true)");
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, playlistDTO.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
    }

    @Override
    public void update(String token, int playlistId, PlaylistDTO playlistDTO) {
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE playlist_id = ? AND token = ?");
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setInt(2, playlistId);
            preparedStatement.setString(3, token);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
    }
}

