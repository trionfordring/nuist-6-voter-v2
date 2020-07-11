package icu.fordring.voter.config;

import icu.fordring.voter.component.user.LogoutSuccessHandler;
import icu.fordring.voter.profile.ApplicationProfile;
import icu.fordring.voter.profile.AuthorityProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description spring-security的配置类
 * @ClassName SecurityConfig
 * @Author fordring
 * @date 2020.07.03 20:12
 */
@ConditionalOnProperty("app.security.enable")
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "loginSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource(name = "loginFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource(name = "userVerifier")
    private UserDetailsService userDetailsService;
    @Resource
    private AuthorityProfile authorityProfile;
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private ApplicationProfile applicationProfile;
    @Resource
    private PersistentTokenRepository tokenRepository;
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(applicationProfile.isInitDatabaseWhenStart());
        return tokenRepository;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(CorsConfigurationSource()).and()
                .csrf().disable()
                .formLogin()
                .loginPage("/error/unauthorized").loginProcessingUrl("/user/login")
                .passwordParameter("password").usernameParameter("name")
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .and().rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3153600)
                .tokenRepository(tokenRepository)
                .and().anonymous()
                .authorities(
                        authorityProfile.getDefaultAuthorities()
                )
                .and().logout().logoutUrl("/user/logout").logoutSuccessHandler(logoutSuccessHandler)
        ;
    }
    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(7200l);
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
}
