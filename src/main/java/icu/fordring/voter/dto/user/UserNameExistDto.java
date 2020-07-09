package icu.fordring.voter.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description 查询用户名是否存在
 * @ClassName UserNameExistDto
 * @Author fordring
 * @date 2020.07.09 09:43
 */
@ApiModel("用户名是否存在")
@Data
public class UserNameExistDto {
    private boolean exist;
    public UserNameExistDto(boolean exist){
        this.exist=exist;
    }
}
