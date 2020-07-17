package icu.fordring.voter.dto.image;

import icu.fordring.voter.dto.plate.PlateSimpleDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Image;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @ClassName ImageDto
 * @Author fordring
 * @date 2020.07.16 16:01
 */
@Data
@ApiModel("image")
public class ImageDto {
    @ApiModelProperty("唯一标识符")
    protected String id;
    @ApiModelProperty("所有者")
    protected UserDto owner;
    @ApiModelProperty("所有板块")
    protected PlateSimpleDto plate;
    @ApiModelProperty("简介")
    protected String description;
    @ApiModelProperty(value = "创建时间",example = "0")
    protected Long createTime;
    @ApiModelProperty("image名")
    protected String name;
    public ImageDto(Image image){
        if(image==null)return;
        this.id=image.getId();
        if(image.getOwner()!=null)this.owner=new UserDto(image.getOwner());
        if(image.getPlate()!=null)this.plate=new PlateSimpleDto(image.getPlate());
        this.description=image.getDescription();
        if(image.getCreateTime()!=null)this.createTime=image.getCreateTime().getTime();
        this.name=image.getName();
    }
}
