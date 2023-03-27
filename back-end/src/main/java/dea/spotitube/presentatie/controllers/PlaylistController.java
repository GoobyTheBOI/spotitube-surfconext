package dea.spotitube.presentatie.controllers;

import dea.spotitube.business.services.PlaylistService;
import dea.spotitube.business.services.TrackService;
import dea.spotitube.CCC.DTO.PlaylistDTO;
import dea.spotitube.CCC.DTO.TrackDTO;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService;
    private TrackService trackService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlayLists(@NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.OK)
                .entity(playlistService.getAllPlaylists(token))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@NotNull @PathParam("id") int id,
                               @NotNull @QueryParam("token") String token,
                               PlaylistDTO playlistDTO) {
        return Response
                .status(Response.Status.OK)
                .entity(playlistService.updatePlaylist(token, id, playlistDTO))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@NotNull @QueryParam("token") String token,
                            PlaylistDTO playlistDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(playlistService.addPlaylist(token, playlistDTO))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@NotNull @PathParam("id") int id,
                               @NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.OK)
                .entity(playlistService.deletePlaylist(token, id))
                .build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(@NotNull @PathParam("id") int id, @NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.OK)
                .entity(trackService.getTracksForPlaylist(token, id))
                .build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@NotNull @PathParam("id") int playlistId, @NotNull @PathParam("trackId") int trackId, @NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.OK)
                .entity(trackService.deleteTrack(playlistId, trackId, token))
                .build();
    }

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrack(@NotNull @PathParam("id") int playlistId, TrackDTO track, @NotNull @QueryParam("token") String token) {
        return Response
                .status(Response.Status.CREATED)
                .entity(trackService.addTrack(playlistId, token, track))
                .build();
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistServiceMock) {
        this.playlistService = playlistServiceMock;
    }
    @Inject
    public void setTrackService(TrackService trackServiceMock) {
        this.trackService = trackServiceMock;
    }
}
