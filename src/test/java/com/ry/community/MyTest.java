package com.ry.community;

import com.alibaba.fastjson.JSON;
import com.ry.community.dto.GithubUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author ry
 * @since 2021-09-05 20:06
 **/
public class MyTest {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        System.out.println(s1 == s2);*/
        int[] arr = null;

        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void test1(){
        String url = "https://github.com/login/oauth/authorize?client_id=f438334f1d6d792c3fb1&redirect_uri=http://localhost:8080/callback&scope=user&state=1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            System.out.println(response.body());
            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);
            System.out.println(githubUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
