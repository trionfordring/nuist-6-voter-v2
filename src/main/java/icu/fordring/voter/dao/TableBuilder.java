package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.*;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 表构建器
 * @ClassName TableBuilder
 * @Author fordring
 * @date 2020.07.13 19:53
 */
@Slf4j
@Component
public class TableBuilder {
    @Resource
    private DevMapper devMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;
    @Resource
    private AuthorityMapper authorityMapper;

    /**
     * @Author fordring
     * @Description  重置权限表
     * @Date 2020/7/7 22:18
     * @Param []
     * @return void
     **/
    public void initAuthorityTable(){
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
    public void initRoleTable(){
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
    public void initRoleAuthority(){
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
            specialRoleAuth();
            log.warn(" ---赋予角色默认权限完成---");
        }catch (Exception e){
            throw new RuntimeException("初始化[角色-权限]关联表失败",e.getCause());
        }
        log.warn("初始化[角色-权限]关联表成功");
    }
    /**
     * @Author fordring
     * @Description  特殊角色初始化
     * @Date 2020/7/14 14:56
     * @Param []
     * @return void
     **/
    private void specialRoleAuth(){
        Role phone = roleMapper.selectByName("PHONE_AUTHORIZED");
        Role email = roleMapper.selectByName("EMAIL_AUTHORIZED");
        Authority pa = authorityMapper.selectByName("PHONE_USER");
        Authority pe = authorityMapper.selectByName("EMAIL_USER");
        roleAuthorityMapper.insert(phone.getId(),pa.getId());
        roleAuthorityMapper.insert(email.getId(),pe.getId());
        log.info("已添加权限[{}]-[{}]",phone.getDescription(),pa.getDescription());
        log.info("已添加权限[{}]-[{}]",email.getDescription(),pe.getDescription());
    }
    /**
     * @Author fordring
     * @Description  重置用户表
     * @Date 2020/7/7 22:30
     * @Param []
     * @return void
     **/
    public void initUserTable(){
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
    public void initHeadResourceTable(){
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
    public void initUserRoleTable(){
        log.warn("正在重置[用户-角色]关联表");
        try {
            devMapper.createUserRole();
        }catch (Exception e){
            throw new RuntimeException("重置[用户-角色]关联表失败");
        }
        log.warn("重置[用户-角色]关联表成功");
    }
    /**
     * @Author fordring
     * @Description  建立会话表
     * @Date 2020/7/11 21:53
     * @Param []
     * @return void
     **/
    public void initSessionTable(){
        log.warn("正在建立会话表");
        try {
            devMapper.createSessionTable();
        }catch (Exception e){
            throw new RuntimeException("建立会话表失败");
        }
    }
    /**
     * @Author fordring
     * @Description  建立[remember-me]表
     * @Date 2020/7/11 21:58
     * @Param []
     * @return void
     **/
    public void initPersistentLoginTable(){
        log.warn("正在建立[remember-me]表");
        try {
            devMapper.createPersistentLoginTable();
        }catch (Exception e){
            throw new RuntimeException("建立[remember-me]表失败");
        }
    }

    /**
     * @Author fordring
     * @Description  建立图片评论表
     * @Date 2020/7/13 19:48
     * @Param []
     * @return void
     **/
    public void initCommentTable(){
        log.warn("正在建立图片评论表");
        try {
            devMapper.createCommentTable();
        }catch (Exception e){
            throw new RuntimeException("建立图片评论表失败");
        }
    }
    /**
     * @Author fordring
     * @Description  建立图片表
     * @Date 2020/7/13 19:49
     * @Param []
     * @return void
     **/
    public void initImageTable(){
        log.warn("正在建立图片表");
        try {
            devMapper.createImageTable();
        }catch (Exception e){
            throw new RuntimeException("建立图片表失败");
        }
    }
    /**
     * @Author fordring
     * @Description  建立图片点赞表
     * @Date 2020/7/13 19:53
     * @Param []
     * @return void
     **/
    public void initLikeImageTable(){
        log.warn("正在建立图片点赞表");
        try {
            devMapper.createLikeImageTable();
        }catch (Exception e){
            throw new RuntimeException("建立图片点赞表失败");
        }
    }
    /**
     * @Author fordring
     * @Description  建立板块表
     * @Date 2020/7/14 13:31
     * @Param []
     * @return void
     **/
    public void initPlateTable(){
        log.warn("正在建立板块表");
        try {
            devMapper.createPlateTable();
        }catch (Exception e){
            throw new RuntimeException("建立板块表失败");
        }
    }
}
