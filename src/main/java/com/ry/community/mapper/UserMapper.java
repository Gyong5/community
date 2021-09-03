package com.ry.community.mapper;

import com.ry.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ry
 * @since 2021-09-02 18:20
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modify) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModify})")
    public void insert(User user);
}
