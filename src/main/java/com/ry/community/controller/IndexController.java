package com.ry.community.controller;

import com.ry.community.mapper.UserMapper;
import com.ry.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ry
 * @since 2021-08-31 16:20
 **/
@Controller
public class IndexController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                User user = userMapper.findByToken(cookie.getValue());
                if (user != null){
                    //user不为空,将信息加到session中
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        return "index";
    }
}
