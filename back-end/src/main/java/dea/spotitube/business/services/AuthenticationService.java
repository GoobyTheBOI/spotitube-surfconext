package dea.spotitube.business.services;

import dea.spotitube.business.StringEncoder;
import dea.spotitube.data.DAO.TokenDAO;
import jakarta.inject.Inject;

public class AuthenticationService {
    private TokenDAO tokenDAO;
    private StringEncoder stringEncoder;

    public boolean checkIfTokenIsValid(String token) {
        return tokenDAO.checkIfTokenIsValid(token);
    }

    public String generateHashString(String input) {
        return stringEncoder.stringToHash(input);
    }

    @Inject
    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Inject
    public void setStringEncoder(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }
}
