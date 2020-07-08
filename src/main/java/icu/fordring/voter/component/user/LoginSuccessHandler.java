package icu.fordring.voter.component.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.AuthorityUtils;
import icu.fordring.voter.utils.InternetUtils;
import icu.fordring.voter.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName LoginSussessHandler
 * @Author fordring
 * @date 2020.07.03 20:51
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private ResponseUtils responseUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("用户[{}]登录成功,登录ip:{}",authentication.getName(), InternetUtils.getIPAddress(httpServletRequest));
        UserInfo userInfo = AuthorityUtils.getSelf();
        Result<Object> result = new Result<>();
        result.setStatus(200);
        result.setData(new UserDto(userInfo));
        result.setMessage("登录成功");
        responseUtils.writeResult(result,httpServletResponse);
    }
}
