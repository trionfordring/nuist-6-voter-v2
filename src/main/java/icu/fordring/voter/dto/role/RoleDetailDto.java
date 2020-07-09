package icu.fordring.voter.dto.role;

import icu.fordring.voter.dto.authority.AuthorityDto;
import icu.fordring.voter.dto.authority.AuthorityListDto;
import icu.fordring.voter.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @ClassName RoleDetailDto
 * @Author fordring
 * @date 2020.07.09 10:10
 */
@ApiModel("角色详细信息")
@Data
public class RoleDetailDto extends RoleDto {
    @ApiModelProperty("角色的权限信息")
    protected Set<AuthorityDto> authorities;

    public RoleDetailDto(Role role){
        super(role);
        this.authorities = new AuthorityListDto(role.getAuthorities()).getAuthorities();
    }
}
