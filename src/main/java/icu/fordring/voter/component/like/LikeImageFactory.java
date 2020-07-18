package icu.fordring.voter.component.like;

import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.pojo.LikeImage;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @ClassName LikeImageFactory
 * @Author fordring
 * @date 2020.07.18 16:38
 */
@Component
public class LikeImageFactory {
    public LikeImage newLikeImage(User owner, Image image){
        LikeImage likeImage = new LikeImage();
        likeImage.setCreateTime(new Date());
        likeImage.setId(StringUtils.UUID());
        likeImage.setImage(image);
        likeImage.setOwner(owner);
        return likeImage;
    }
}
