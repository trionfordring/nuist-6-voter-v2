package icu.fordring.voter.utils;

import java.util.UUID;

/**
 * @Description 字符串工具类
 * @ClassName StringUtils
 * @Author fordring
 * @date 2020.07.05 11:37
 */
public class StringUtils {
    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
