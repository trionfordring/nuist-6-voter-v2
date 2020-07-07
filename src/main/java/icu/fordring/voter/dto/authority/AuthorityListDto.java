package icu.fordring.voter.dto.authority;

import icu.fordring.voter.pojo.Authority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @ClassName AuthorityListDto
 * @Author fordring
 * @date 2020.07.05 19:21
 */
@Data
@ApiModel("权限集合")
public class AuthorityListDto {
    @ApiModelProperty(value = "由权限构成的集合")
    private Set<AuthorityDto> authorities;

    public AuthorityListDto(){}
    public AuthorityListDto(Set<Authority> authorities){
        this.authorities = new HashSet<>();
        authorities.forEach((Authority a)->{
            this.authorities.add(new AuthorityDto(a));
        });
    }
}
