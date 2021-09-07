package com.ry.community.mapper;

import com.ry.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ry
 * @since 2021-09-05 19:13
 **/
@Mapper
public interface QuestionMapepr {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag })")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
