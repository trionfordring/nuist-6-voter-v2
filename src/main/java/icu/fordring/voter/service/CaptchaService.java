package icu.fordring.voter.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import icu.fordring.voter.profile.AuthorityProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @Description
 * @ClassName CaptchaService
 * @Author fordring
 * @date 2020.07.13 14:29
 */
@Slf4j
@Service
public class CaptchaService {
    @Resource
    private DefaultKaptcha kaptcha;
    @Resource
    private AuthorityProfile authorityProfile;

    /**
     * @Author fordring
     * @Description  创建一个验证码
     * @Date 2020/7/13 15:50
     * @Param [request]
     * @return java.awt.image.BufferedImage
     **/
    public BufferedImage createCaptcha(HttpSession session){
        String key = kaptcha.createText().toLowerCase();
        Long expirationTime = System.currentTimeMillis()+authorityProfile.getCaptcha().getAliveTime();
        BufferedImage captcha = kaptcha.createImage(key);
        session.setAttribute(authorityProfile.getCaptcha().getCodeKey(),key);
        session.setAttribute(authorityProfile.getCaptcha().getExpirationKey(),expirationTime);
        log.info("创建验证码[{}],过期时间{}",key,new Date(expirationTime));
        return captcha;
    }
    /**
     * @Author fordring
     * @Description  验证图片校验是否通过
     * @Date 2020/7/13 15:54
     * @Param [session]
     * @return void
     **/
    public void verity(String key,HttpSession session){
        log.info("验证码验证中");
        try {
            String realKey = (String) session.getAttribute(authorityProfile.getCaptcha().getCodeKey());
            Long expirationTime = (Long) session.getAttribute(authorityProfile.getCaptcha().getExpirationKey());
            if(realKey==null||expirationTime==null)throw new HttpClientErrorException(HttpStatus.FORBIDDEN,"用户还未请求验证码");
            if(System.currentTimeMillis()>expirationTime)throw new HttpClientErrorException(HttpStatus.GONE,"验证码已过期,请重新请求验证码");
            if(!realKey.equals(key.toLowerCase()))throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"验证码错误");
        }catch (RuntimeException e){
            log.error("用户验证码认证失败");
            throw e;
        }finally {
            session.removeAttribute(authorityProfile.getCaptcha().getExpirationKey());
            session.removeAttribute(authorityProfile.getCaptcha().getCodeKey());
        }
        log.info("验证码认证成功");
    }
}
