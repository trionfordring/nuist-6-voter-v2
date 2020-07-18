package icu.fordring.voter.service;

import icu.fordring.voter.component.like.LikeImageFactory;
import icu.fordring.voter.dao.ImageDao;
import icu.fordring.voter.dao.LikeImageDao;
import icu.fordring.voter.dto.image.ImageDto;
import icu.fordring.voter.dto.image.ImageListDto;
import icu.fordring.voter.dto.user.UserDto;
import icu.fordring.voter.dto.user.UserListDto;
import icu.fordring.voter.pojo.Image;
import icu.fordring.voter.pojo.LikeImage;
import icu.fordring.voter.pojo.User;
import icu.fordring.voter.utils.AuthorityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @ClassName LikeImageService
 * @Author fordring
 * @date 2020.07.18 16:20
 */
@Service
public class LikeImageService {
    @Resource
    private LikeImageDao likeImageDao;
    @Resource
    private LikeImageFactory likeImageFactory;
    @Resource
    private ImageDao imageDao;
    /**
     * @Author fordring
     * @Description  喜欢一张图片
     * @Date 2020/7/18 16:42
     * @Param [iid]
     * @return void
     **/
    public void like(String iid){
        User user = AuthorityUtils.getSelf();
        Image image = imageDao.selectById(iid);
        if(user==null||user.getId()==null)throw new HttpClientErrorException(HttpStatus.FORBIDDEN,"用户登录状态异常，建议重新登录");
        if(image==null||image.getId()==null)throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"图片不存在");
        LikeImage likeImage = likeImageFactory.newLikeImage(user,image);
        likeImageDao.insert(likeImage);
    }
    /**
     * @Author fordring
     * @Description  不喜欢一张图片
     * @Date 2020/7/18 16:45
     * @Param [iid]
     * @return void
     **/
    public void dislike(String iid){
        likeImageDao.deleteByLink(Objects.requireNonNull(AuthorityUtils.getSelf()).getId(),iid);
    }
    /**
     * @Author fordring
     * @Description  查找喜欢一张图片的所有用户信息
     * @Date 2020/7/18 16:55
     * @Param [iid]
     * @return icu.fordring.voter.dto.user.UserListDto
     **/
    public UserListDto selectUsersByLike(String iid){
        List<LikeImage> list = likeImageDao.selectByImage(iid);
        List<UserDto> users = new ArrayList<>();
        for(LikeImage likeImage:list){
            users.add(new UserDto(likeImage.getOwner()));
        }
        UserListDto userListDto = new UserListDto();
        userListDto.setUsers(users);
        return userListDto;
    }
    /**
     * @Author fordring
     * @Description  查找一个用户喜欢的所有图片
     * @Date 2020/7/18 17:00
     * @Param [uid]
     * @return icu.fordring.voter.dto.image.ImageListDto
     **/
    public ImageListDto selectImageByLike(String name){
        List<LikeImage> list = likeImageDao.selectByUser(name);
        List<ImageDto> images = new ArrayList<>();
        for(LikeImage likeImage:list){
            images.add(new ImageDto(likeImage.getImage()));
        }
        ImageListDto imageListDto = new ImageListDto();
        imageListDto.setImages(images);
        return imageListDto;
    }
    /**
     * @Author fordring
     * @Description  查找当前用户所喜欢的所有图片
     * @Date 2020/7/18 17:05
     * @Param []
     * @return icu.fordring.voter.dto.image.ImageListDto
     **/
    public ImageListDto selectSelfLikes(){
        return selectImageByLike(Objects.requireNonNull(AuthorityUtils.getSelf()).getUsername());
    }
}
