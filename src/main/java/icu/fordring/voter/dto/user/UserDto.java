package icu.fordring.voter.dto.user;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @ClassName UserDto
 * @Author fordring
 * @date 2020.07.06 13:25
 */
@ApiModel("用户信息")
@Data
public class UserDto {
    @ApiModelProperty("用户名")
    protected String username;
    @ApiModelProperty("真实姓名")
    protected String realName;
    @ApiModelProperty("学号")
    protected String studentId;
    @ApiModelProperty("邮箱")
    protected String email;
    @ApiModelProperty("电话")
    protected String phone;
    @ApiModelProperty("简介")
    protected String description;

    public UserDto(){}
    public UserDto(User user){
        this.username=user.getUsername();
        this.realName=user.getRealName();
        this.studentId=user.getStudentId();
        this.email=user.getEmail();
        this.phone=user.getPhone();
        this.description=user.getDescription();
    }

}
