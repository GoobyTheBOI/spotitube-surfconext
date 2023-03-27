package dea.spotitube.presentatie.controllers;

import dea.spotitube.CCC.DTO.LoginDTO;
import dea.spotitube.business.services.LoginService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    private LoginService loginService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        return Response
                .status(Response.Status.OK)
                .entity(loginService.checkValidUser(loginDTO))
                .build();
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
