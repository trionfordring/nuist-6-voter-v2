package icu.fordring.voter.service;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.component.user.UserRegister;
import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.dto.user.UserDetailDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.dto.user.UserHasLoginDto;
import icu.fordring.voter.dto.user.UserNameExistDto;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.AuthorityUtils;
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

    /**
     * @Author fordring
     * @Description  查询用户名是否已存在
     * @Date 2020/7/9 9:47
     * @Param [name]
     * @return icu.fordring.voter.dto.user.UserNameExistDto
     **/
    public UserNameExistDto exist(String name){
        boolean exist = userDao.exist(name);
        return new UserNameExistDto(exist);
    }
    
    /**
     * @Author fordring
     * @Description  查询当前用户的详细信息
     * @Date 2020/7/9 10:23
     * @Param []
     * @return icu.fordring.voter.dto.user.UserDetailDto
     **/
    public UserDetailDto details(){
        UserInfo userInfo = AuthorityUtils.getSelf();
        log.info("用户{}查询了自身的详细信息",userInfo.getUsername());
        return new UserDetailDto(userInfo);
    }

    /**
     * @Author fordring
     * @Description  判断用户是否已经登录
     * @Date 2020/7/9 16:20
     * @Param []
     * @return icu.fordring.voter.dto.user.UserHasLoginDto
     **/
    public UserHasLoginDto hasLogin(){
        boolean ans = false;
        Authentication authentication = AuthorityUtils.getSelfAuthentication();
        if(authentication.getPrincipal() instanceof UserInfo)ans = true;
        log.info("用户[{}]检查自己登录状态:{}",authentication.getPrincipal(),ans);
        return new UserHasLoginDto(ans);
    }
}
