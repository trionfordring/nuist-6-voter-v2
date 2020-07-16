package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.plate.PlateDto;
import icu.fordring.voter.dto.plate.PlateListDto;
import icu.fordring.voter.service.PlateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 板块管理
 * @ClassName PlateController
 * @Author fordring
 * @date 2020.07.14 16:47
 */
@RequestMapping("/plate")
@RestController
@Api(tags = "板块")
public class PlateController {
    @Resource
    private PlateService plateService;

    @PreAuthorize("hasAuthority('PLATE_QUERY')")
    @ApiOperation(value = "通过板块的id查询板块信息",notes = "[PLATE_QUERY]")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result<PlateDto> selectById(
            @PathVariable("id") @ApiParam("板块的唯一标识符") String id
    ){
        return new Result<>(HttpStatus.OK,plateService.selectById(id),"查询成功");
    }

    @PreAuthorize("hasAuthority('PLATE_CREATE')")
    @ApiOperation(value = "新建板块",notes = "[PLATE_CREATE]")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result<PlateDto> createPlate(
            @RequestParam("name") @ApiParam("板块名") String name,
            @RequestParam(value = "description",required = false) @ApiParam(value = "简介") String description,
            @RequestParam(value = "startTime",required = false) @ApiParam(value = "起始时间戳",example = "0") Long startTime,
            @RequestParam(value = "endTime",required = false) @ApiParam(value = "结束时间戳",example = "1") Long endTime
    ){
        PlateDto plateDto = plateService.create(name,description,startTime,endTime);
        return new Result<>(HttpStatus.OK,plateDto,"新建成功");
    }
    @PreAuthorize("hasAuthority('PLATE_QUERY')")
    @ApiOperation(value = "查询全部板块的简单信息",notes = "[PLATE_QUERY]")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result<PlateListDto> selectAll(){
        return new Result<>(HttpStatus.OK,plateService.selectAll(),"查询成功");
    }
}
