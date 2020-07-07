package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @Description 处理异常的controller
 * @ClassName ErrorController
 * @Author fordring
 * @date 2020.07.05 15:28
 */
@Api(tags = "异常")
@RestController
@RequestMapping("/error")
public class ErrorController {
    /**
     * @Author fordring
     * @Description  返回指定的错误信息
     * @Date 2020/7/5 15:31
     * @Param [code, message]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ApiOperation("返回一个异常")
    @PreAuthorize("hasAuthority('ERROR')")
    @RequestMapping(value = "/{code}/{message}",method = RequestMethod.GET)
    public Result<Object> error(@PathVariable int code,@PathVariable String message){
        throw new HttpClientErrorException(HttpStatus.valueOf(code),message);
    }
    /**
     * @Author fordring
     * @Description  用户权限不足的异常
     * @Date 2020/7/5 15:37
     * @Param []
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ApiOperation("返回权限不足")
    @ApiResponse(code = 403,message = "权限不足")
    @PreAuthorize("hasAuthority('ERROR')")
    @RequestMapping(value = "/unauthorized",method = RequestMethod.GET)
    public Result<Object> unauthorized(){
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN,"用户权限不足");
    }
}