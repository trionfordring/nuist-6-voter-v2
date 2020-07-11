package icu.fordring.voter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Description
 * @ClassName Role
 * @Author fordring
 * @date 2020.07.03 21:02
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    private Set<User> users;
    private int level;
    private Set<Authority> authorities=new HashSet<>();

    public Role(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name,role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
