package icu.fordring.voter.profile;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 权限相关配置
 * @ClassName AuthorityProfile
 * @Author fordring
 * @date 2020.07.06 11:37
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.authority")
public class AuthorityProfile {
    protected String[] defaultAuthorities;
}
