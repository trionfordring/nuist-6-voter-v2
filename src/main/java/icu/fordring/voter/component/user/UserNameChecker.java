package icu.fordring.voter.component.user;

import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.dto.user.UserDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description 用户名校验器
 * @ClassName UserNameChecker
 * @Author fordring
 * @date 2020.07.06 14:15
 */
@Component
public class UserNameChecker {

    @Resource
    private UserDao userDao;


    /**
     * @Author fordring
     * @Description  检查名字的长度是否符合要求
     * @Date 2020/7/6 15:52
     * @Param [name]
     * @return boolean
     **/
    public boolean checkLength(String name){
        if(name==null)return false;
        return name.length()>0&&name.length()<32;
    }

    /**
     * @Author fordring
     * @Description  判断用户名是否存在
     * @Date 2020/7/6 15:32
     * @Param [name]
     * @return boolean
     **/
    public boolean exist(String name){
        return userDao.exist(name);
    }
}
