package icu.fordring.voter.dto.comment;

import icu.fordring.voter.dto.image.ImageDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName CommentDto
 * @Author fordring
 * @date 2020.07.18 10:02
 */
@Data
@ApiModel("评论信息")
public class CommentDto extends CommentSimpleDto {
    @ApiModelProperty("内容")
    protected String content;
    @ApiModelProperty("作者")
    protected UserDto owner;
    public CommentDto(Comment comment){
        super(comment);
        if(comment==null)return;
        this.content=comment.getContent();
        if(comment.getOwner()!=null)this.owner=new UserDto(comment.getOwner());
    }
}
