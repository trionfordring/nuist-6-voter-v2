package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.LikeImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description
 * @ClassName LikeImageMapper
 * @Author fordring
 * @date 2020.07.18 15:21
 */
@Mapper
public interface LikeImageMapper {
    int insert(LikeImage likeImage);
    int deleteByLink(@Param("uid") String uid,@Param("iid") String iid);
    int deleteById(@Param("lid") String lid);
    int countByImage(@Param("iid") String iid);
    List<LikeImage> selectByImage(@Param("iid") String iid);
    List<LikeImage> selectByUser(@Param("name") String name);
}
