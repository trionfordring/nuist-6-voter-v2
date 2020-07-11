package icu.fordring.voter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * @Description
 * @ClassName Authority
 * @Author fordring
 * @date 2020.07.03 21:02
 */
@Data
@NoArgsConstructor
public class Authority {
    private Long id;
    private String name;
    private String description;
    private int level;
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(name,authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
