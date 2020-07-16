package icu.fordring.voter.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @Description
 * @ClassName FileUtils
 * @Author fordring
 * @date 2020.07.16 18:31
 */
@Slf4j
public class FileUtils {
    /**
     * 判断两个content-type是否相同
     *
     * @param type1
     * @param type2
     * @return
     */
    public static boolean verifyFileType(String type1, String type2) {
        log.info("verifyFileType {},{}", type1, type2);
        Assert.notNull(type1, "格式不能为空");
        Assert.notNull(type2, "格式不能为空");
        String[] realType = type1.split("/"), inputType = type2.split("/");
        for (int i = 0; i < inputType.length && i < realType.length; i++) {
            if (!(inputType[i].equals("*") || inputType[i].equals(realType[i]))) {
                return false;
            }
        }
        return true;
    }
}
