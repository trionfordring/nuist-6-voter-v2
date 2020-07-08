package icu.fordring.voter.component.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @Description 密码合法性检查
 * @ClassName UserPasswordChecker
 * @Author fordring
 * @date 2020.07.08 12:05
 */
@Component
public class UserPasswordChecker {
    public void checkPassword(String password){
        if(password==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"未填写密码");
        if(!checkLength(password))throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"密码长度不合法");
    }

    /**
     * @Author fordring
     * @Description  检查密码长度
     * @Date 2020/7/8 12:07
     * @Param [password]
     * @return boolean
     **/
    public boolean checkLength(String password){
        if(password==null)return false;
        return password.length()>=3&&password.length()<=128;
    }
}
