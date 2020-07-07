package icu.fordring.voter.dto.authority;

import icu.fordring.voter.pojo.Authority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 展现给用户的权限对象
 * @ClassName AuthorityDto
 * @Author fordring
 * @date 2020.07.05 19:17
 */
@Data
@ApiModel("权限对象")
public class AuthorityDto {
    @ApiModelProperty(value = "权限名",example = "USER_LOGIN")
    private String name;
    @ApiModelProperty(value = "解释",example = "登录权限")
    private String description;

    public AuthorityDto(){}
    public AuthorityDto(Authority authority){
        this.name=authority.getName();
        this.description=authority.getDescription();
    }
}
