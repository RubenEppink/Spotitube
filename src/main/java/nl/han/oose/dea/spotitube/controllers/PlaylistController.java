package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.datasources.dao.interfaces.PlaylistDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    PlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token) {
        return Response.status(200).entity(playlistDAO.getAll()).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.status(200).entity(playlistDAO.delete(id)).build();
    }
}
