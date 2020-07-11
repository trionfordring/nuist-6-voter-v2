package icu.fordring.voter.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName RoleMapperTest
 * @Author fordring
 * @date 2020.07.11 12:16
 */
@SpringBootTest
@Slf4j
public class RoleMapperTest {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ObjectMapper objectMapper;
    @Test
    public void testSelectByName() throws JsonProcessingException {
        log.warn("\n{}",objectMapper.writeValueAsString(roleMapper.selectDetailByName("ROOT")));
    }
}
