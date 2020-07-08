package icu.fordring.voter.utils;

import java.util.Random;
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

    public static String getRandomString(int length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!^@#$%&*?";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
