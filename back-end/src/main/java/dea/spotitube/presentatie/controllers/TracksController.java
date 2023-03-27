package dea.spotitube.presentatie.controllers;

import dea.spotitube.business.services.TrackService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.OK)
                .entity(trackService.getAllTracks(token))
                .build();
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
