package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Plate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @ClassName PlateMapper
 * @Author fordring
 * @date 2020.07.14 17:08
 */
@Mapper
public interface PlateMapper {
    int insert(Plate plate);
    Plate selectById(@Param("id") String id);
    List<Plate> selectAll();
}
