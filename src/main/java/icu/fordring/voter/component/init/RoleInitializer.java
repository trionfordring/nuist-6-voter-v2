package icu.fordring.voter.component.init;

import icu.fordring.voter.component.role.RoleFactory;
import icu.fordring.voter.pojo.Role;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName RoleInitializer
 * @Author fordring
 * @date 2020.07.10 14:07
 */
@Component
public class RoleInitializer implements Initializer {


    @Override
    public void init() throws Exception {
        Role anonymous = new Role();
        anonymous.setName("ANONYMOUS");
        anonymous.setDescription("匿名用户");
        RoleFactory.ANONYMOUS_ROLE = anonymous;
    }

    @Override
    public void after() throws Exception {

    }
}
