package nl.han.oose.dea.spotitube.controllers;

import nl.han.oose.dea.spotitube.domains.interfaces.TrackDomain;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {
    TrackDomain trackDomain;

    @Inject
    public void setTrackDomain(TrackDomain trackDomain) {
        this.trackDomain = trackDomain;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        return Response.status(200).entity(trackDomain.getAllTracksNotInPlaylist(token, playlistId)).build();
    }
}
