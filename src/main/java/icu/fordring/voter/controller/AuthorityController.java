package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.authority.AuthorityListDto;
import icu.fordring.voter.dto.authority.AuthorityNameListDto;
import icu.fordring.voter.service.AuthorityService;
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
 * @ClassName AuthorityController
 * @Author fordring
 * @date 2020.07.05 19:15
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Resource
    private AuthorityService authorityService;

    @PreAuthorize("hasAuthority('AUTHORITY_SELF')")
    @ApiOperation(value = "查询当前用户具有的所有权限信息",notes = "[AUTHORITY_SELF]")
    @RequestMapping(value = "/self",method = RequestMethod.GET)
    public Result<AuthorityListDto> selfAuthority(){
        return new Result<>(HttpStatus.OK,authorityService.getSelfAuthorities(),"查询成功");
    }

    @PreAuthorize("hasAuthority('AUTHORITY_NAMES_SELF')")
    @ApiOperation(value = "查询当前用户具有的所有权限名",notes = "[AUTHORITY_NAMES_SELF]")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result<AuthorityNameListDto> selfAuthorityNames(){
        return new Result<>(HttpStatus.OK,authorityService.getSelfAuthorityNames(),"查询成功");
    }

    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @ApiOperation(value = "为一个角色添加一个权限",notes = "[ROLE_UPDATE]")
    @RequestMapping(value = "/{roleName}/{authName}",method = RequestMethod.POST)
    public Result<Object> addAuthority(
            @PathVariable("roleName") @ApiParam("角色名") String roleName,
            @PathVariable("authName") @ApiParam("权限名") String authName){
        authorityService.addAuthority(roleName,authName);
        return new Result<>("添加成功");
    }
    @PreAuthorize("hasAuthority('AUTHORITY_LIST')")
    @ApiOperation(value = "查询全部的权限",notes = "[AUTHORITY_LIST]")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result<AuthorityListDto> getAll(){
        return new Result<>(HttpStatus.OK,authorityService.getAll(),"查询成功");
    }
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @ApiOperation(value = "为一个角色删除一个权限",notes = "[ROLE_UPDATE]")
    @RequestMapping(value = "/{roleName}/{authName}",method = RequestMethod.DELETE)
    public Result<Object> deleteAuthority(
            @PathVariable("roleName") @ApiParam("角色名") String roleName,
            @PathVariable("authName") @ApiParam("权限名") String authName){
        authorityService.deleteAuthority(roleName,authName);
        return new Result<>("删除成功");
    }
}
