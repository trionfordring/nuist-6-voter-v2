package icu.fordring.voter.component.init;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @Description 初始化接口
 * @ClassName Initializer
 * @Author fordring
 * @date 2020.07.08 12:56
 */
public interface Initializer {
    void init() throws Exception;

    void after() throws Exception;
}
