package icu.fordring.voter.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @Description 图片压缩工具
 * @ClassName ImageCompressor
 * @Author fordring
 * @date 2020.07.16 19:01
 */
public class ImageCompressor {
    /**
     * @Author fordring
     * @Description  压缩图像
     * @Date 2020/7/16 19:06
     * @Param [img, scale, quality]
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage compress(byte[] img,double scale,double quality) throws IOException {
        return Thumbnails.of(new ByteArrayInputStream(img)).scale(scale).outputQuality(quality).asBufferedImage();
    }
}
