package icu.fordring.voter.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 图片点赞
 * @ClassName LikeImage
 * @Author fordring
 * @date 2020.07.14 17:01
 */
@Data
public class LikeImage implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected User owner;
    protected Image image;
    protected Date createTime;
}
