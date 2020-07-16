package icu.fordring.voter.component.image;

import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @ClassName ImageFactory
 * @Author fordring
 * @date 2020.07.16 16:21
 */
@Component
public class ImageFactory {
    public Image newImage(String name,String description){
        Image image = new Image();
        image.setCreateTime(new Date());
        image.setId(StringUtils.UUID());
        image.setDescription(description);
        image.setName(name);
        return image;
    }
}
