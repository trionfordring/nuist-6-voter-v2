package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @ClassName ImageMapper
 * @Author fordring
 * @date 2020.07.16 12:30
 */
@Mapper
public interface ImageMapper {
    Image selectById(@Param("id") String id);
    int insert(Image image);
}
