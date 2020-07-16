package icu.fordring.voter.dto.plate;

import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Plate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @ClassName PlateDto
 * @Author fordring
 * @date 2020.07.15 11:31
 */
@Data
@ApiModel("板块")
public class PlateDto {
    @ApiModelProperty("板块的唯一标识符")
    protected String id;
    @ApiModelProperty("板块的创建者")
    protected UserDto owner;
    @ApiModelProperty("板块名")
    protected String name;
    @ApiModelProperty("板块简介")
    protected String description;
    @ApiModelProperty("活动起始时间戳")
    protected Long startTime;
    @ApiModelProperty("活动结束时间戳")
    protected Long endTime;
    @ApiModelProperty("板块创建时间戳")
    protected Long createTime;

    public PlateDto(Plate plate){
        this.id = plate.getId();
        this.owner = new UserDto(plate.getOwner());
        this.name = plate.getName();
        this.description = plate.getDescription();
        this.startTime = plate.getStartTime().getTime();
        this.endTime = plate.getEndTime().getTime();
        this.createTime = plate.getCreateTime().getTime();
    }
}
