package dea.spotitube.CCC.DTO;

public class LoginDTO {
    private String user;

    private String password;
    public LoginDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public LoginDTO() {
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
