package com.ry.community.controller;

import com.ry.community.dto.QuestionDTO;
import com.ry.community.mapper.QuestionMapepr;
import com.ry.community.mapper.UserMapper;
import com.ry.community.model.Question;
import com.ry.community.model.User;
import com.ry.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ry
 * @since 2021-08-31 16:20
 **/
@Controller
public class IndexController {
    Logger logger = Logger.getLogger("com.ry.controller.IndexController");

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        logger.info("request对象变量引用的是 "+request.getClass().getName());
        if (cookies != null && cookies.length != 0){
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
        }

        List<QuestionDTO> list = questionService.list();
        model.addAttribute("questions",list);

        return "index";
    }
}
