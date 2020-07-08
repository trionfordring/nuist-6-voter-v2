package icu.fordring.voter.devtool;

import icu.fordring.voter.mapper.AuthorityMapper;
import icu.fordring.voter.mapper.DevMapper;
import icu.fordring.voter.mapper.RoleAuthorityMapper;
import icu.fordring.voter.mapper.RoleMapper;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName Tool
 * @Author fordring
 * @date 2020.07.06 16:47
 */
@SpringBootTest
@Slf4j
public class Tool {
    @Resource
    DevMapper devMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    AuthorityMapper authorityMapper;
    @Resource
    RoleAuthorityMapper roleAuthorityMapper;

    public void resetRole(){
        devMapper.cleanRole();
        devMapper.initRole();
    }

    public void initRoleAuthority(){
        devMapper.createRoleAuthority();
        log.info("创建表[role_authority]完成");
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
    }
}
