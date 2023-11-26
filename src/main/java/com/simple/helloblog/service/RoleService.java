package com.simple.helloblog.service;

import com.github.yulichang.base.MPJBaseService;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.model.dto.DisableDTO;
import com.simple.helloblog.model.dto.RoleDTO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RoleVO;

import java.util.List;

/**
 * 角色服务
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:10:05
 */
public interface RoleService extends MPJBaseService<Role> {
    /**
     * 查询角色列表
     *
     * @param roleDTO 查询条件
     * @return {@link PageResult}<{@link RoleVO}>
     */
    PageResult<RoleVO> listRoleVO(RoleDTO roleDTO);

    /**
     * 查询全部角色列表
     * @param roleDTO 查询参数
     * @return {@link List}<{@link RoleVO}>
     */
    List<RoleVO> listAllRoleVO(RoleDTO roleDTO);

    /**
     * 根据用户id查询用户所有角色id List
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> listRoleByUserId(Object userId);

    /**
     * 添加角色
     *
     * @param roleDTO 角色 DTO
     */
    void addRole(RoleDTO roleDTO);

    /**
     * 删除角色
     *
     * @param roleIdList 角色 ID 列表
     */
    void batchDeleteRole(List<Integer> roleIdList);

    /**
     * 更新角色
     *
     * @param roleDTO 角色 DTO
     */
    void updateRole(RoleDTO roleDTO);

    /**
     * 更新角色状态
     *
     * @param disableDTO 禁用 DTO
     */
    void updateRoleStatus(DisableDTO disableDTO);

    /**
     * 按 ID 获取角色
     *
     * @param roleId 角色 ID
     * @return {@link RoleVO}
     */
    RoleVO getRoleById(Integer roleId);

    /**
     * 获取角色菜单 ID 列表
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> getRoleMenuIdList(Integer roleId);
}
