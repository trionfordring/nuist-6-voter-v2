package icu.fordring.voter.service;

import com.google.common.collect.Sets;
import icu.fordring.voter.bean.UserInfo;
import icu.fordring.voter.component.role.RoleFactory;
import icu.fordring.voter.dao.RoleDao;
import icu.fordring.voter.dto.role.RoleDetailDto;
import icu.fordring.voter.dto.role.RoleListDto;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.utils.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 角色服务
 * @ClassName RoleService
 * @Author fordring
 * @date 2020.07.11 12:26
 */
@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;
    
    /**
     * @Author fordring
     * @Description  获取角色的详细信息
     * @Date 2020/7/11 12:29
     * @Param [name]
     * @return icu.fordring.voter.dto.role.RoleDetailDto
     **/
    public RoleDetailDto getRoleDetail(String name){
        Role role = roleDao.getRoleDetailByName(name);
        RoleDetailDto roleDetailDto = new RoleDetailDto(role);
        return roleDetailDto;
    }

    /**
     * @Author fordring
     * @Description  获取用户具有的角色
     * @Date 2020/7/11 12:40
     * @Param []
     * @return icu.fordring.voter.dto.role.RoleListDto
     **/
    public RoleListDto getSelfRoles(){
        UserInfo userInfo = AuthorityUtils.getSelf();
        if(userInfo==null)return new RoleListDto(Sets.newHashSet(RoleFactory.ANONYMOUS_ROLE));
        return new RoleListDto(userInfo.getRoles());
    }
}
