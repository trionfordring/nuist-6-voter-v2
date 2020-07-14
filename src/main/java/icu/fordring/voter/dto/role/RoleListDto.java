package icu.fordring.voter.dto.role;

import icu.fordring.voter.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @ClassName RoleListDto
 * @Author fordring
 * @date 2020.07.09 10:26
 */
@ApiModel("角色信息集合")
@Data
public class RoleListDto {
    @ApiModelProperty("角色信息组成的集合")
    protected Set<RoleDto> roles;

    public RoleListDto(Collection<Role> roles){
        this.roles = new HashSet<>();
        for(Role role:roles){
            RoleDto roleDto = new RoleDto(role);
            this.roles.add(roleDto);
        }
    }
}
