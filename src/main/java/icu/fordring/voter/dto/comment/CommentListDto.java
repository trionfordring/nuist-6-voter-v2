package icu.fordring.voter.dto.comment;

import icu.fordring.voter.pojo.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName CommentListDto
 * @Author fordring
 * @date 2020.07.18 10:37
 */
@Data
@ApiModel("评论列表")
public class CommentListDto {
    @ApiModelProperty("评论构成的数组")
    protected List<CommentSimpleDto> comments;

    /**
     * @Author fordring
     * @Description
     * 初始化comment列表
     * * 需要传入的comment按时间升序排序
     * * 即：父评论总是在子评论前面
     * * 如果某些子评论在父评论前面或父评论不存在，则这些子评论视为删除
     * * 乐观情况下时间复杂度:O(n)
     * * 一般情况下时间复杂度:O(n*log(n))
     * * 时间复杂度取决于HashMap的表现
     * @Date 2020/7/18 10:53
     * @Param [comments]
     * @return
     **/
    public CommentListDto(List<Comment> comments){
        this.comments = new ArrayList<>();
        Map<String,CommentSimpleDto> commentMap = new HashMap<>();
        for(Comment comment:comments){
            CommentSimpleDto commentSimpleDto = new CommentSimpleDto(comment);
            commentMap.put(comment.getId(),commentSimpleDto);   //HASH化comments数组，便于按照id查询
            if(comment.getParent()==null){
                this.comments.add(commentSimpleDto);
            }else {
                CommentSimpleDto parent = commentMap.get(comment.getParent());
                if(parent!=null)parent.getSons().add(commentSimpleDto);
            }
        }
    }
}
