package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.domains.PlaylistDomain;
import nl.han.oose.dea.spotitube.domains.TrackDomain;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    PlaylistDomain playlistDomain;
    TrackDomain trackDomain;

    @Inject
    public void setTrackDomain(TrackDomain trackDomain) {
        this.trackDomain = trackDomain;
    }

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
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO) {
        return Response.status(200).entity(playlistDomain.update(token, id, playlistDTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/tracks")
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.status(200).entity(trackDomain.getAllTracksInPlaylist(token, id)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token,
                                            @PathParam("playlistId") int playlistId,
                                            @PathParam("trackId") int trackId) {
        return Response.status(200).entity(trackDomain.deleteTrackFromPlaylist(token, playlistId, trackId)).build();
    }


}
