package icu.fordring.voter.component.user;


import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.utils.AuthorityUtils;
import icu.fordring.voter.utils.InternetUtils;
import icu.fordring.voter.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @ClassName LogoutSuccessHandler
 * @Author fordring
 * @date 2020.07.08 20:53
 */
@Component
@Slf4j
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Resource
    private ResponseUtils responseUtils;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication==null){
            Result<Object> result = new Result<>();
            result.setStatus(403);
            result.setMessage("用户未登录");
            responseUtils.writeResult(result,httpServletResponse);
            return;
        }
        log.info("用户[{}]登出成功,登录ip:{}",authentication.getName(), InternetUtils.getIPAddress(httpServletRequest));
        Result<Object> result = new Result<>();
        result.setStatus(200);
        result.setMessage("登出成功");
        responseUtils.writeResult(result,httpServletResponse);
    }
}
