package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.image.ImageDto;
import icu.fordring.voter.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName ImageController
 * @Author fordring
 * @date 2020.07.16 16:38
 */
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
}
