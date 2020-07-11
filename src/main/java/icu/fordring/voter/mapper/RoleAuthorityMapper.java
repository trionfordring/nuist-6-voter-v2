package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @ClassName RoleAuthorityMapper
 * @Author fordring
 * @date 2020.07.06 17:27
 */
@Mapper
public interface RoleAuthorityMapper {
    int insert(String rid,Long aid);
}
