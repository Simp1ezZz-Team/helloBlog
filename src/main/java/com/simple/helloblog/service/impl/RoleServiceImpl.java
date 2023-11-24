package com.simple.helloblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.MPJWrappers;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.entity.UserRole;
import com.simple.helloblog.mapper.RoleMapper;
import com.simple.helloblog.model.dto.DisableDTO;
import com.simple.helloblog.model.dto.RoleDTO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RoleVO;
import com.simple.helloblog.service.RoleService;
import com.simple.helloblog.service.UserRoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:10:48
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {

    private final UserRoleService userRoleService;

    private final RoleMapper roleMapper;

    /**
     * 查询角色列表
     *
     * @param roleDTO 查询条件
     * @return {@link PageResult}<{@link RoleVO}>
     */
    @Override
    public PageResult<RoleVO> listRoleVO(RoleDTO roleDTO) {
        Page<Role> rolePage = this.page(roleDTO.toPage(), Wrappers.<Role>lambdaQuery()
            .eq(roleDTO.getRoleId() != null, Role::getRoleId, roleDTO.getRoleId())
            .like(CharSequenceUtil.isNotBlank(roleDTO.getRoleName()), Role::getRoleName, roleDTO.getRoleName())
            .eq(roleDTO.getDisableFlag() != null, Role::getDisableFlag, roleDTO.getDisableFlag()));
        return new PageResult<>(rolePage.convert(role -> BeanUtil.copyProperties(role, RoleVO.class)));
    }

    /**
     * 查询全部角色列表
     *
     * @param roleDTO 查询参数
     * @return {@link List}<{@link RoleVO}>
     */
    @Override
    public List<RoleVO> listAllRoleVO(RoleDTO roleDTO) {
        return this.selectJoinList(RoleVO.class, MPJWrappers.<Role>lambdaJoin()
            .select(Role::getRoleId, Role::getRoleName, Role::getDisableFlag)
            .eq(roleDTO.getRoleId() != null, Role::getRoleId, roleDTO.getRoleId())
            .like(CharSequenceUtil.isNotBlank(roleDTO.getRoleName()), Role::getRoleName, roleDTO.getRoleName())
            .eq(roleDTO.getDisableFlag() != null, Role::getDisableFlag, roleDTO.getDisableFlag()));
    }

    /**
     * 添加角色
     *
     * @param roleDTO 角色 DTO
     */
    @Override
    public void addRole(RoleDTO roleDTO) {
        // 角色名是否已存在
        Role existRole = this.getOne(Wrappers.<Role>lambdaQuery()
            .select(List.of(Role::getRoleId))
            .eq(Role::getRoleName, roleDTO.getRoleName()));
        Assert.isNull(existRole, "{}角色名已存在", roleDTO.getRoleName());

        Role role = BeanUtil.copyProperties(roleDTO, Role.class);
        this.save(role);
        //TODO 插入roleMenu
    }

    /**
     * 删除角色
     *
     * @param roleIdList 角色 ID 列表
     */
    @Override
    @Transactional
    public void batchDeleteRole(List<Integer> roleIdList) {
        Assert.isFalse(roleIdList.contains(CommonConstant.ADMIN_ROLE_ID), "不允许删除超级管理员角色");
        // 角色是否已分配
        long count = userRoleService.count(Wrappers.<UserRole>lambdaQuery().in(UserRole::getRoleId, roleIdList));
        Assert.isFalse(count > 0, "角色已分配给人员，无法删除");

        roleMapper.deleteBatchIds(roleIdList);
        //TODO 删除角色菜单关联
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色 DTO
     */
    @Override
    public void updateRole(RoleDTO roleDTO) {
        Assert.isFalse(CommonConstant.ADMIN_ROLE_ID.equals(roleDTO.getRoleId())
            && CommonConstant.TRUE.equals(roleDTO.getDisableFlag()), "不允许禁用超级管理员角色");
        // 角色id是否存在
        long countId = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, roleDTO.getRoleId()));
        Assert.isTrue(countId > 0, "要更新的角色不存在");
        // 角色名是否存在
        long countRoleName = this.count(Wrappers.<Role>lambdaQuery().ne(Role::getRoleId, roleDTO.getRoleId())
            .eq(Role::getRoleName, roleDTO.getRoleName()));
        Assert.isFalse(countRoleName > 0, "{}角色名已存在", roleDTO.getRoleName());

        Role role = BeanUtil.copyProperties(roleDTO, Role.class);
        this.updateById(role);
        //TODO 更新角色菜单关联
    }

    /**
     * 更新角色状态
     *
     * @param disableDTO 禁用 DTO
     */
    @Override
    public void updateRoleStatus(DisableDTO disableDTO) {
        Assert.isFalse(CommonConstant.ADMIN_ROLE_ID.equals(disableDTO.getId())
            && CommonConstant.TRUE.equals(disableDTO.getDisableFlag()), "不允许禁用超级管理员角色");
        // 角色id是否存在
        long countId = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, disableDTO.getId()));
        Assert.isTrue(countId > 0, "要更新的角色不存在");

        Role role = Role.builder()
            .roleId(disableDTO.getId())
            .disableFlag(disableDTO.getDisableFlag()).build();
        this.updateById(role);
    }

    /**
     * 按 ID 获取角色
     *
     * @param roleId 角色 ID
     * @return {@link RoleVO}
     */
    @Override
    public RoleVO getRoleById(Integer roleId) {
        Role role = this.getById(roleId);
        return BeanUtil.copyProperties(role, RoleVO.class);
    }

    /**
     * 根据用户id查询用户所有角色id List
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> listRoleByUserId(Object userId) {
        List<RoleVO> roleVOS = this.selectJoinList(RoleVO.class, MPJWrappers.<Role>lambdaJoin()
            .select(Role::getRoleId)
            .innerJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
            .eq(Role::getDisableFlag, CommonConstant.FALSE)
            .eq(UserRole::getUserId, userId));
        return roleVOS.stream().map(roleVO -> roleVO.getRoleId().toString()).toList();
    }


}
