package com.ry.community.controller;

import com.ry.community.mapper.QuestionMapepr;
import com.ry.community.mapper.UserMapper;
import com.ry.community.model.Question;
import com.ry.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ry
 * @since 2021-09-05 10:12
 **/
@Controller
public class PublishController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private QuestionMapepr questionMapepr;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "/publish";
        }
        if(description == null ||description.equals("")){
            model.addAttribute("error","详细内容不能为空");
            return "/publish";
        }

        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    user = userMapper.findByToken(cookie.getValue());
                    if (user != null){
                        //user不为空,将信息加到session中
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "/publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        question.setCreator(user.getId());

        questionMapepr.create(question);

        return "redirect:/";
    }
}
