package icu.fordring.voter.component.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.mapper.RoleMapper;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.profile.ApplicationProfile;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description 程序初始化
 * @ClassName ApplicationInitializer
 * @Author fordring
 * @date 2020.07.08 12:27
 */

@Slf4j
@Component
public class ApplicationInitializer implements Initializer {
    @Resource
    private Initializer databaseInitializer;
    @Resource
    private ApplicationProfile applicationProfile;
    @Resource
    private ObjectMapper objectMapper;
    @Override
    @PostConstruct
    public void init() throws Exception {
        log.info("正在初始化程序");
        log.info("程序配置文件：\n{}",objectMapper.writeValueAsString(applicationProfile));
        if(applicationProfile.isInitDatabaseWhenStart()){
            log.warn("即将初始化数据库");
            databaseInitializer.init();
            log.warn("初始化数据库完成");
        }
        after();
        log.info("程序初始化完成");
    }

    @Override
    public void after() throws Exception {
        if(applicationProfile.isInitDatabaseWhenStart()){
            databaseInitializer.after();
        }
    }
}
