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

package com.vanwei.tech.util;

import cn.hutool.core.util.StrUtil;
import com.vanwei.tech.dto.RoleDTO;
import com.vanwei.tech.security.SecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static com.vanwei.tech.enums.RoleEnum.ADMIN;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public SecurityUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser) {
            return (SecurityUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public SecurityUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<RoleDTO> getRoles() {
        SecurityUser currentUser = getUser();

        return currentUser.getRoles();
    }

    /**
     * 判断当前用户是否为管理员
     *
     * @return 是否为管理员
     */
    public boolean isAdmin() {
        SecurityUser currentUser = getUser();

        return currentUser.getRoles().stream()
                .anyMatch(role -> StrUtil.equals(role.getName(), ADMIN.getName()));
    }
}
