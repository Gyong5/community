package com.ry.community;

import com.ry.community.controller.AuthorizeController;
import com.ry.community.dto.AccessTokenDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

@SpringBootTest(classes = CommunityApplication.class)
@RunWith(SpringRunner.class)
public class CommunityApplicationTests {

    @Autowired
    private AuthorizeController authorizeController;

    @Test
    public void contextLoads() {
        //需要先拿到code
    }

    @Test
    public void test1(){
        String str1 = "abc";
        String str2 = "abc";

        System.out.println(str1 == str2);
    }
}
