package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.comment.CommentDetailsDto;
import icu.fordring.voter.dto.comment.CommentListDto;
import icu.fordring.voter.pojo.Comment;
import icu.fordring.voter.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName CommentController
 * @Author fordring
 * @date 2020.07.18 11:04
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PreAuthorize("hasAuthority('COMMENT_INSERT')")
    @ApiOperation(value = "为图片添加一条评论",notes = "[COMMENT_INSERT]")
    @RequestMapping(value = "/{iid}",method = RequestMethod.POST)
    public Result<CommentDetailsDto> insert(
            @PathVariable("iid")                                @ApiParam("图片id")       String iid,
            @RequestParam(value = "parent",required = false)    @ApiParam("父评论id")     String parent,
            @RequestParam("content")                            @ApiParam("评论内容")     String content
    ){
        return new Result<>(HttpStatus.OK,commentService.insert(iid,parent,content),"添加成功");
    }

    @PreAuthorize("hasAuthority('COMMENT_QUERY')")
    @ApiOperation(value = "通过评论id查询一条评论的详细信息",notes = "[COMMENT_QUERY]")
    @RequestMapping(value = "/details/{id}",method = RequestMethod.GET)
    public Result<CommentDetailsDto> selectDetailsById(
            @PathVariable("id") String id
    ){
        return new Result<>(HttpStatus.OK,commentService.selectById(id),"查询成功");
    }
    @PreAuthorize("hasAuthority('COMMENT_QUERY')")
    @ApiOperation(value = "查询一张图片下的所有评论",
            notes = "[COMMENT_QUERY]<br>" +
                    "<b>约定:</b>此接口将会梳理好评论的时间关系及依赖关系，comments数组将会按照时间升序排序(即越早的评论在越前面)。" +
                    "此接口将会将评论间的依赖关系梳理好，即同级关系在同一个数组，同级子评论在sons数组中。<br>" +
                    "大量数据下时间复杂度:O(n*lg(n))"
    )
    @RequestMapping(value = "/{iid}",method = RequestMethod.GET)
    public Result<CommentListDto> selectAll(
            @PathVariable("iid") String iid
    ){
        return new Result<>(HttpStatus.OK,commentService.selectAll(iid),"查询成功");
    }
}
