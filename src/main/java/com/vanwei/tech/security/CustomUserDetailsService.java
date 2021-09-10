/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanwei.tech.security;

import cn.hutool.core.collection.CollectionUtil;
import com.vanwei.tech.mapper.UserMapper;
import com.vanwei.tech.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.vanwei.tech.constant.CommonConstants.STATUS_NORMAL;

/**
 * 用户详细信息
 *
 * @author lengleng
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        // FIXME Add Cache
        UserVO user = userMapper.selectOneByUsername(username);
        UserDetails userDetails = getUserDetails(user);

        return userDetails;
    }

    /**
     * 构建userdetails
     *
     * @param user 用户信息
     * @return userdetails
     */
    private UserDetails getUserDetails(UserVO user) {
        Set<String> permissionSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(user.getRoleList())) {
            // 获取角色
            user.getRoleList().forEach(role -> permissionSet.add(role.getName()));
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(permissionSet.toArray(new String[0]));

        // 构造security用户
        return new SecurityUser(user.getId(), 0L, user.getMobile(), user.getEmail(), user.getStatus(), user.getLastLoginTime(),
                user.getCreateTime(), user.getUsername(), user.getPassword(), Objects.equals(user.getStatus(), STATUS_NORMAL),
                true, true, true, user.getRoleList(), authorities);
    }

}
