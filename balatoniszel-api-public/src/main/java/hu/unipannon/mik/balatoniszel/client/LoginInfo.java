package hu.unipannon.mik.balatoniszel.client;

public class LoginInfo {
    private String token;
    private String serverUrl;
    private LoginLevel level;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public LoginLevel getLevel() {
        return level;
    }

    public void setLevel(LoginLevel level) {
        this.level = level;
    }
}
