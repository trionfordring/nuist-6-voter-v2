package icu.fordring.voter.dto.authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @ClassName AuthorityNameListDto
 * @Author fordring
 * @date 2020.07.06 11:29
 */
@Data
@ApiModel("权限名数组")
public class AuthorityNameListDto {
    @ApiModelProperty(value = "由权限名构成的集合")
    private Set<String> authorities;
}
