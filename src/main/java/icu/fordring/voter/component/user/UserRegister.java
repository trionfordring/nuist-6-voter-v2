package icu.fordring.voter.component.user;

import icu.fordring.voter.dao.RoleDao;
import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.profile.RoleProfile;
import icu.fordring.voter.utils.InternetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 用户注册组件
 * @ClassName UserRegister
 * @Author fordring
 * @date 2020.07.08 11:56
 */
@Component
@Slf4j
public class UserRegister {
    @Resource
    private UserNameChecker userNameChecker;
    @Resource
    private UserPasswordChecker userPasswordChecker;
    @Resource
    private UserFactory userFactory;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleProfile roleProfile;
    /**
     * @Author fordring
     * @Description  向数据库中注册一个用户
     * @Date 2020/7/8 12:27
     * @Param [name, password]
     * @return icu.fordring.voter.pojo.User
     **/
    public User register(String name, String password, Set<Role> roles){
        log.info("收到用户注册请求,name:{},pwd:{}",name,password);
        userNameChecker.checkName(name);
        userPasswordChecker.checkPassword(password);
        User user = userFactory.newDefaultUser();
        user.setUsername(name);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(password));
        userDao.insertUser(user);
        log.info("用户[{}]注册成功",name);
        return user;
    }
    public User register(String name, String password){
        HashSet<Role> roles = new HashSet<>();
        Role role = roleDao.getRoleByName(roleProfile.getUserRole());
        roles.add(role);
        return register(name,password,roles);
    }
}
