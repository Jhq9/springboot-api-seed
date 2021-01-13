package com.vanwei.tech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vanwei.tech.entity.User;
import com.vanwei.tech.dto.request.UserRequestDTO;
import com.vanwei.tech.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User Mapper
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 9:24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据账号ID查询账户详细信息
     *
     * @param id 账号ID
     * @return 账号详细信息
     */
    UserVO selectOneById(@Param("id") Long id);

    /**
     * 根据账号查询账号详细信息
     *
     * @param username 账号
     * @return 账号详细信息
     */
    UserVO selectOneByUsername(@Param("username") String username);

    /**
     * 分页查询用户列表
     *
     * @param page           分页信息
     * @param userRequestDTO 查询条件信息
     * @return 用户列表
     */
    IPage<UserVO> selectPageVo(Page page, @Param("query") UserRequestDTO userRequestDTO);
}
