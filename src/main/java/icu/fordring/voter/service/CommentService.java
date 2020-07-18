package icu.fordring.voter.service;

import icu.fordring.voter.component.comment.CommentFactory;
import icu.fordring.voter.dao.CommentDao;
import icu.fordring.voter.dao.ImageDao;
import icu.fordring.voter.dto.comment.CommentDetailsDto;
import icu.fordring.voter.dto.comment.CommentListDto;
import icu.fordring.voter.pojo.Comment;
import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.AuthorityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName CommentService
 * @Author fordring
 * @date 2020.07.18 10:12
 */
@Service
public class CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private CommentFactory commentFactory;
    @Resource
    private ImageDao imageDao;
    /**
     * @Author fordring
     * @Description  插入一条评论
     * @Date 2020/7/18 10:29
     * @Param [iid, parent, content]
     * @return icu.fordring.voter.dto.comment.CommentDetailsDto
     **/
    public CommentDetailsDto insert(String iid, String parent, String content){
        Image image = imageDao.selectById(iid);
        if(image==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"图片不存在");
        if(commentDao.selectById(parent)==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"父评论不存在");
        User user = AuthorityUtils.getSelf();
        Comment comment = commentFactory.newComment(image,user,parent,content);
        commentDao.insert(comment);
        return new CommentDetailsDto(comment);
    }
    /**
     * @Author fordring
     * @Description  查询一条评论的信息
     * @Date 2020/7/18 10:35
     * @Param [cid]
     * @return icu.fordring.voter.dto.comment.CommentDetailsDto
     **/
    public CommentDetailsDto selectById(String cid){
        Comment comment = commentDao.selectById(cid);
        if(comment==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"评论不存在");
        return new CommentDetailsDto(comment);
    }
    /**
     * @Author fordring
     * @Description  查询某个图片下面的全部评论，并且将会按照时间降序排序，且会梳理好父子关系
     * @Date 2020/7/18 11:02
     * @Param [iid]
     * @return icu.fordring.voter.dto.comment.CommentListDto
     **/
    public CommentListDto selectAll(String iid){
        List<Comment> comments = commentDao.selectAll(iid);
        return new CommentListDto(comments);
    }
}
