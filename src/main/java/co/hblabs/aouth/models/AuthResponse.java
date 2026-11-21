package co.hblabs.aouth.models;

// POJO
public class AuthResponse {
    public String accessToken;
    public boolean isLoggedIn;

    public AuthResponse(String accessToken, boolean isLoggedIn) {
        this.setAccessToken(accessToken);
        this.setIsLoggedIn(isLoggedIn);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
