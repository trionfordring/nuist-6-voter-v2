package icu.fordring.voter.profile;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description spring-security配置
 * @ClassName SecurityProfile
 * @Author fordring
 * @date 2020.07.08 12:43
 */
@Component
@Data
@ToString
@ConfigurationProperties("app.security")
public class SecurityProfile {
    private boolean enable;
}
