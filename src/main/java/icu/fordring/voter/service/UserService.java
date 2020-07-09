package icu.fordring.voter.service;

import icu.fordring.voter.component.user.UserRegister;
import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName UserService
 * @Author fordring
 * @date 2020.07.06 14:15
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private UserRegister userRegister;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDao userDao;
    
    /**
     * @Author fordring
     * @Description  注册一个账户
     * @Date 2020/7/8 20:01
     * @Param [name, password]
     * @return icu.fordring.voter.dto.user.UserDto
     **/
    public UserDto register(String name, String password, HttpServletRequest request){
        log.info("用户{}正在注册",name);
        User user = userRegister.register(name,password);
        UserDto userDto = new UserDto(user);
        login(user.getUsername(),password,request);
        return userDto;
    }

    /**
     * @Author fordring
     * @Description  用户登录
     * @Date 2020/7/8 20:24
     * @Param [name, password, request]
     * @return void
     **/
    public void login(String name,String password,HttpServletRequest request){
        log.info("用户{}正在登录",name);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name,password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser=authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        log.info("用户{}登录成功",name);
    }
}
