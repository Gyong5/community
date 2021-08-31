package com.ry.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ry
 * @since 2021-08-31 16:20
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
