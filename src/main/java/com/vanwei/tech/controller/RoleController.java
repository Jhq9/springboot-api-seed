package com.vanwei.tech.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vanwei.tech.annotation.Log;
import com.vanwei.tech.dto.request.RoleRequestDTO;
import com.vanwei.tech.service.RoleService;
import com.vanwei.tech.util.Result;
import com.vanwei.tech.vo.RoleVO;
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
@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @ApiImplicitParam(name = "name", value = "角色的名称", paramType = "query", dataTypeClass = String.class)
    @Log
    @GetMapping("/actions/list")
    public Result<List<RoleVO>> listRole(@RequestParam(required = false) String name) {
        log.debug("API CALL : List Role.");

        return Result.ok(roleService.listRole(name));
    }

    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表")
    @Log
    @GetMapping("/actions/query")
    public Result<IPage<RoleVO>> listRole(Page page, RoleRequestDTO roleDTO) {
        log.debug("API CALL : Query Role.");

        return Result.ok(roleService.listRole(page, roleDTO));
    }

    @ApiOperation(value = "保存角色", notes = "保存角色")
    @Log
    @PostMapping("")
    public Result<Map<String, Boolean>> saveRole(@Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("API CALL : Save Role.");

        return Result.ok(roleService.saveRole(roleDTO));
    }

    @ApiOperation(value = "更新角色", notes = "更新角色信息")
    @ApiImplicitParam(name = "id", value = "角色的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO roleDTO) {
        log.debug("API CALL : Update Role.");
        return Result.ok(roleService.updateRole(id, roleDTO));
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @DeleteMapping("/{id}")
    public Result<Map<String, Boolean>> removeRole(@PathVariable Long id) {
        log.debug("API CALL : Remove Role.");

        return Result.ok(roleService.removeRole(id));
    }
}
