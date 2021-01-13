package com.vanwei.tech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vanwei.tech.entity.Role;
import com.vanwei.tech.dto.request.RoleRequestDTO;
import com.vanwei.tech.vo.RoleVO;

import java.util.List;
import java.util.Map;

/**
 * Role Service
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 9:39
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页获取角色列表
     *
     * @param page    页码信息
     * @param roleDTO 查询信息
     * @return 角色列表
     */
    IPage<RoleVO> listRole(Page page, RoleRequestDTO roleDTO);

    /**
     * 查询角色列表
     *
     * @param name 角色名称
     * @return 角色列表
     */
    List<RoleVO> listRole(String name);

    /**
     * 根据角色的名称查询角色信息
     *
     * @param name 角色的名称
     * @return 角色信息
     */
    Role getRole(String name);

    /**
     * 保存角色信息
     *
     * @param roleDTO 角色信息
     * @return 新增的结果
     */
    Map<String, Boolean> saveRole(RoleRequestDTO roleDTO);

    /**
     * 更新角色信息
     *
     * @param id      角色ID
     * @param roleDTO 角色信息
     * @return 更新的结果
     */
    Map<String, Boolean> updateRole(Long id, RoleRequestDTO roleDTO);

    /**
     * 删除角色
     *
     * @param id 角色的ID
     * @return 删除的结果
     */
    Map<String, Boolean> removeRole(Long id);

    /**
     * 初始化默认角色
     *
     * @return 是否初始化成功
     */
    boolean initDefaultRole();
}
