package com.vanwei.tech.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanwei.tech.dto.PayloadDTO;
import com.vanwei.tech.dto.UserDTO;
import com.vanwei.tech.dto.request.UserLoginRequestDTO;
import com.vanwei.tech.dto.request.UserRequestDTO;
import com.vanwei.tech.entity.Role;
import com.vanwei.tech.entity.User;
import com.vanwei.tech.mapper.UserMapper;
import com.vanwei.tech.properties.JwtProperties;
import com.vanwei.tech.security.SecurityUser;
import com.vanwei.tech.service.RoleService;
import com.vanwei.tech.service.UserRoleService;
import com.vanwei.tech.service.UserService;
import com.vanwei.tech.util.JwtUtil;
import com.vanwei.tech.util.SecurityUtils;
import com.vanwei.tech.vo.UserInfoVO;
import com.vanwei.tech.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.vanwei.tech.constant.CommonConstants.*;
import static com.vanwei.tech.enums.RoleEnum.ADMIN;


/**
 * User Service Implements
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 11:20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final ObjectMapper objectMapper;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    private final JwtProperties jwtProperties;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    @Override
    public IPage<UserVO> listUser(Page page, UserRequestDTO userRequestDTO) {
        return baseMapper.selectPageVo(page, userRequestDTO);
    }

    @Override
    public UserDTO getUser() {
        Authentication authentication = SecurityUtils.getAuthentication();

        return getUser(authentication.getName());
    }

    @Override
    public UserDTO getUser(String username) {
        UserVO userVO = baseMapper.selectOneByUsername(username);
        Assert.notNull(userVO, "未找到对应的用户");

        UserDTO userDTO = new UserDTO();

        userDTO.setUserVO(userVO);

        return userDTO;
    }

    @SneakyThrows
    @Override
    public UserInfoVO login(UserLoginRequestDTO userLoginRequestDTO) {
        // 根据用户输入的账号及密码生成AuthenticationToken
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
        // 用户名密码登陆效验
        final Authentication authentication = authenticationManager.authenticate(upToken);
        // 认证成功，将认证信息存入holder中
        SecurityContext ctx = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(ctx);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final SecurityUser userDetails = SecurityUtils.getUser();
        Instant instant = Instant.now(Clock.system(ZoneId.of(ZONE_ID_ASIA_SHANGHAI)));

        PayloadDTO payloadDTO = PayloadDTO.builder()
                .exp(jwtProperties.getExpiresIn())
                .nbf(instant.toEpochMilli())
                .username(userDetails.getUsername())
                .build();

        String accessToken = JwtUtil.generateToken(objectMapper.writeValueAsString(payloadDTO), jwtProperties.getSecret());

        UserInfoVO userInfo = new UserInfoVO();

        BeanUtil.copyProperties(userDetails, userInfo);
        userInfo.setRoleList(userDetails.getRoles());
        userInfo.setAccessToken(accessToken);
        userInfo.setExpiresIn(jwtProperties.getExpiresIn());
        userInfo.setTokenType(jwtProperties.getTokenType());

        User user = getById(userInfo.getId());
        user.setLastLoginTime(LocalDateTime.ofInstant(instant, ZoneId.of(ZONE_ID_ASIA_SHANGHAI)));

        this.updateById(user);

        return userInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> saveUser(UserRequestDTO userRequestDTO) {
        checkUsername(userRequestDTO.getUsername());

        User user = new User();

        BeanUtils.copyProperties(userRequestDTO, user);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setDeleted(STATUS_NORMAL);

        baseMapper.insert(user);

        boolean result = userRoleService.saveUserRole(userRequestDTO.getRoleIds(), user.getId());
        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, result);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initAdmin() {
        User user = getOne("admin");
        if (Objects.nonNull(user)) {
            return true;
        }
        Role adminRole = roleService.getRole(ADMIN.getName());

        user = new User();

        user.setUsername("admin");
        String userName= SecureUtil.md5("9dak5tvb");
        user.setPassword(passwordEncoder.encode(userName));
        user.setPhone("15700084691");
        user.setEmail("1044038055@qq.com");
        user.setStatus(STATUS_NORMAL);

        baseMapper.insert(user);

        return userRoleService.saveUserRole(new Long[]{adminRole.getId()}, user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = baseMapper.selectById(id);
        Assert.notNull(user, "用户不存在");

        user.setPhone(userRequestDTO.getPhone());
        user.setEmail(userRequestDTO.getEmail());
        user.setStatus(userRequestDTO.getStatus());
        int count = baseMapper.updateById(user);

        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, count == 1);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> removeUser(Long id) {
        User user = baseMapper.selectById(id);
        Assert.notNull(user, "用户不存在");

        baseMapper.deleteById(id);

        boolean result = userRoleService.removeUserRole(id);

        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, result);

        return resultMap;
    }

    /**
     * 根据用户账号查询对应的用户
     *
     * @param username 用户账号
     * @return 用户
     */
    private User getOne(String username) {
        return this.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }


    /**
     * 校验账户是否已经被注册了
     *
     * @param username 用户的账号
     */
    private void checkUsername(String username) {
        int count = this.count(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        Assert.isTrue(count == 0, "账号已被占用");
    }
}
