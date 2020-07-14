package icu.fordring.voter.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 板块
 * @ClassName Plate
 * @Author fordring
 * @date 2020.07.14 16:57
 */
@Data
public class Plate implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String uid;
    protected String description;
    protected Date startTime;
    protected Date endTime;
    protected Date createTime;
}
