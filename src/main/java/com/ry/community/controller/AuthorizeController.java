package com.ry.community.controller;

import com.ry.community.dto.AccessTokenDTO;
import com.ry.community.dto.GithubUser;
import com.ry.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

/**
 * @author ry
 * @since 2021-09-02 09:04
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${AccessToken.client_id}")
    private String clientId;
    @Value("${AccessToken.client_secret}")
    private String clientSecret;
    @Value("${AccessToken.redirect_uri}")
    private String redirectUri;



    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        System.out.println(accessTokenDTO);


        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user!= null){
            Logger.getGlobal().info(user.toString());
        }

        return "index";
    }
}
