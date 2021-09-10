package com.vanwei.tech.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.vanwei.tech.annotation.Log;
import com.vanwei.tech.dto.request.RoleQueryRequestDTO;
import com.vanwei.tech.dto.request.RoleRequestDTO;
import com.vanwei.tech.service.RoleService;
import com.vanwei.tech.util.Result;
import com.vanwei.tech.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Role Controller
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 10:28
 */
@Api(value = "角色", tags = "角色")
@ApiSort(2)
@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 1)
    @ApiImplicitParam(name = "name", value = "角色的名称", paramType = "query", dataTypeClass = String.class)
    @Log
    @GetMapping("/actions/list")
    public Result<List<RoleVO>> listRole(@RequestParam(required = false) String name) {
        log.debug("API CALL : List Role.");

        return Result.ok(roleService.listRole(name));
    }

    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 2)
    @Log
    @GetMapping("/actions/query")
    public Result<Page<RoleVO>> listRole(RoleQueryRequestDTO queryDTO) {
        log.debug("API CALL : Query Role.");

        return Result.ok(roleService.listRole(queryDTO));
    }

    @ApiOperation(value = "保存角色", notes = "保存角色")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 3)
    @Log
    @PostMapping("")
    public Result<Map<String, Boolean>> saveRole(@Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("API CALL : Save Role.");

        return Result.ok(roleService.saveRole(roleDTO));
    }

    @ApiOperation(value = "更新角色", notes = "更新角色信息")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 4)
    @ApiImplicitParam(name = "id", value = "角色的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("API CALL : Update Role.");
        return Result.ok(roleService.updateRole(id, roleDTO));
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 5)
    @ApiImplicitParam(name = "id", value = "角色的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @DeleteMapping("/{id}")
    public Result<Map<String, Boolean>> removeRole(@PathVariable Long id) {
        log.debug("API CALL : Remove Role.");

        return Result.ok(roleService.removeRole(id));
    }
}
