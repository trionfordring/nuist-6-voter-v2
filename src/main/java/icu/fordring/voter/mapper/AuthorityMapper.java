package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;

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
}
