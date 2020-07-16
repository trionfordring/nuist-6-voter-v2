package icu.fordring.voter.dto.plate;

import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Plate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName PlateSimpleDto
 * @Author fordring
 * @date 2020.07.15 20:08
 */
@Data
@ApiModel("板块的简单信息")
public class PlateSimpleDto {
    @ApiModelProperty("板块的唯一标识符")
    protected String id;
    @ApiModelProperty("板块名")
    protected String name;
    @ApiModelProperty("活动起始时间戳")
    protected Long startTime;
    @ApiModelProperty("活动结束时间戳")
    protected Long endTime;

    public PlateSimpleDto(Plate plate){
        this.id = plate.getId();
        this.name = plate.getName();
        this.startTime = plate.getStartTime().getTime();
        this.endTime = plate.getEndTime().getTime();
    }
}
