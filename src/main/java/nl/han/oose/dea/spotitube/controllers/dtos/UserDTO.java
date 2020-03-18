package nl.han.oose.dea.spotitube.controllers.dtos;

public class UserDTO {
    String token;
    String user;
    
    public UserDTO(String token, String username) {
        this.token = token;
        this.user = username;

    }

    public UserDTO() {
    }


    public String getUser() {
        return user;
    }

    public void getUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
