package com.vanwei.tech.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vanwei.tech.annotation.AnonymousAccess;
import com.vanwei.tech.annotation.Log;
import com.vanwei.tech.dto.UserDTO;
import com.vanwei.tech.dto.request.UserLoginRequestDTO;
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
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @Log
    @GetMapping("/actions/list")
    public Result<IPage<UserVO>> listUser(Page page, UserRequestDTO userRequestDTO) {
        log.debug("API CALL : List User.");

        return Result.ok(userService.listUser(page, userRequestDTO));
    }

    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    @Log
    @GetMapping("/info")
    public Result<UserDTO> getUserInfo() {
        log.debug("API CALL : Get Current User Info.");

        return Result.ok(userService.getUser());
    }

    @ApiOperation(value = "查询用户信息", notes = "根据账号查询用户信息")
    @ApiImplicitParam(name = "username", value = "账号", paramType = "query", required = true)
    @Log
    @GetMapping("/info/actions/get")
    public Result<UserDTO> getUserInfo(@RequestParam("username") String username) {
        log.info("API CALL : Get User Info By Username.");

        return Result.ok(userService.getUser(username));
    }

    @ApiOperation(value = "登录", notes = "用户登录")
    @Log
    @AnonymousAccess
    @PostMapping("/actions/login")
    public Result<UserInfoVO> login(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        log.info("API CALL : User Login.");

        return Result.ok(userService.login(userLoginRequestDTO));
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @Log
    @PostMapping
    public Result<Map<String, Boolean>> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.debug("API CALL : Save User.");

        return Result.ok(userService.saveUser(userRequestDTO));
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParam(name = "id", value = "用户的ID", paramType = "path", required = true)
    @Log
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.debug("API CALL : Update User.");

        return Result.ok(userService.updateUser(id, userRequestDTO));
    }
}
