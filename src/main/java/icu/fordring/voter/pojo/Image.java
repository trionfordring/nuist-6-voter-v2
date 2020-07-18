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
    protected User owner;
    protected Plate plate;
    protected String description;
    protected Date createTime;
    protected byte[] resource;
    protected String name;
    protected Integer likes;
}
