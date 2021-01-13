package com.vanwei.tech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vanwei.tech.entity.UserRole;

/**
 * User Role Service
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 9:39
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 添加用户与角色的关联
     *
     * @param roleIds 角色ID列表
     * @param userId  用户的ID
     * @return 新增的结果
     */
    boolean saveUserRole(Long[] roleIds, Long userId);

    /**
     * 更新用户与角色的关联信息
     *
     * @param roleIds 角色ID列表
     * @param userId  用户ID
     * @return 更新的结果
     */
    boolean updateUserRole(Long[] roleIds, Long userId);

    /**
     * 移除用户与角色关联信息
     *
     * @param userId 用户的ID
     * @return 移除的结果
     */
    boolean removeUserRole(Long userId);
}
