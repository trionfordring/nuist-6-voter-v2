package icu.fordring.voter.profile;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 运行环境相关配置
 * @ClassName DevProfile
 * @Author fordring
 * @date 2020.07.08 12:44
 */
@Component
@Data
@ToString
@ConfigurationProperties("app.dev")
public class DevProfile {
    private boolean enable;
    private SwaggerProfile swagger;
    @Data
    @ToString
    public static class SwaggerProfile{
        private String path;
    }
}
