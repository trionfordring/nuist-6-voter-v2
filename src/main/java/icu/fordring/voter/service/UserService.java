package icu.fordring.voter.service;

import icu.fordring.voter.component.user.UserRegister;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName UserService
 * @Author fordring
 * @date 2020.07.06 14:15
 */
@Service
public class UserService {
    @Resource
    private UserRegister userRegister;
    
    /**
     * @Author fordring
     * @Description  注册一个账户
     * @Date 2020/7/8 19:58
     * @Param [name, password]
     * @return icu.fordring.voter.dto.user.UserDto
     **/
    public UserDto register(String name,String password){
        User user = userRegister.register(name,password);
        UserDto userDto = new UserDto(user);
        return userDto;
    }
}
