package icu.fordring.voter.dto.comment;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.pojo.Comment;
import icu.fordring.voter.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Description
 * @ClassName CommentListTest
 * @Author fordring
 * @date 2020.07.18 11:58
 */
public class CommentListTest {
    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED,true);
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        Comment comment3 = new Comment();
        Comment comment4 = new Comment();
        Comment comment5 = new Comment();
        Comment comment6 = new Comment();
        comment1.setId("1");
        comment2.setId("2");
        comment3.setId("3");
        comment4.setId("4");
        comment5.setId("5");
        comment6.setId("6");
        comment3.setParent("2");
        comment4.setParent("2");
        comment5.setParent("3");
        CommentListDto commentListDto = new CommentListDto(Arrays.asList(comment1,comment2,comment3,comment4,comment5,comment6));
        System.out.println(StringUtils.formatJson(objectMapper.writeValueAsString(commentListDto)));
    }
}
