package icu.fordring.voter.service;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dao.AuthorityDao;
import icu.fordring.voter.dao.RoleDao;
import icu.fordring.voter.dto.authority.AuthorityListDto;
import icu.fordring.voter.dto.authority.AuthorityNameListDto;
import icu.fordring.voter.dto.role.RoleDetailDto;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.utils.AuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 权限服务
 * @ClassName AuthorityService
 * @Author fordring
 * @date 2020.07.05 19:43
 */
@Slf4j
@Service
public class AuthorityService {
    @Resource
    private AuthorityDao authorityDao;
    @Resource
    private RoleDao roleDao;
    /**
     * @Author fordring
     * @Description  获取当前用户的权限列表
     * @Date 2020/7/5 20:24
     * @Param []
     * @return icu.fordring.voter.dto.authority.AuthorityListDto
     **/
    public AuthorityListDto getSelfAuthorities(){
        UserInfo userInfo = AuthorityUtils.getSelf();
        Set<Authority> authorities = userInfo.getAuthoritiesSet();
        AuthorityListDto authorityListDto = new AuthorityListDto(authorities);
        log.info("用户[name='{}']查询自身权限列表",userInfo.getUsername());
        return authorityListDto;
    }
    
    /**
     * @Author fordring
     * @Description  获取当前用户的权限名列表
     * @Date 2020/7/6 11:38
     * @Param []
     * @return icu.fordring.voter.dto.authority.AuthorityNameListDto
     **/
    public AuthorityNameListDto getSelfAuthorityNames(){
        Set<String> set = new HashSet<>();
        for(GrantedAuthority authority:SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            set.add(authority.getAuthority());
        }
        AuthorityNameListDto authorityNameListDto = new AuthorityNameListDto();
        authorityNameListDto.setAuthorities(set);
        return authorityNameListDto;
    }
    /**
     * @Author fordring
     * @Description  为一个角色添加一个权限
     * @Date 2020/7/14 15:23
     * @Param [roleName, authName]
     * @return void
     **/
    public void addAuthority(String roleName,String authName){
        Authority authority = authorityDao.selectByName(authName);
        Role role = roleDao.getRoleByName(roleName);
        if(authority==null||role==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"角色或权限不存在");
        authorityDao.addAuthority(role,authority);
        log.info("角色[{}]添加权限[{}]",roleName,authName);
    }
    /**
     * @Author fordring
     * @Description  删除一个角色的一个权限
     * @Date 2020/7/14 15:25
     * @Param [roleName, authName]
     * @return void
     **/
    public void deleteAuthority(String roleName,String authName){
        Authority authority = authorityDao.selectByName(authName);
        Role role = roleDao.getRoleByName(roleName);
        if(authority==null||role==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"角色或权限不存在");
        authorityDao.deleteAuthority(role,authority);
        log.info("角色[{}]删除权限[{}]",roleName,authName);
    }
    
    /**
     * @Author fordring
     * @Description  查询所有的权限
     * @Date 2020/7/14 15:35
     * @Param []
     * @return icu.fordring.voter.dto.authority.AuthorityListDto
     **/
    public AuthorityListDto getAll(){
        log.info("正在查询所有的权限");
        AuthorityListDto authorityListDto = new AuthorityListDto(authorityDao.selectAll());
        return authorityListDto;
    }
}
