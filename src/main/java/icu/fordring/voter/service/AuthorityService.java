package icu.fordring.voter.service;

import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.dto.authority.AuthorityDto;
import icu.fordring.voter.dto.authority.AuthorityListDto;
import icu.fordring.voter.dto.authority.AuthorityNameListDto;
import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.AuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
}
