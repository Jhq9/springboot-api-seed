/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.vanwei.tech.security;

import com.vanwei.tech.dto.RoleDTO;
import lombok.Getter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Security User
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/9/10 14:18
 */
public class SecurityUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private final Long id;

    /**
     * 部门ID FIXME(是否引入部门需要待确定)
     */
    @Getter
    private final Long deptId;

    /**
     * 手机号
     */
    @Getter
    private final String mobile;

    /**
     * 邮箱地址
     */
    @Getter
    private final String email;

    /**
     * 用户的状态值
     */
    @Getter
    private final Integer status;

    /**
     * 上次登陆时间
     */
    @Getter
    private final LocalDateTime lastLoginTime;

    /**
     * 创建账号时间
     */
    @Getter
    private final LocalDateTime createTime;

    /**
     * 关联角色集合
     */
    @Getter
    private final List<RoleDTO> roles;

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param id                    用户ID
     * @param deptId                部门ID
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not
     *                              expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authorities           the authorities that should be granted to the caller if they
     *                              presented the correct username and password and the user is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public SecurityUser(Long id, Long deptId, String mobile, String email, Integer status, LocalDateTime lastLoginTime,
                        LocalDateTime createTime, String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked, List<RoleDTO> roles,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.createTime = createTime;
        this.roles = roles;
    }

}
