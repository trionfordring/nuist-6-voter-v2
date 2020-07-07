package icu.fordring.voter.utils;

import org.junit.jupiter.api.Test;

/**
 * @Description
 * @ClassName StringUtilsTest
 * @Author fordring
 * @date 2020.07.05 11:39
 */
public class StringUtilsTest {
    @Test
    public void testUUID(){
        for(int i=0;i<10;i++)
            System.out.println(StringUtils.UUID());
    }
}
