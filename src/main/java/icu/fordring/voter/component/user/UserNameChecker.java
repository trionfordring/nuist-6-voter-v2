package icu.fordring.voter.component.user;

import icu.fordring.voter.dao.UserDao;
import icu.fordring.voter.dto.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

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
     * @Description  整体检查用户名，如果用户名不合法，则抛出异常[403]
     * @Date 2020/7/8 12:05
     * @Param [name]
     * @return void
     **/
    public void checkName(String name){
        if(name==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"用户名不能为空");
        if(!checkLength(name))throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"用户名长度不符合规范");
        if(exist(name))throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"用户名已存在");
    }
    /**
     * @Author fordring
     * @Description  检查名字的长度是否符合要求
     * @Date 2020/7/6 15:52
     * @Param [name]
     * @return boolean
     **/
    public boolean checkLength(String name){
        if(name==null)return false;
        return name.length()>0&&name.length()<64;
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
