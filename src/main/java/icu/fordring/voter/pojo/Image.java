package icu.fordring.voter.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 图片
 * @ClassName Image
 * @Author fordring
 * @date 2020.07.14 16:59
 */
@Data
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String uid;
    protected String pid;
    protected String description;
    protected Date createTime;
    protected byte[] resource;
}
