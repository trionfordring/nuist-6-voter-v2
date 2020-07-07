package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName UserController
 * @Author fordring
 * @date 2020.07.06 12:47
 */
@Api(tags = "用户")
@RestController()
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "用户注册",notes = "[USER_REGISTER]")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<UserDto> register(){
        return new Result<>();
    }
}
