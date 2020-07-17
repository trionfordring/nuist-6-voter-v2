package icu.fordring.voter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.fordring.voter.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName ResponseUtils
 * @Author fordring
 * @date 2020.07.06 12:59
 */
@Component
@Slf4j
public class ResponseUtils {
    @Resource
    private ObjectMapper objectMapper;
    public void writeResult(Result<?> result, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(result.getStatus());
        Writer writer = httpServletResponse.getWriter();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
    /**
     * @Author fordring
     * @Description  输出图片
     * @Date 2020/7/17 10:52
     * @Param [image, response]
     * @return void
     **/
    public void writeImage(BufferedImage image,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
    /**
     * @Author fordring
     * @Description  输出压缩后的图片
     * @Date 2020/7/17 11:06
     * @Param [img, scale, quality, response]
     * @return void
     **/
    public void showImage(byte[] img,double scale,double quality,HttpServletResponse response){
        BufferedImage image;
        try {
            image = ImageCompressor.compress(img,scale,quality);
            this.writeImage(image,response);
        } catch (IOException e) {
            log.error("在压缩和传输图片时出错：{}",e.getMessage());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"图片传输失败");
        }
    }
}
