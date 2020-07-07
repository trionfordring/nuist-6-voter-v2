package icu.fordring.voter.component.user;

import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName UserFactory
 * @Author fordring
 * @date 2020.07.04 16:19
 */
@Component
public class UserFactory {
    public User newDefaultUser(){
        User user = new User();
        user.setId(StringUtils.UUID());
        return user;
    }
}
