package dea.spotitube.business.services;

import dea.spotitube.CCC.exceptions.UnauthorizedException;
import dea.spotitube.data.DAO.LoginDAO;
import dea.spotitube.CCC.DTO.LoginDTO;
import dea.spotitube.CCC.DTO.LoginResponseDTO;
import jakarta.inject.Inject;

public class LoginService {
    private LoginDAO loginDAO;
    private AuthenticationService authenticationService;

    public LoginService() {
    }

    public LoginResponseDTO checkValidUser(LoginDTO loginDTO) {
        var user = loginDTO.getUser();
        var password = authenticationService.generateHashString(loginDTO.getPassword());
        loginDTO.setPassword(password);

        if (!loginDAO.checkIfUserExits(user)) {
            throw new UnauthorizedException();
        }

        loginDAO.generateToken(loginDTO);
        return loginDAO.checkValidUser(loginDTO);
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
