package icu.fordring.voter.dao;

import icu.fordring.voter.component.plate.PlateFactory;
import icu.fordring.voter.mapper.PlateMapper;
import icu.fordring.voter.pojo.Plate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description 板块持久化层
 * @ClassName PlateDao
 * @Author fordring
 * @date 2020.07.14 17:48
 */
@Component
@Slf4j
public class PlateDao {
    @Resource
    private PlateFactory plateFactory;
    @Resource
    private PlateMapper plateMapper;
    /**
     * @Author fordring
     * @Description  创建一个板块
     * @Date 2020/7/14 17:49
     * @Param [name, description, startTime, endTime]
     * @return void
     **/
    public Plate createPlate(String name, String description, Date startTime,Date endTime){
        log.info("创建板块{}",name);
        Plate plate = plateFactory.newPlate(name,description,startTime,endTime);
        if(plateMapper.insert(plate)!=1){
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"新建板块时出现异常");
        }
        return plate;
    }
    /**
     * @Author fordring
     * @Description  通过id查找板块
     * @Date 2020/7/15 12:36
     * @Param [id]
     * @return icu.fordring.voter.pojo.Plate
     **/
    public Plate selectPlateById(String id){
        log.info("正在查找板块[{}]",id);
        Plate plate = plateMapper.selectById(id);
        return plate;
    }
    /**
     * @Author fordring
     * @Description  查询所有板块信息
     * @Date 2020/7/15 20:03
     * @Param []
     * @return java.util.List<icu.fordring.voter.pojo.Plate>
     **/
    public List<Plate> selectAll(){
        log.info("正在查询所有板块信息");
        List<Plate> plateList = plateMapper.selectAll();
        return plateList;
    }
}
