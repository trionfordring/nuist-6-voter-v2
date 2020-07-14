package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @ClassName RoleAuthorityMapper
 * @Author fordring
 * @date 2020.07.06 17:27
 */
@Mapper
public interface RoleAuthorityMapper {
    int insert(@Param("rid") String rid,@Param("aid") Long aid);

    /**
     * @Author fordring
     * @Description  删除一个用户的一个权限
     * @Date 2020/7/14 14:49
     * @Param [rid, aid]
     * @return int
     **/
    int delete(@Param("rid") String rid,@Param("aid") Long aid);
}
