package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.image.ImageListDto;
import icu.fordring.voter.dto.user.UserListDto;
import icu.fordring.voter.service.LikeImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName LikeImageComtroller
 * @Author fordring
 * @date 2020.07.18 21:34
 */
@RestController
@RequestMapping
@Api(tags = "点赞")
public class LikeImageController {
    @Resource
    private LikeImageService likeImageService;
    @PreAuthorize("hasAuthority('IMAGE_LIKE')")
    @ApiOperation(value = "点赞图片",notes = "[IMAGE_LIKE]")
    @RequestMapping(value = "/image/like/{iid}",method = RequestMethod.POST)
    public Result<Object> like(
            @PathVariable("iid") String iid
    ){
        likeImageService.like(iid);
        return new Result<>("点赞成功");
    }
    @PreAuthorize("hasAuthority('IMAGE_LIKE')")
    @ApiOperation(value = "取消点赞",notes = "[IMAGE_LIKE]")
    @RequestMapping(value = "/image/dislike/{iid}",method = RequestMethod.POST)
    public Result<Object> dislike(
            @PathVariable("iid") String iid
    ){
        likeImageService.dislike(iid);
        return new Result<>("取消成功");
    }
    @PreAuthorize("hasAuthority('IMAGE_QUERY')")
    @ApiOperation(value = "获取点赞一张图片的所有用户",notes = "[IMAGE_QUERY]")
    @RequestMapping(value = "/image/like/{iid}",method = RequestMethod.GET)
    public Result<UserListDto> selectUsersByLike(
            @PathVariable("iid") String iid
    ){
        return new Result<>(HttpStatus.OK,likeImageService.selectUsersByLike(iid),"查询成功");
    }
    @PreAuthorize("hasAuthority('USER_INFO')")
    @ApiOperation(value = "获取当前用户所点赞的所有图片",notes = "[USER_INFO]")
    @RequestMapping(value = "/user/likes",method = RequestMethod.GET)
    public Result<ImageListDto> selectImagesByLike(){
        return new Result<>(HttpStatus.OK,likeImageService.selectSelfLikes(),"查询成功");
    }
    @PreAuthorize("hasAuthority('USER_ALL_QUERY')")
    @ApiOperation(value = "获取某个用户点赞的所有图片",notes = "[USER_ALL_QUERY]")
    @RequestMapping(value = "/user/likes/{name}",method = RequestMethod.GET)
    public Result<ImageListDto> selectImagesByLike(
            @PathVariable("name") @ApiParam("用户名") String name
    ){
        return new Result<>(HttpStatus.OK,likeImageService.selectImageByLike(name),"查询成功");
    }
}
