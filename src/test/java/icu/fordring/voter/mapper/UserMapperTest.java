package icu.fordring.voter.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.component.role.RoleFactory;
import icu.fordring.voter.component.user.UserFactory;
import icu.fordring.voter.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description 测试UserMapper
 * @ClassName UserMapperTest
 * @Author fordring
 * @date 2020.07.04 16:15
 */
@SpringBootTest
@Slf4j
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserFactory userFactory;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RoleFactory roleFactory;

    public static boolean cleanAfterAll = true;
    @Test
    @Transactional
    public void testInsert() throws JsonProcessingException {
        User user = userFactory.newDefaultUser();
        user.setUsername("test");
        user.setPassword("aaa");
        user.getRoles().add(roleFactory.newDefaultRole("r1"));
        user.getRoles().add(roleFactory.newDefaultRole("r2"));
        user.getRoles().add(roleFactory.newDefaultRole("r3"));
        user.getRoles().add(roleFactory.newDefaultRole("r4"));
        user.getRoles().add(roleFactory.newDefaultRole("r5"));
        Assertions.assertEquals(1,userMapper.insert(user));
        log.info(objectMapper.writeValueAsString(user));
    }
    @Test
    public void testSelectById() throws JsonProcessingException {
        log.warn(objectMapper.writeValueAsString(userMapper.selectById("1")));
    }
    @Test
    public void testSelectByName() throws JsonProcessingException {
        log.warn("\ntestSelectByName:\nuser:{}",objectMapper.writeValueAsString(userMapper.selectByName("aaa")));
    }
    @Test
    public void testUpdateById() throws JsonProcessingException {
        User user = userMapper.selectByName("aaa");
        log.warn("\nSelectByName:\nuser:{}",objectMapper.writeValueAsString(user));
        user.setPassword("109995aaa");
        user.setDescription("11aasewqa1");
        log.warn("\ntestUpdateById:{}\nuser:{}",objectMapper.writeValueAsString(userMapper.updateById(user)),
                objectMapper.writeValueAsString(user));
    }
    @AfterAll
    public static void clean(){

    }
}
