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
    List<Role> selectAll();
}
