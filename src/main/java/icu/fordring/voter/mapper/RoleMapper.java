package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @ClassName RoleMapper
 * @Author fordring
 * @date 2020.07.06 17:21
 */
@Mapper
public interface RoleMapper {
    /**
     * @Author fordring
     * @Description  查出所有的角色信息（不包括相关的权限和用户信息）
     * @Date 2020/7/8 12:27
     * @Param []
     * @return java.util.List<icu.fordring.voter.pojo.Role>
     **/
    List<Role> selectAll();

    /**
     * @Author fordring
     * @Description  通过角色名查找角色(不包括级联信息)
     * @Date 2020/7/8 13:30
     * @Param [name]
     * @return icu.fordring.voter.pojo.Role
     **/
    Role selectByName(String name);
}
