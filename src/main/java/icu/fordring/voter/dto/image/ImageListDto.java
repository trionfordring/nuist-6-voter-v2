package icu.fordring.voter.dto.image;

import icu.fordring.voter.pojo.Image;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description
 * @ClassName ImageListDto
 * @Author fordring
 * @date 2020.07.16 16:06
 */
@Data
@ApiModel("image数组")
public class ImageListDto {
    @ApiModelProperty("image组成的数组")
    protected Collection<ImageDto> images;
    public ImageListDto(Collection<Image> images){
        this.images=new ArrayList<>();
        for(Image image:images){
            this.images.add(new ImageDto(image));
        }
    }
}
