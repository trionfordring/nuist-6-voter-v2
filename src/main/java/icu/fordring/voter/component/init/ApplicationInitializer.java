package icu.fordring.voter.component.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.profile.ApplicationProfile;
import icu.fordring.voter.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    private RoleInitializer roleInitializer;
    @Resource
    private ObjectMapper objectMapper;
    @Override
    @PostConstruct
    public void init() throws Exception {
        long t = System.currentTimeMillis();
        log.info("==================正在初始化程序==================");
        log.info("\n程序配置文件：\napplicationProfile:{}", StringUtils.formatJson(objectMapper.writeValueAsString(applicationProfile)));
        if(applicationProfile.isInitDatabaseWhenStart()){
            log.warn("即将初始化数据库");
            databaseInitializer.init();
            log.warn("初始化数据库完成");
        }
        roleInitializer.init();
        after();
        log.info("==================程序初始化完成==================");
        log.info("  耗时: {} ms",System.currentTimeMillis()-t);
        log.info("=================================================");
    }

    @Override
    public void after() throws Exception {
        if(applicationProfile.isInitDatabaseWhenStart()) databaseInitializer.after();
        roleInitializer.after();
    }
}
