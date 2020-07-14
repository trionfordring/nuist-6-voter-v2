package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.AuthorityMapper;
import icu.fordring.voter.mapper.RoleAuthorityMapper;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName AuthorityDao
 * @Author fordring
 * @date 2020.07.14 15:01
 */

@Slf4j
@Component
public class AuthorityDao {

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;
    @Resource
    private AuthorityMapper authorityMapper;
    /**
     * @Author fordring
     * @Description  为一个角色添加权限
     * @Date 2020/7/14 14:59
     * @Param [role, authority]
     * @return void
     **/
    public void addAuthority(Role role, Authority authority){
        log.info("为角色[{}]添加权限[{}]",role.getDescription(),authority.getDescription());
        roleAuthorityMapper.insert(role.getId(),authority.getId());
    }
    /**
     * @Author fordring
     * @Description  为一个角色删除一个权限
     * @Date 2020/7/14 14:59
     * @Param [role, authority]
     * @return void
     **/
    @Transactional
    public void deleteAuthority(Role role, Authority authority){
        log.info("为角色[{}]删除权限[{}]",role.getDescription(),authority.getDescription());
        roleAuthorityMapper.delete(role.getId(),authority.getId());
    }

    /**
     * @Author fordring
     * @Description  通过权限名查找权限
     * @Date 2020/7/14 15:13
     * @Param [name]
     * @return icu.fordring.voter.pojo.Authority
     **/
    public Authority selectByName(String name){
        log.info("查找权限[{}]",name);
        Authority authority = authorityMapper.selectByName(name);
        return authority;
    }
    /**
     * @Author fordring
     * @Description  查询出所有的权限
     * @Date 2020/7/14 15:34
     * @Param []
     * @return java.util.List<icu.fordring.voter.pojo.Authority>
     **/
    public List<Authority> selectAll(){
        return authorityMapper.selectAll();
    }
}
