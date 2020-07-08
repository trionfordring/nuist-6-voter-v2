package icu.fordring.voter.profile;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName RoleProfile
 * @Author fordring
 * @date 2020.07.08 13:36
 */
@Component
@Data
@ToString
@ConfigurationProperties("app.role")
public class RoleProfile {
    private String userRole;
    private String rootRole;
}
