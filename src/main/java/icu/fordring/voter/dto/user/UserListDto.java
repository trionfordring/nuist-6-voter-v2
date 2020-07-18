package icu.fordring.voter.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @ClassName UserListDto
 * @Author fordring
 * @date 2020.07.18 16:52
 */
@Data
@ApiModel("用户列表")
public class UserListDto {
    @ApiModelProperty("用户组成的数组")
    protected List<UserDto> users;

}
