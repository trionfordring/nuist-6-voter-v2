package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.CommentMapper;
import icu.fordring.voter.pojo.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName CommentDao
 * @Author fordring
 * @date 2020.07.17 16:13
 */
@Slf4j
@Component
public class CommentDao {
    @Resource
    private CommentMapper commentMapper;
    /**
     * @Author fordring
     * @Description  插入一条评论
     * @Date 2020/7/17 16:18
     * @Param [comment]
     * @return void
     **/
    public void insert(Comment comment){
        log.info("正在添加评论[{}],父评论[{}]",comment.getId(),comment.getParent());
        if(commentMapper.insert(comment)!=1)throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"添加评论失败");
    }
    /**
     * @Author fordring
     * @Description  通过id查询一条评论
     * @Date 2020/7/17 16:20
     * @Param [id]
     * @return icu.fordring.voter.pojo.Comment
     **/
    public Comment selectById(String id){
        log.info("正在查询评论[{}]",id);
        Comment comment = commentMapper.selectById(id);
        return comment;
    }
    /**
     * @Author fordring
     * @Description  查询所有评论
     * @Date 2020/7/17 16:22
     * @Param [iid]
     * @return java.util.List<icu.fordring.voter.pojo.Comment>
     **/
    public List<Comment> selectAll(String iid){
        log.info("正在查询图片[{}]下的所有评论",iid);
        return commentMapper.selectAll(iid);
    }
}
