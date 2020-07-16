package icu.fordring.voter.component.plate;

import icu.fordring.voter.pojo.Plate;
import icu.fordring.voter.utils.AuthorityUtils;
import icu.fordring.voter.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @ClassName PlateFactory
 * @Author fordring
 * @date 2020.07.14 17:15
 */
@Component
public class PlateFactory {
    /**
     * @Author fordring
     * @Description  创建一个船星的板块
     * @Date 2020/7/14 17:48
     * @Param [name, description, startTime, endTime]
     * @return icu.fordring.voter.pojo.Plate
     **/
    public Plate newPlate(String name,String description,Date startTime,Date endTime){
        Plate plate = new Plate();
        plate.setId(StringUtils.UUID());
        plate.setCreateTime(new Date());
        plate.setStartTime(startTime);
        plate.setEndTime(endTime);
        plate.setName(name);
        plate.setDescription(description);
        plate.setOwner(AuthorityUtils.getSelf());
        return plate;
    }
}
