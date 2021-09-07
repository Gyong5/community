package com.ry.community.provider;

import com.alibaba.fastjson.JSON;
import com.ry.community.dto.AccessTokenDTO;
import com.ry.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author ry
 * @since 2021-09-02 09:08
 **/
@Component
public class GithubProvider {
    Logger logger = Logger.getLogger("com.ry.community.provider.GithubProvider");

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json;charset=utf8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()){
            String str = response.body().string();
            String accessToken = str.split("&")[0].split("=")[1];
            logger.info(accessToken);


            return accessToken;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try(Response response = client.newCall(request).execute()){
            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);

            return githubUser;
        }catch (Exception e){

        }

        return null;
    }
}
