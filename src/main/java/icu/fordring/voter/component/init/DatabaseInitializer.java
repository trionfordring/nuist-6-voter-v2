package icu.fordring.voter.component.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @Description 初始化数据库
 * @ClassName DatabaseInitializer
 * @Author fordring
 * @date 2020.07.07 21:20
 */
@Component
@Slf4j
@ConditionalOnProperty("app.init-database-when-start")
public class DatabaseInitializer {
    public DatabaseInitializer(){
        log.info("载入[DatabaseInitializer],即将重置数据库");
    }
}
