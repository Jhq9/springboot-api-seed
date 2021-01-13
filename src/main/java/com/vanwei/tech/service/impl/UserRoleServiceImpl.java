package com.vanwei.tech.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vanwei.tech.entity.UserRole;
import com.vanwei.tech.mapper.UserRoleMapper;
import com.vanwei.tech.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User Role Service
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/5 13:31
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public boolean saveUserRole(Long[] roleIds, Long userId) {
        List<UserRole> userRoleList = Arrays.stream(roleIds)
                .map(roleId -> setUserRole(userId, roleId))
                .collect(Collectors.toList());

        return this.saveBatch(userRoleList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean updateUserRole(Long[] roleIds, Long userId) {
        List<UserRole> userRoleList = this.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        List<Long> removingRoleIdList = userRoleList.stream()
                .filter(userRole -> Arrays.stream(roleIds).noneMatch(roleId -> Objects.equals(roleId, userRole.getRoleId())))
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        List<UserRole> savingUserRoleList = Arrays.stream(roleIds)
                .filter(roleId -> userRoleList.stream().noneMatch(userRole -> Objects.equals(userRole.getRoleId(), roleId)))
                .map(roleId -> setUserRole(userId, roleId))
                .collect(Collectors.toList());

        if (!removingRoleIdList.isEmpty()) {
            this.remove(Wrappers.<UserRole>lambdaQuery().and(i -> i.eq(UserRole::getUserId, userId).in(UserRole::getRoleId, removingRoleIdList)));
        }
        if (!savingUserRoleList.isEmpty()) {
            this.saveBatch(savingUserRoleList, DEFAULT_BATCH_SIZE);
        }

        return true;
    }

    @Override
    public boolean removeUserRole(Long userId) {
        return this.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
    }

    /**
     * Set User Role
     *
     * @param userId User ID
     * @param roleId Role ID
     * @return User Role
     */
    private UserRole setUserRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();

        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        return userRole;
    }

}
