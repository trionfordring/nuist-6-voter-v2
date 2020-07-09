package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

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
    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('USER_REGISTER')")
    @ApiOperation(value = "用户注册",notes = "[USER_REGISTER]")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<UserDto> register(
            @RequestParam("name") @ApiParam("用户名")                               String name       ,
            @RequestParam("password") @ApiParam("密码")                             String password   ,
            @RequestParam(value = "verify",required = false) @ApiParam("校验码")    String verifyCode ,
            HttpServletRequest request
    ){

        return new Result<>(HttpStatus.OK,userService.register(name,password,request),"注册成功");
    }


}
