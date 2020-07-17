package icu.fordring.voter.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import java.awt.*;
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
        Image image = Toolkit.getDefaultToolkit().createImage(img);
        return Thumbnails.of(toBufferedImage(image)).scale(scale).outputQuality(quality).asBufferedImage();
    }

    /**
     * @Author fordring
     * @Description  防止图片偏色重绘图片
     * @Date 2020/7/17 10:49
     * @Param [image]
     * @return java.awt.image.BufferedImage
     **/
    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

}
