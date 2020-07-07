package icu.fordring.voter.component.user;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.utils.InternetUtils;
import icu.fordring.voter.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @ClassName LoginFailureHandler
 * @Author fordring
 * @date 2020.07.03 20:50
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ResponseUtils responseUtils;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.warn("用户[ip='{}']登录失败", InternetUtils.getIPAddress(httpServletRequest));
        Result<Object> result = new Result<>();
        result.setStatus(403);
        result.setMessage(e.getMessage());
        responseUtils.writeResult(result,httpServletResponse);
    }
}
