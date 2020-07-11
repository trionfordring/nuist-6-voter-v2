package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @ClassName AuthorityMapper
 * @Author fordring
 * @date 2020.07.06 17:18
 */
@Mapper
public interface AuthorityMapper {
    List<Authority> selectAll();

    /**
     * @Author fordring
     * @Description  通过权限名查找权限
     * @Date 2020/7/11 12:06
     * @Param [name]
     * @return icu.fordring.voter.pojo.Authority
     **/
    Authority selectByName(@Param("name") String name);
}
