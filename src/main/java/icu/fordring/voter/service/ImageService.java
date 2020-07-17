package icu.fordring.voter.service;

import icu.fordring.voter.component.image.ImageFactory;
import icu.fordring.voter.dao.ImageDao;
import icu.fordring.voter.dao.PlateDao;
import icu.fordring.voter.dto.image.ImageDto;
import icu.fordring.voter.dto.image.ImageListDto;
import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.utils.AuthorityUtils;
import icu.fordring.voter.utils.FileUtils;
import icu.fordring.voter.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @ClassName ImageService
 * @Author fordring
 * @date 2020.07.16 16:17
 */
@Slf4j
@Service
public class ImageService {
    @Resource
    private ImageFactory imageFactory;
    @Resource
    private PlateDao plateDao;
    @Resource
    private ImageDao imageDao;
    @Resource
    private ResponseUtils responseUtils;
    /**
     * @Author fordring
     * @Description  新建一个image
     * @Date 2020/7/16 16:35
     * @Param [name, pid, description, image]
     * @return icu.fordring.voter.dto.image.ImageDto
     **/
    public ImageDto insert(String name, String pid, String description, MultipartFile image){
        if(!FileUtils.verifyFileType(image.getContentType(),"image/*"))throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"文件格式错误,文件应该是一个图片文件");
        if(image.getSize()>1024*1024*15)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"文件过大，图片文件上传大小限制为15MB");
        Image i = imageFactory.newImage(name,description);
        i.setOwner(AuthorityUtils.getSelf());
        i.setPlate(plateDao.selectPlateById(pid));
        try {
            if(image!=null)i.setResource(image.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"传输图像数据时发生异常");
        }
        imageDao.insert(i);
        return new ImageDto(i);
    }
    /**
     * @Author fordring
     * @Description  通过id检索image
     * @Date 2020/7/16 16:36
     * @Param [id]
     * @return icu.fordring.voter.dto.image.ImageDto
     **/
    public ImageDto selectById(String id){
        Image image = imageDao.selectById(id);
        return new ImageDto(image);
    }
    /**
     * @Author fordring
     * @Description  展示图片
     * @Date 2020/7/17 10:29
     * @Param [id]
     * @return void
     **/
    public void show(String id,double scale,double quality, HttpServletResponse response) {
        byte[] res = imageDao.getResource(id);
        responseUtils.showImage(res,scale,quality,response);
    }
    /**
     * @Author fordring
     * @Description  通过pid查询板块下的所有image
     * @Date 2020/7/17 11:07
     * @Param [pid]
     * @return icu.fordring.voter.dto.image.ImageListDto
     **/
    public ImageListDto getAll(String pid){
        List<Image> imageList = imageDao.getAll(pid);
        return new ImageListDto(imageList);
    }
}
