package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.image.ImageDto;
import icu.fordring.voter.dto.image.ImageListDto;
import icu.fordring.voter.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

/**
 * @Description
 * @ClassName ImageController
 * @Author fordring
 * @date 2020.07.16 16:38
 */
@Validated
@Api(tags = "图像")
@RestController
@RequestMapping("/image")
public class ImageController {
    @Resource
    private ImageService imageService;
    @PreAuthorize("hasAuthority('IMAGE_INSERT')")
    @ApiOperation(value = "插入一个image",notes = "[IMAGE_INSERT]")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result<ImageDto> insert(
            @RequestParam(value = "name",required = false) @ApiParam("image名") String name,
            @RequestParam(value = "description",required = false) @ApiParam("简介") String description,
            @RequestParam(value = "pid",required = true) @ApiParam(value = "板块id") String pid,
            @RequestParam(value = "image",required = false) @ApiParam(value = "图片文件(文件大小必须小于等于15MB)") MultipartFile image
    ){
        return new Result<>(HttpStatus.OK,imageService.insert(name,pid,description,image),"添加成功");
    }
    @PreAuthorize("hasAuthority('IMAGE_QUERY')")
    @ApiOperation(value = "通过id查询一个image",notes = "[IMAGE_QUERY]")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result<ImageDto> selectById(
            @PathVariable("id") @ApiParam("id") String id
    ){
        return new Result<>(HttpStatus.OK,imageService.selectById(id),"查询成功");
    }
    @PreAuthorize("hasAuthority('IMAGE_QUERY')")
    @ApiOperation(value = "通过id显示图片",notes = "[IMAGE_QUERY]<br><b>tips:</b>由于特殊原因，quality参数几乎不会影响图片大小，请尽量不要使用quality参数")
    @RequestMapping(value = "/resource/{id}",method = RequestMethod.GET)
    public void getResource(
            @PathVariable(value = "id",required = true)
            @ApiParam(value = "image的id",required = true) String id,
            @RequestParam(value = "scale",required = false,defaultValue = "1")
            @ApiParam(value = "缩放比例(默认为1)",example = "1.0")
            @DecimalMin(value = "0",inclusive = false,message = "缩放比例必须大于{value}")
            @DecimalMax(value = "1",message = "缩放比例必须小于等于{value}") Double scale,
            @RequestParam(value = "quality",required = false,defaultValue = "1")
            @ApiParam(value = "图像质量(默认为1)",example = "1.0")
            @DecimalMin(value = "0",inclusive = false,message = "图像质量必须大于{value}")
            @DecimalMax(value = "1",message = "图像质量必须小于等于{value}")Double quality,
            HttpServletResponse response
    ){
        imageService.show(id,scale,quality,response);
    }
    @PreAuthorize("hasAuthority('IMAGE_QUERY')")
    @ApiOperation(value = "通过板块id查询全部image",notes = "[IMAGE_QUERY]")
    @RequestMapping(value = "/all/{pid}",method = RequestMethod.GET)
    public Result<ImageListDto> getAll(
            @PathVariable("pid") @ApiParam("板块的id") String pid
    ){
        return new Result<>(HttpStatus.OK,imageService.getAll(pid),"查询成功");
    }
}
