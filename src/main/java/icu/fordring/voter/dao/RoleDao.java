package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.RoleMapper;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description 角色dao
 * @ClassName RoleDao
 * @Author fordring
 * @date 2020.07.08 13:28
 */
@Slf4j
@Component
public class RoleDao {
    @Resource
    private RoleMapper roleMapper;
    /**
     * @Author fordring
     * @Description  通过角色名查找角色(不包含相关的权限)
     * @Date 2020/7/8 13:29
     * @Param [name]
     * @return icu.fordring.voter.pojo.Role
     **/
    public Role getRoleByName(String name){
        log.info("查找角色[{}]",name);
        Role role = roleMapper.selectByName(name);
        return role;
    }
}
