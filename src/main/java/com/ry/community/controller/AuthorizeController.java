package com.ry.community.controller;

import com.ry.community.dto.AccessTokenDTO;
import com.ry.community.dto.GithubUser;
import com.ry.community.mapper.UserMapper;
import com.ry.community.model.User;
import com.ry.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession session,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user!= null){
            Logger.getGlobal().info(user.toString());
            User modelUser = new User();
            String token = UUID.randomUUID().toString();
            modelUser.setAccountId(String.valueOf(user.getId()));
            modelUser.setName(user.getName());
            modelUser.setToken(token);
            modelUser.setGmtCreate(System.currentTimeMillis());
            modelUser.setGmtModify(modelUser.getGmtCreate());

            //插入到数据库中
            userMapper.insert(modelUser);

            //session.setAttribute("user",user);

            //将token加到cookie中
            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}
