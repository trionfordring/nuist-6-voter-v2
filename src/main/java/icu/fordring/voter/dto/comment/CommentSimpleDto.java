package icu.fordring.voter.dto.comment;

import icu.fordring.voter.pojo.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName CommentSimpleDto
 * @Author fordring
 * @date 2020.07.18 09:55
 */
@Data
@ApiModel("评论的结构信息")
public class CommentSimpleDto {
    @ApiModelProperty("唯一标识符id")
    protected String id;
    @ApiModelProperty("子评论")
    protected List<CommentSimpleDto> sons;
    @ApiModelProperty("发表时间")
    protected Long createTime;
    public CommentSimpleDto(Comment comment){
        if(comment==null)return;
        this.sons=new ArrayList<>();
        this.id=comment.getId();
        if(comment.getCreateTime()!=null)this.createTime=comment.getCreateTime().getTime();
    }
}
