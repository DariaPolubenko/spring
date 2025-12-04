package ru.daria.dto.response.authentication;

public class ResponseAuthentication {
    private String accessToken;
    private String refreshToken;
    private String token;

    public ResponseAuthentication(String accessToken, String refreshToken, String token) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.token = token;
    }

    public ResponseAuthentication() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
