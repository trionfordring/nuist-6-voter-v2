package icu.fordring.voter.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 头像资源
 * @ClassName HeadResource
 * @Author fordring
 * @date 2020.07.04 16:03
 */
@Data
public class HeadResource implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private byte[] resource;
    private Boolean isGift;
}
