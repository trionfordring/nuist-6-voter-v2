package icu.fordring.voter.component.role;

import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description role工厂类
 * @ClassName RoleFactory
 * @Author fordring
 * @date 2020.07.05 11:46
 */
@Component
public class RoleFactory {


    public Role newDefaultRole(){
        return newDefaultRole(null,null);
    }
    public Role newDefaultRole(String name,String describe){
        Role role = new Role();
        role.setId(StringUtils.UUID());
        role.setName(name);
        role.setDescription(describe);

        return role;
    }
    public Role newDefaultRole(String name){
        return newDefaultRole(name,null);
    }




}
