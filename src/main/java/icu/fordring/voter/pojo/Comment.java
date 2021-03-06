package icu.fordring.voter.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description 评论
 * @ClassName Comment
 * @Author fordring
 * @date 2020.07.14 17:03
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String content;
    protected User owner;
    protected Image image;
    protected String parent;
    protected Date createTime;
}
