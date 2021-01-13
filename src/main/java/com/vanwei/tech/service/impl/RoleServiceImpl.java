package com.vanwei.tech.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vanwei.tech.entity.Role;
import com.vanwei.tech.entity.UserRole;
import com.vanwei.tech.dto.request.RoleRequestDTO;
import com.vanwei.tech.enums.RoleEnum;
import com.vanwei.tech.mapper.RoleMapper;
import com.vanwei.tech.service.RoleService;
import com.vanwei.tech.service.UserRoleService;
import com.vanwei.tech.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vanwei.tech.constant.CommonConstants.RESULT;
import static com.vanwei.tech.constant.CommonConstants.STATUS_NORMAL;
import static java.lang.Boolean.TRUE;

/**
 * Role Service Implements
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 11:21
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final UserRoleService userRoleService;

    @Override
    public IPage<RoleVO> listRole(Page page, RoleRequestDTO roleDTO) {
        return baseMapper.selectPageVo(page, roleDTO);
    }

    @Override
    public List<RoleVO> listRole(String name) {
        return baseMapper.selectListByName(name);
    }

    @Override
    public Role getRole(String name) {
        return this.baseMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, name));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> saveRole(RoleRequestDTO roleDTO) {
        checkName(roleDTO.getName());

        Role role = new Role();

        role.setName(roleDTO.getName());
        role.setRemark(roleDTO.getRemark());
        role.setDeleted(STATUS_NORMAL);

        this.save(role);

        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, TRUE);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> updateRole(Long id, RoleRequestDTO roleDTO) {
        Role role = baseMapper.selectById(id);
        Assert.notNull(role, "未找到对应的角色");

        if (!Objects.equals(role.getName(), roleDTO.getName())) {
            checkName(roleDTO.getName());
            role.setName(roleDTO.getName());
        }
        role.setRemark(roleDTO.getRemark());

        this.updateById(role);

        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, TRUE);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> removeRole(Long id) {
        Role role = this.getById(id);
        Assert.notNull(role, "未找到对应的角色");
        checkLink(id);

        // 批量移除角色绑定的菜单集合
        this.baseMapper.deleteById(id);

        Map<String, Boolean> resultMap = new HashMap<>(1);
        resultMap.put(RESULT, TRUE);

        return resultMap;
    }

    @Override
    public boolean initDefaultRole() {
        List<Role> roleList = this.list();
        List<Role> savingRoleList = Stream.of(RoleEnum.values())
                .filter(roleEnum -> roleList.stream()
                        .noneMatch(role -> Objects.equals(roleEnum.getName(), role.getName())))
                .map(roleEnum -> {
                    Role role = new Role();

                    role.setName(roleEnum.getName());
                    role.setRemark(roleEnum.getMark());

                    return role;
                }).collect(Collectors.toList());

        if (savingRoleList.isEmpty()) {
            return true;
        }

        return this.saveBatch(savingRoleList);
    }

    /**
     * 根据角色名称查询
     *
     * @param roleNameList
     * @return
     */
    private List<Role> listRole(List<String> roleNameList) {
        return this.list(Wrappers.<Role>lambdaQuery().in(Role::getName, roleNameList));
    }

    /**
     * 校验角色名称是否已存在
     *
     * @param name 角色名称
     */
    private void checkName(String name) {
        int count = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getName, name));
        Assert.isTrue(count == 0, "已存在相同名称的角色,请检查角色名称");
    }

    /**
     * 校验角色是否关联了用户
     *
     * @param id 角色的ID
     */
    private void checkLink(Long id) {
        int count = userRoleService.count(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, id));
        Assert.isTrue(count == 0, "有用户关联了该角色,不能进行删除操作");
    }
}
