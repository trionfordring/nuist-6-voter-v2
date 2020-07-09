package icu.fordring.voter.dto.role;

import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @ClassName RoleDto
 * @Author fordring
 * @date 2020.07.09 10:06
 */
@ApiModel("角色信息")
@Data
public class RoleDto {
    @ApiModelProperty("角色名")
    protected String name;
    @ApiModelProperty("解释")
    protected String description;

    public RoleDto(Role role){
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
