package icu.fordring.voter.dto.plate;

import icu.fordring.voter.pojo.Plate;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description
 * @ClassName PlateListDto
 * @Author fordring
 * @date 2020.07.15 11:32
 */
@ApiModel("板块列表")
@Data
public class PlateListDto {
    private Collection<PlateSimpleDto> plates;

    public PlateListDto(Collection<Plate> plateList){
        this.plates = new ArrayList<>();
        for (Plate plate:plateList){
            this.plates.add(new PlateSimpleDto(plate));
        }
    }
}
