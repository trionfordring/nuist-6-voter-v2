package icu.fordring.voter.controller;

import icu.fordring.voter.dto.Result;
import icu.fordring.voter.dto.role.RoleDetailDto;
import icu.fordring.voter.dto.role.RoleDetailListDto;
import icu.fordring.voter.dto.role.RoleListDto;
import icu.fordring.voter.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @ClassName RoleController
 * @Author fordring
 * @date 2020.07.10 14:01
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_SELF')")
    @ApiOperation(value = "获取当前用户的角色信息",notes = "[ROLE_SELF]")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result<RoleListDto> getRoles(){
        return new Result<>(HttpStatus.OK,roleService.getSelfRoles(),"查询成功");
    }

    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    @ApiOperation(value = "通过name获取角色的详细信息",notes = "[ROLE_QUERY]")
    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public Result<RoleDetailDto> getRoleByName(
            @PathVariable("name") String name
    ){
        return new Result<>(HttpStatus.OK,roleService.getRoleDetail(name),"查询成功");
    }
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    @ApiOperation(value = "查询出所有角色信息(不含对应的权限)",notes = "[ROLE_QUERY]")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result<RoleListDto> getAll(){
        return new Result<>(HttpStatus.OK,roleService.getAll(),"查询成功");
    }
}
