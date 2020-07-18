package icu.fordring.voter.mapper;

import icu.fordring.voter.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @ClassName CommentMapper
 * @Author fordring
 * @date 2020.07.17 15:37
 */
@Mapper
public interface CommentMapper {
    /**
     * @Author fordring
     * @Description  插入一个评论
     * @Date 2020/7/17 16:06
     * @Param [comment]
     * @return int
     **/
    int insert(Comment comment);
    /**
     * @Author fordring
     * @Description  通过id查询一个评论
     * @Date 2020/7/17 16:07
     * @Param [id]
     * @return icu.fordring.voter.pojo.Comment
     **/
    Comment selectById(@Param("id") String id);
    /**
     * @Author fordring
     * @Description  通过iid查询全部评论的简略信息(id,parent,创建时间),将会按照时间降序排序
     * @Date 2020/7/17 16:11
     * @Param [iid]
     * @return java.util.List<icu.fordring.voter.pojo.Comment>
     **/
    List<Comment> selectAll(@Param("iid") String iid);
}
