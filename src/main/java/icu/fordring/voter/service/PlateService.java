package icu.fordring.voter.service;

import icu.fordring.voter.dao.PlateDao;
import icu.fordring.voter.dto.plate.PlateDto;
import icu.fordring.voter.dto.plate.PlateListDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.pojo.Plate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @ClassName PlateService
 * @Author fordring
 * @date 2020.07.14 16:52
 */
@Service
public class PlateService {
    @Resource
    private PlateDao plateDao;
    /**
     * @Author fordring
     * @Description  
     * @Date 2020/7/15 12:46
     * @Param [id]
     * @return icu.fordring.voter.dto.plate.PlateDto
     **/
    public PlateDto selectById(String id){
        Plate plate = plateDao.selectPlateById(id);
        if(plate==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"板块不存在");
        PlateDto plateDto = new PlateDto(plate);
        return plateDto;
    }

    /**
     * @Author fordring
     * @Description  创建一个板块
     * @Date 2020/7/15 19:14
     * @Param [name 板块名, description 板块简介, startTime 活动起始时间(null为当前时间), endTime 活动结束时间(null为100年后)]
     * @return void
     **/
    public PlateDto create(String name,String description,Long startTime,Long endTime){
        if(startTime==null)startTime=System.currentTimeMillis();
        if(endTime==null)endTime=System.currentTimeMillis()+100L*365*24*60*60*1000;//写死，如果不填写结束时间，那么结束时间为100年后
        Plate plate = plateDao.createPlate(name,description,new Date(startTime),new Date(endTime));
        return new PlateDto(plate);
    }

    /**
     * @Author fordring
     * @Description  查询全部板块
     * @Date 2020/7/15 20:07
     * @Param []
     * @return icu.fordring.voter.dto.plate.PlateListDto
     **/
    public PlateListDto selectAll(){
        List<Plate> plateList = plateDao.selectAll();
        return new PlateListDto(plateList);
    }
}
