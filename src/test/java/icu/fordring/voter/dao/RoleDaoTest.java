package icu.fordring.voter.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.pojo.Role;
import icu.fordring.voter.profile.RoleProfile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName RoleDaoTest
 * @Author fordring
 * @date 2020.07.08 13:43
 */
@Slf4j
@SpringBootTest
public class RoleDaoTest {
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleProfile roleProfile;
    @Resource
    private ObjectMapper objectMapper;
    @Test
    public void testSelectByName() throws JsonProcessingException {
        log.warn(objectMapper.writeValueAsString(roleProfile));
        Role role = roleDao.getRoleByName(roleProfile.getRootRole());
        log.warn(objectMapper.writeValueAsString(role));
    }
}
