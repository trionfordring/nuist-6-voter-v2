package icu.fordring.voter.dto.user;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dto.authority.AuthorityDto;
import icu.fordring.voter.dto.authority.AuthorityListDto;
import icu.fordring.voter.dto.role.RoleDetailDto;
import icu.fordring.voter.dto.role.RoleDetailListDto;
import icu.fordring.voter.dto.role.RoleDto;
import icu.fordring.voter.dto.role.RoleListDto;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @ClassName UserDetailDto
 * @Author fordring
 * @date 2020.07.09 10:05
 */
@Data
@ApiModel("用户详细信息")
public class UserDetailDto extends UserDto {
    @ApiModelProperty("由角色详细信息组成的集合")
    protected Set<RoleDto> roles;
    @ApiModelProperty("由用户权限组成的集合")
    protected Set<AuthorityDto> authorities;

    public UserDetailDto(User user){
        super(user);
        this.roles = new RoleListDto(user.getRoles()).getRoles();
        this.authorities = new AuthorityListDto(new UserInfo(user).getAuthoritiesSet()).getAuthorities();
    }
}
