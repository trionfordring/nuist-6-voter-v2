package icu.fordring.voter.profile;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 存放程序的配置文件
 * @ClassName ApplicationProfile
 * @Author fordring
 * @date 2020.07.08 12:41
 */
@Component
@Data
@ToString
@ConfigurationProperties("app")
public class ApplicationProfile {
    private boolean initDatabaseWhenStart;
    private DevProfile dev;
    private SecurityProfile security;
    private AuthorityProfile authority;
    private RoleProfile role;
}
