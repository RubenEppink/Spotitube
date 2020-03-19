package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.controllers.dtos.PlaylistDTO;
import nl.han.oose.dea.spotitube.controllers.dtos.TrackDTO;
import nl.han.oose.dea.spotitube.domains.interfaces.PlaylistDomain;
import nl.han.oose.dea.spotitube.domains.interfaces.TrackDomain;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    private PlaylistDomain playlistDomain;
    private TrackDomain trackDomain;

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
    @Path("/{playlistId}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        return Response.status(200).entity(playlistDomain.delete(playlistId, token)).build();
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
    @Path("/{playlistId}")
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, PlaylistDTO playlistDTO) {
        return Response.status(200).entity(playlistDomain.update(token, playlistId, playlistDTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}/tracks")
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        return Response.status(200).entity(trackDomain.getAllTracksInPlaylist(token, playlistId)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token,
                                            @PathParam("playlistId") int playlistId,
                                            @PathParam("trackId") int trackId) {
        return Response.status(200).entity(trackDomain.deleteTrackFromPlaylist(token, playlistId, trackId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}/tracks")
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token,
                                            @PathParam("playlistId") int playlistId,
                                            TrackDTO trackDTO) {
        return Response.status(201).entity(trackDomain.addTrackToPlaylist(token, playlistId, trackDTO)).build();
    }
}
