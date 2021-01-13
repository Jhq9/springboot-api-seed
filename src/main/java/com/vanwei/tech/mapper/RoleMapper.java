package com.vanwei.tech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vanwei.tech.entity.Role;
import com.vanwei.tech.dto.request.RoleRequestDTO;
import com.vanwei.tech.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Role Mapper
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 9:24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色列表
     *
     * @param name 角色名称
     * @return 角色列表
     */
    List<RoleVO> selectListByName(@Param("name") String name);

    /**
     * 根据用户ID查询其角色列表
     *
     * @param userId 用户的ID
     * @return 角色列表
     */
    List<RoleVO> selectListByUserId(@Param("userId") Long userId);

    /**
     * 分页查询角色列表
     *
     * @param page    分页信息
     * @param roleDTO 查询条件信息
     * @return 角色列表
     */
    IPage<RoleVO> selectPageVo(Page page, @Param("query") RoleRequestDTO roleDTO);
}
