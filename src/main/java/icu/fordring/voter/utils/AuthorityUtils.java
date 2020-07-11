package icu.fordring.voter.utils;

import icu.fordring.voter.bean.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description
 * @ClassName AuthorityUtils
 * @Author fordring
 * @date 2020.07.05 19:55
 */
public class AuthorityUtils {
    public static Authentication getSelfAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static UserInfo getSelf(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserInfo)
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
    public static void setSelf(UserInfo userInfo){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userInfo,userInfo.getPassword(),userInfo.getAuthorities())
        );
    }
}
