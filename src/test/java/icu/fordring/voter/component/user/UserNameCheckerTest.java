package icu.fordring.voter.component.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName UserNameCheckerTest
 * @Author fordring
 * @date 2020.07.06 15:40
 */
@SpringBootTest
public class UserNameCheckerTest {
    @Resource
    private UserNameChecker userNameChecker;
    @Test
    public void testExist(){
        Assertions.assertEquals(false,userNameChecker.exist("a12312a"));
        Assertions.assertEquals(false,userNameChecker.exist(null));
        Assertions.assertEquals(false,userNameChecker.exist(""));
    }
}
