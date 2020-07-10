package icu.fordring.voter.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName UserHasLoginDto
 * @Author fordring
 * @date 2020.07.09 16:12
 */
@ApiModel("用户是否已登录")
@Data
public class UserHasLoginDto {
    @ApiModelProperty("是否已登录")
    protected boolean hasLogin;

    public UserHasLoginDto(boolean hasLogin){
        this.hasLogin = hasLogin;
    }
}
