package icu.fordring.voter.dto.role;

import icu.fordring.voter.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @ClassName RoleDetailListDto
 * @Author fordring
 * @date 2020.07.09 10:15
 */
@ApiModel("角色详细信息集合")
@Data
public class RoleDetailListDto {
    @ApiModelProperty("由角色详细信息构成的集合")
    protected Set<RoleDetailDto> roles;

    public RoleDetailListDto(Set<Role> roles){
        this.roles = new HashSet<>();
        for(Role role:roles){
            RoleDetailDto roleDetailDto = new RoleDetailDto(role);
            this.roles.add(roleDetailDto);
        }
    }
}
