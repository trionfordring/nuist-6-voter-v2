package icu.fordring.voter.component.user;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 校验用户
 * @ClassName UserVerifier
 * @Author fordring
 * @date 2020.03.13 18:02
 */
@Slf4j
@Service
public class UserVerifier implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("收到登录请求[name='{}']",s);
        if(s==null||s.length()>255)throw new UsernameNotFoundException("用户名异常");
        User user = userDao.findUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new UserInfo(user);
    }
}