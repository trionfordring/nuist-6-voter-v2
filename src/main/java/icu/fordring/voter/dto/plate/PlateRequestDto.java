package icu.fordring.voter.dto.plate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName PlateRequestDto
 * @Author fordring
 * @date 2020.07.15 19:25
 */
@Data
@ApiModel("新建板块参数")
public class PlateRequestDto {
    @ApiModelProperty("板块名")
    protected String name;
    @ApiModelProperty("板块简介")
    protected String description;
    @ApiModelProperty("活动起始时间")
    protected Long startTime;
    @ApiModelProperty("活动结束时间")
    protected Long endTime;
}
