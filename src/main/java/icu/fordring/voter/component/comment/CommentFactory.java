package icu.fordring.voter.component.comment;

import icu.fordring.voter.pojo.Comment;
import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @ClassName CommentFactory
 * @Author fordring
 * @date 2020.07.18 10:19
 */
@Component
public class CommentFactory {
    /**
     * @Author fordring
     * @Description  构建一个新的comment
     * @Date 2020/7/18 10:27
     * @Param [image, owner, parent, content]
     * @return icu.fordring.voter.pojo.Comment
     **/
    public Comment newComment(Image image,User owner,String parent,String content){
        Comment comment = new Comment();
        comment.setCreateTime(new Date());
        comment.setId(StringUtils.UUID());
        comment.setOwner(owner);
        comment.setImage(image);
        comment.setParent(parent);
        comment.setContent(content);
        return comment;
    }
}
