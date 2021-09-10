package com.vanwei.tech.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.vanwei.tech.annotation.AnonymousAccess;
import com.vanwei.tech.annotation.Log;
import com.vanwei.tech.dto.UserDTO;
import com.vanwei.tech.dto.request.UserLoginRequestDTO;
import com.vanwei.tech.dto.request.UserQueryRequestDTO;
import com.vanwei.tech.dto.request.UserRequestDTO;
import com.vanwei.tech.service.UserService;
import com.vanwei.tech.util.Result;
import com.vanwei.tech.vo.UserInfoVO;
import com.vanwei.tech.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * User Controller
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 10:26
 */
@Api(value = "用户", tags = "用户")
@ApiSort(1)
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 1)
    @Log
    @GetMapping("/actions/list")
    public Result<Page<UserVO>> listUser(UserQueryRequestDTO queryDTO) {
        log.debug("API CALL : List User.");

        return Result.ok(userService.listUser(queryDTO));
    }

    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 2)
    @Log
    @GetMapping("/info")
    public Result<UserDTO> getUserInfo() {
        log.debug("API CALL : Get Current User Info.");

        return Result.ok(userService.getUser());
    }

    @ApiOperation(value = "查询用户信息", notes = "根据账号查询用户信息")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 3)
    @ApiImplicitParam(name = "username", value = "账号", paramType = "query", dataTypeClass = String.class, required = true)
    @Log
    @GetMapping("/info/actions/get")
    public Result<UserDTO> getUserInfo(@RequestParam("username") String username) {
        log.info("API CALL : Get User Info By Username.");

        return Result.ok(userService.getUser(username));
    }

    @ApiOperation(value = "登录", notes = "用户登录")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 4)
    @Log
    @AnonymousAccess
    @PostMapping("/actions/login")
    public Result<UserInfoVO> login(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        log.info("API CALL : User Login.");

        return Result.ok(userService.login(userLoginRequestDTO));
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 5)
    @Log
    @PostMapping
    public Result<Map<String, Boolean>> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.debug("API CALL : Save User.");

        return Result.ok(userService.saveUser(userRequestDTO));
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 6)
    @ApiImplicitParam(name = "id", value = "用户的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.debug("API CALL : Update User.");

        return Result.ok(userService.updateUser(id, userRequestDTO));
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiOperationSupport(author = "1044038055@qq.com", order = 7)
    @ApiImplicitParam(name = "id", value = "用户的ID", paramType = "path", dataTypeClass = Long.class, required = true)
    @Log
    @DeleteMapping("/{id}")
    public Result<Map<String, Boolean>> removeUser(@PathVariable Long id) {
        log.info("API CALL : Remove User.");

        return Result.ok(userService.removeUser(id));
    }
}
