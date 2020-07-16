package icu.fordring.voter.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.component.plate.PlateFactory;
import icu.fordring.voter.pojo.Plate;
import icu.fordring.voter.service.UserService;
import icu.fordring.voter.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @ClassName PlateMapperTest
 * @Author fordring
 * @date 2020.07.15 11:51
 */
@SpringBootTest
@Slf4j
public class PlateMapperTest {
    @Resource
    private PlateMapper plateMapper;
    @Resource
    private PlateFactory plateFactory;
    @Resource
    private UserService userService;
    @Resource
    private ObjectMapper objectMapper;
    @Test
    public void testInsert(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        userService.register("test_"+ StringUtils.UUID(),"213123", request);
        Plate plate = plateFactory.newPlate("测试","用于测试的板块",new Date(),new Date(new Date().getTime()+(60*60*1000)));
        plateMapper.insert(plate);
    }
    @Test
    public void testSelectById() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        userService.register("test_"+ StringUtils.UUID(),"123456", request);
        Plate plate = plateFactory.newPlate("测试","用于测试的板块",new Date(),new Date(new Date().getTime()+(60*60*1000)));
        plateMapper.insert(plate);
        log.warn("\n{}",StringUtils.formatJson(objectMapper.writeValueAsString(plateMapper.selectById(plate.getId()))));
    }
}
