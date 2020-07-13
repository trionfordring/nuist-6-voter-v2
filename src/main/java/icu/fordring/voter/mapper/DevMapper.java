package icu.fordring.voter.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @ClassName DevMapper
 * @Author fordring
 * @date 2020.07.06 16:47
 */
@Mapper
public interface DevMapper {
    void createRole();
    void cleanRole();
    void initRole();

    void createRoleAuthority();
    void cleanRoleAuthority();

    void createAuthority();
    void cleanAuthority();
    void initAuthority();

    void createUser();
    void cleanUser();

    void createHeadResource();
    void cleanHeadResource();

    void createUserRole();
    void cleanUserRole();

    void createSessionTable();

    void createPersistentLoginTable();

    void createCommentTable();

    void createImageTable();

    void createLikeImageTable();
}
