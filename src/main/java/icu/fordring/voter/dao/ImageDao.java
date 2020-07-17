package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.ImageMapper;
import icu.fordring.voter.pojo.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName ImageDao
 * @Author fordring
 * @date 2020.07.16 15:54
 */
@Slf4j
@Component
public class ImageDao {
    @Resource
    private ImageMapper imageMapper;
    /**
     * @Author fordring
     * @Description  新建一条image
     * @Date 2020/7/16 15:59
     * @Param [image]
     * @return void
     **/
    public void insert(Image image){
        log.info("新建image[{}]",image.getId());
        if(imageMapper.insert(image)!=1)throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"插入图片时出现异常");
    }
    /**
     * @Author fordring
     * @Description  查询image
     * @Date 2020/7/16 16:01
     * @Param [id]
     * @return icu.fordring.voter.pojo.Image
     **/
    public Image selectById(String id){
        log.info("查询image[{}]",id);
        return imageMapper.selectById(id);
    }
    
    /**
     * @Author fordring
     * @Description  获取图片资源
     * @Date 2020/7/17 10:43
     * @Param [id]
     * @return byte[]
     **/
    public byte[] getResource(String id){
        log.info("获取image[{}]的资源",id);
        return imageMapper.getResource(id).getResource();
    }
}
