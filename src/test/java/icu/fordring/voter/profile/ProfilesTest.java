package icu.fordring.voter.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName ProfilesTest
 * @Author fordring
 * @date 2020.07.08 13:47
 */
@Slf4j
@SpringBootTest
public class ProfilesTest {
    @Resource
    private ApplicationProfile applicationProfile;
    @Resource
    private AuthorityProfile authorityProfile;
    @Resource
    private DevProfile devProfile;
    @Resource
    private RoleProfile roleProfile;
    @Resource
    private SecurityProfile securityProfile;
    @Resource
    private ObjectMapper objectMapper;
    @Test
    public void testProfiles() throws JsonProcessingException {
        log.warn(objectMapper.writeValueAsString(applicationProfile));
        log.warn(objectMapper.writeValueAsString(authorityProfile));
        log.warn(objectMapper.writeValueAsString(devProfile));
        log.warn(objectMapper.writeValueAsString(roleProfile));
        log.warn(objectMapper.writeValueAsString(securityProfile));
    }
}
