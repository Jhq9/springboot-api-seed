package com.vanwei.tech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vanwei.tech.dto.UserDTO;
import com.vanwei.tech.dto.request.UserLoginRequestDTO;
import com.vanwei.tech.dto.request.UserQueryRequestDTO;
import com.vanwei.tech.dto.request.UserRequestDTO;
import com.vanwei.tech.entity.User;
import com.vanwei.tech.vo.UserInfoVO;
import com.vanwei.tech.vo.UserVO;

import java.util.Map;

/**
 * User Service
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 9:39
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户列表
     *
     * @param queryDTO 查询条件信息
     * @return 用户列表
     */
    Page<UserVO> listUser(UserQueryRequestDTO queryDTO);

    /**
     * 查询当前用户
     *
     * @return 当前用户信息
     */
    UserDTO getUser();

    /**
     * 根据账号查询用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    UserDTO getUser(String username);

    /**
     * 用户登录
     *
     * @param userLoginRequestDTO 登录信息
     * @return 用户信息
     */
    UserInfoVO login(UserLoginRequestDTO userLoginRequestDTO);

    /**
     * 添加用户
     *
     * @param userRequestDTO 用户信息
     * @return 添加的结果
     */
    Map<String, Boolean> saveUser(UserRequestDTO userRequestDTO);

    /**
     * 创建默认管理员账号
     *
     * @return 是否创建成功
     */
    boolean initAdmin();

    /**
     * 更新用户信息
     *
     * @param id             用户的ID
     * @param userRequestDTO 用户的信息
     * @return 更新的结果
     */
    Map<String, Boolean> updateUser(Long id, UserRequestDTO userRequestDTO);

    /**
     * 删除用户
     *
     * @param id 用户的ID
     * @return 删除的结果
     */
    Map<String, Boolean> removeUser(Long id);
}
