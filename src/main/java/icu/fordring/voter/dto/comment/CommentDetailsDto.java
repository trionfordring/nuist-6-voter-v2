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
 * @date 2020.07.17 16:25
 */
@Data
@ApiModel("评论的详细信息")
public class CommentDetailsDto extends CommentDto {
    @ApiModelProperty("父图片")
    protected ImageDto image;
    public CommentDetailsDto(Comment comment){
        super(comment);
        if(comment==null)return;
        if(comment.getImage()!=null)this.image=new ImageDto(comment.getImage());
    }
}
