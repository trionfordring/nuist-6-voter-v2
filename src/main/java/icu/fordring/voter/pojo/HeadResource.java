package icu.fordring.voter.pojo;

import lombok.Data;

/**
 * @Description 头像资源
 * @ClassName HeadResource
 * @Author fordring
 * @date 2020.07.04 16:03
 */
@Data
public class HeadResource {
    private String id;
    private byte[] resource;
    private Boolean isGift;
}
