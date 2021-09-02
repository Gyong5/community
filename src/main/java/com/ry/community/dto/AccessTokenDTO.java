package com.ry.community.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ry
 * @since 2021-09-02 00:25
 **/
public class AccessTokenDTO {
    private String code;
    private String state;

    @Value("${AccessToken.client_id}")
    private String client_id;
    @Value("${AccessToken.client_secret}")
    private String client_secret;
    @Value("${AccessToken.redirect_uri}")
    private String redirect_uri;

    public AccessTokenDTO(String client_id, String client_secret, String code, String redirect_uri, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
        this.state = state;
    }

    public AccessTokenDTO() {
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "code='" + code + '\'' +
                ", state='" + state + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                '}';
    }
}
