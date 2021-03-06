package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.user.UserDetailDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.dto.user.UserHasLoginDto;
import icu.fordring.voter.dto.user.UserNameExistDto;
import icu.fordring.voter.service.CaptchaService;
import icu.fordring.voter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    @Resource
    private CaptchaService captchaService;
    @PreAuthorize("hasAuthority('USER_REGISTER')")
    @ApiOperation(value = "用户注册",notes = "[USER_REGISTER]<br><b>注意:</b>用户请求完成后,无论成功与否，都会清空验证码缓存")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<UserDto> register(
            @RequestParam("name") @ApiParam("用户名")                               String name       ,
            @RequestParam("password") @ApiParam("密码")                             String password   ,
            @RequestParam(value = "verify",required = false) @ApiParam("校验码")    String verifyCode ,
            HttpServletRequest request
    ){
        captchaService.verity(verifyCode,request.getSession());
        return new Result<>(HttpStatus.OK,userService.register(name,password,request),"注册成功");
    }

    @PreAuthorize("hasAnyAuthority('USER_REGISTER','USER_FIND_BACK')")
    @ApiOperation(value = "查询用户名是否已存在",notes = "[USER_REGISTER,USER_FIND_BACK]")
    @RequestMapping(value = "/exist/{name}",method = RequestMethod.GET)
    public Result<UserNameExistDto> exist(
            @PathVariable("name") @ApiParam("用户名") String name
    ){
        return new Result<>(HttpStatus.OK,userService.exist(name),"查询成功");
    }

    @PreAuthorize("hasAuthority('USER_INFO')")
    @ApiOperation(value = "查询用户的详细信息",notes = "[USER_INFO]")
    @RequestMapping(value = "/details",method = RequestMethod.GET)
    public Result<UserDetailDto> details(){
        return new Result<>(HttpStatus.OK, userService.details(),"查询成功");
    }

    @PreAuthorize("hasAuthority('HAS_LOGIN')")
    @ApiOperation(value = "查询用户是否已登录",notes = "[HAS_LOGIN]")
    @RequestMapping(value = "/hasLogin",method = RequestMethod.GET)
    public Result<UserHasLoginDto> hasLogin(){
        return new Result<>(HttpStatus.OK,userService.hasLogin(),"查询成功");
    }

    @PreAuthorize("hasAuthority('USER_REGISTER')")
    @ApiOperation(value = "获取一个注册用验证码",notes = "[USER_REGISTER]")
    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //禁止缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = captchaService.createCaptcha(request.getSession());
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
}
