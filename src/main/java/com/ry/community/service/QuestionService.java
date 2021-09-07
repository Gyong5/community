package com.ry.community.service;

import com.ry.community.dto.QuestionDTO;
import com.ry.community.mapper.QuestionMapepr;
import com.ry.community.mapper.UserMapper;
import com.ry.community.model.Question;
import com.ry.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author ry
 * @since 2021-09-07 09:40
 **/
@Service
public class QuestionService {

    Logger logger = Logger.getLogger("com.ry.community.service.QuestionService");
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private QuestionMapepr questionMapepr;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        Map<Integer, User> map = new HashMap<>();
        List<User> users = userMapper.list();
        List<Question> questions = questionMapepr.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            map.put(user.getId(),user);
        }
        for (int i = 0; i < questions.size(); i++) {
            QuestionDTO questionDTO = new QuestionDTO();
            Question question = questions.get(i);
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(map.get(questionDTO.getCreator()));
            questionDTOList.add(questionDTO);
        }
        logger.info(questionDTOList.toString());

        return questionDTOList;
    }
}
