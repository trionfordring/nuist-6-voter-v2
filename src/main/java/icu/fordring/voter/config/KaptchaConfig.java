package icu.fordring.voter.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import icu.fordring.voter.profile.AuthorityProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @ClassName KaptchaConfig
 * @Author fordring
 * @date 2020.07.13 14:52
 */
@Slf4j
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha kaptcha(AuthorityProfile authorityProfile){
        log.info("验证码组件初始化");
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(new Config(authorityProfile.getCaptcha().getProperties()));
        return kaptcha;
    }
}
