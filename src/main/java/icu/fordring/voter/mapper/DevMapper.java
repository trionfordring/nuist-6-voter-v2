package icu.fordring.voter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @Description
 * @ClassName DevMapper
 * @Author fordring
 * @date 2020.07.06 16:47
 */
@Mapper
public interface DevMapper {
    int createRole();
    int cleanRole();
    int initRole();

    int createRoleAuthority();
    int cleanRoleAuthority();

    int createAuthority();
    int cleanAuthority();
    int initAuthority();
}
