package icu.fordring.voter.dao;

import icu.fordring.voter.mapper.LikeImageMapper;
import icu.fordring.voter.pojo.LikeImage;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName LikeImageDao
 * @Author fordring
 * @date 2020.07.18 16:07
 */
@Component
@Slf4j
public class LikeImageDao {
    @Resource
    private LikeImageMapper likeImageMapper;
    /**
     * @Author fordring
     * @Description  添加一个喜欢
     * @Date 2020/7/18 16:11
     * @Param [likeImage]
     * @return void
     **/
    @Transactional
    public void insert(LikeImage likeImage){
        log.info("用户{} like 图片{}",likeImage.getOwner().getId(),likeImage.getImage().getId());
        if(likeImageMapper.insert(likeImage)!=1)throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"添加喜欢失败");
    }

    @Transactional
    public void deleteByLink(String uid,String iid){
        log.info("用户{} dislike 图片{}",uid,iid);
        if(likeImageMapper.deleteByLink(uid,iid)<=0)throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"删除喜欢失败");
    }
    @Transactional
    public void deleteById(String lid){
        log.info("删除likeImage记录{}",lid);
        if(likeImageMapper.deleteById(lid)!=1)throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"删除喜欢失败");
    }
    public int countByImage(String iid){
        log.info("正在查找喜欢[{}]的人数",iid);
        return likeImageMapper.countByImage(iid);
    }
    public List<LikeImage> selectByImage(String iid){
        log.info("查找喜欢图片[{}]的所有用户",iid);
        return likeImageMapper.selectByImage(iid);
    }
    public List<LikeImage> selectByUser(String name){
        log.info("查找用户[{}]喜欢的所有图片",name);
        return likeImageMapper.selectByUser(name);
    }
}
