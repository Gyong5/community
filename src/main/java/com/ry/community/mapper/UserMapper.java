package com.ry.community.mapper;

import com.ry.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ry
 * @since 2021-09-02 18:20
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modify,avatar_url) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user")
    List<User> list();
}
