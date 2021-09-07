package com.ry.community.dto;

import com.ry.community.model.User;
import lombok.Data;

/**
 * @author ry
 * @since 2021-09-07 09:37
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
