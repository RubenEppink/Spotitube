package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistsDTO;
import nl.han.oose.dea.spotitube.domains.PlaylistDomain;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    PlaylistDomain playlistDomain;

    @Inject
    public void setPlaylistDomain(PlaylistDomain playlistDomain) {
        this.playlistDomain = playlistDomain;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        return Response.status(200).entity(playlistDomain.getAll(token)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.status(200).entity(playlistDomain.delete(id, token)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        return Response.status(201).entity(playlistDomain.create(token, playlistDTO)).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO){
        return Response.status(200).entity(playlistDomain.update(token, id, playlistDTO)).build();
    }
}
