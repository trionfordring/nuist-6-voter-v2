package icu.fordring.voter.bean;

import icu.fordring.voter.pojo.Authority;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.pojo.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @ClassName UserInfo
 * @Author fordring
 * @date 2020.07.04 19:36
 */
@Data
@Slf4j
public class UserInfo extends User implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private String realName;
    private String studentId;
    private String email;
    private String phone;
    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;
    private Date lastPasswordResetDate;
    private Date createDate;
    private String head;
    private String description;
    private Set<Role> roles = new HashSet<>();

    public UserInfo(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.realName=user.getRealName();
        this.studentId=user.getStudentId();
        this.email=user.getEmail();
        this.phone=user.getPhone();
        this.enabled=user.getEnabled();
        this.lastPasswordResetDate=user.getLastPasswordResetDate();
        this.createDate=user.getCreateDate();
        this.head=user.getHead();
        this.description=user.getDescription();
        this.roles=user.getRoles();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auths = new HashSet<>();
        Set<Role> roles = this.getRoles();
        for (Role role : roles) {
            for(Authority authority:role.getAuthorities()){
                auths.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }
        return auths;
    }
    public Set<Authority> getAuthoritiesSet(){
        Set<Authority> auths = new HashSet<>();
        Set<Role> roles = this.getRoles();
        for (Role role : roles) {
            for(Authority authority:role.getAuthorities()){
                auths.add(authority);
            }
        }
        return auths;
    }
    public Set<String> getRolesList(){
        Set<String> set = new HashSet<>();
        for(Role role:roles){
            set.add(role.getName());
        }
        return set;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(enabled==null)return false;
        return enabled;
    }
}
