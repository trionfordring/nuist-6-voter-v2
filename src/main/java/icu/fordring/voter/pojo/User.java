package icu.fordring.voter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @ClassName User
 * @Author fordring
 * @date 2020.07.03 20:59
 */
@Data
@Slf4j
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String username;
    protected String password;
    protected String realName;
    protected String studentId;
    protected String email;
    protected String phone;
    protected Boolean enabled;
    protected Date lastPasswordResetDate;
    protected Date createDate;
    protected String head;
    protected String description;
    protected Set<Role> roles = new HashSet<>();
}
