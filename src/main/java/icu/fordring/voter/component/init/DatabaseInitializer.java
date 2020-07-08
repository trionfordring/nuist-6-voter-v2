package icu.fordring.voter.component.init;

import icu.fordring.voter.mapper.AuthorityMapper;
import icu.fordring.voter.mapper.DevMapper;
import icu.fordring.voter.mapper.RoleAuthorityMapper;
import icu.fordring.voter.mapper.RoleMapper;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 初始化数据库
 * @ClassName DatabaseInitializer
 * @Author fordring
 * @date 2020.07.07 21:20
 */
@Component
@Slf4j
@ConditionalOnProperty("app.init-database-when-start")
public class DatabaseInitializer {
    @Resource
    private DevMapper devMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private AuthorityMapper authorityMapper;
    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

    public DatabaseInitializer(){
        log.info("载入[DatabaseInitializer],即将重置数据库");
    }
    
    /**
     * @Author fordring
     * @Description  用于重置数据库
     * @Date 2020/7/7 22:18
     * @Param []
     * @return void
     **/
    @PostConstruct
    public void resetDataBase(){
        long time = System.currentTimeMillis();
        log.warn("==========[现在开始重置数据库]==========");
        try{
            log.warn("  ==========[重置权限表]==========  ");
            initAuthorityTable();
            log.warn("  ==========[重置角色表]==========  ");
            initRoleTable();
            log.warn("  =====[重置[角色-权限]关联表]=====  ");
            initRoleAuthority();
            log.warn("  ==========[重置用户表]==========  ");
            initUserTable();
            log.warn("  ==========[重置头像表]==========  ");
            initHeadResourceTable();
            log.warn("  =====[重置[用户-角色]关联表]=====  ");
            initUserRoleTable();
        }catch (RuntimeException e){
            log.error("!=====================================!");
            log.error(" 重置数据库耗时:{}ms",System.currentTimeMillis()-time);
            log.error("!=========[  重置数据库失败  ]=========!");
            throw e;
        }
        log.warn("=======================================");
        log.warn(" 重置数据库耗时:{}ms",System.currentTimeMillis()-time);
        log.warn("==========[  重置数据库成功  ]==========");
    }

    /**
     * @Author fordring
     * @Description  重置权限表
     * @Date 2020/7/7 22:18
     * @Param []
     * @return void
     **/
    private void initAuthorityTable(){
        log.warn("正在重置权限表");
        try {
            devMapper.createAuthority();
        }catch (Exception e){
            throw new RuntimeException("重置权限表失败",e.getCause());
        }
        log.warn("正在初始化权限表");
        try {
            devMapper.initAuthority();
        }catch (Exception e){
            throw new RuntimeException("初始化权限表失败",e.getCause());
        }
        log.warn("初始化权限表成功");
    }
    
    /**
     * @Author fordring
     * @Description  重置角色表
     * @Date 2020/7/7 22:18
     * @Param []
     * @return void
     **/
    private void initRoleTable(){
        log.warn("正在重置角色表");
        try {
            devMapper.createRole();
        }catch (Exception e){
            throw new RuntimeException("重置角色表失败");
        }
        log.warn("正在初始化角色表");
        try {
            devMapper.initRole();
        }catch (Exception e){
            throw new RuntimeException("初始化角色表失败",e.getCause());
        }
        log.warn("初始化角色表成功");
    }
    /**
     * @Author fordring
     * @Description  重置[角色-权限]关联表
     * @Date 2020/7/7 22:22
     * @Param []
     * @return void
     **/
    private void initRoleAuthority(){
        log.warn("正在重置[角色-权限]关联表");
        try {
            devMapper.createRoleAuthority();
        }catch (Exception e){
            throw new RuntimeException("重置[角色-权限]关联表失败",e.getCause());
        }
        log.warn("正在初始化[角色-权限]关联表");
        try {
            log.warn(" ---开始赋予角色默认权限---");
            List<Role> roles = roleMapper.selectAll();
            List<Authority> authorities = authorityMapper.selectAll();
            for(Role role:roles){
                for (Authority authority:authorities){
                    if(authority.getLevel()>=0&&role.getLevel()>=authority.getLevel()){
                        roleAuthorityMapper.insert(role.getId(),authority.getId());
                        log.info("已添加权限[{}]-[{}]",role.getDescription(),authority.getDescription());
                    }
                }
            }
            log.warn(" ---赋予角色默认权限完成---");
        }catch (Exception e){
            throw new RuntimeException("初始化[角色-权限]关联表失败",e.getCause());
        }
        log.warn("初始化[角色-权限]关联表成功");
    }

    /**
     * @Author fordring
     * @Description  重置用户表
     * @Date 2020/7/7 22:30
     * @Param []
     * @return void
     **/
    private void initUserTable(){
        log.warn("正在重置用户表");
        try {
            devMapper.createUser();
        }catch (Exception e){
            throw new RuntimeException("重置用户表失败");
        }
        log.warn("重置用户表成功");
    }

    /**
     * @Author fordring
     * @Description  重置用户头像表
     * @Date 2020/7/7 22:29
     * @Param []
     * @return void
     **/
    private void initHeadResourceTable(){
        log.warn("正在重置用户头像表");
        try {
            devMapper.createHeadResource();
        }catch (Exception e){
            throw new RuntimeException("重置用户头像表失败");
        }
        log.warn("重置用户头像表成功");
    }

    /**
     * @Author fordring
     * @Description  重置[用户-角色]关联表
     * @Date 2020/7/7 22:31
     * @Param []
     * @return void
     **/
    private void initUserRoleTable(){
        log.warn("正在重置[用户-角色]关联表");
        try {
            devMapper.createUserRole();
        }catch (Exception e){
            throw new RuntimeException("重置[用户-角色]关联表失败");
        }
        log.warn("重置[用户-角色]关联表成功");
    }
}
