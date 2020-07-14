package icu.fordring.voter.component.init;

import icu.fordring.voter.component.role.RoleFactory;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName RoleInitializer
 * @Author fordring
 * @date 2020.07.10 14:07
 */
@Slf4j
@Component
public class RoleInitializer implements Initializer {


    @Override
    public void init() throws Exception {
        log.warn("======初始化默认用户=======");
        Role anonymous = new Role();
        anonymous.setName("ANONYMOUS");
        anonymous.setDescription("匿名用户");
        RoleFactory.ANONYMOUS_ROLE = anonymous;
    }

    @Override
    public void after() throws Exception {

    }
}
