package com.simple.helloblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.entity.UserRole;
import com.simple.helloblog.mapper.RoleMapper;
import com.simple.helloblog.model.dto.RoleDTO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RoleVO;
import com.simple.helloblog.service.RoleService;
import com.simple.helloblog.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void deleteRole(List<Integer> roleIdList) {
        Assert.isFalse(roleIdList.contains(1), "不允许删除超级管理员角色");
        // 角色是否已分配
        long count = userRoleService.count(Wrappers.<UserRole>lambdaQuery().in(UserRole::getRoleId, roleIdList));
        Assert.isFalse(count > 0, "角色已分配给人员，无法删除");

        this.removeBatchByIds(roleIdList);
        //TODO 删除角色菜单关联
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色 DTO
     */
    @Override
    public void updateRole(RoleDTO roleDTO) {
        Assert.isFalse(roleDTO.getRoleId().equals(1)
                && roleDTO.getDisableFlag().equals(1), "不允许禁用超级管理员角色");
        // 角色id是否存在
        long countId = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, roleDTO.getRoleId()));
        Assert.isFalse(countId > 0, "要更新的角色不存在");
        // 角色名是否存在
        long countRoleName = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleName, roleDTO.getRoleName()));
        Assert.isFalse(countRoleName > 0, "{}角色名已存在", roleDTO.getRoleName());

        Role role = BeanUtil.copyProperties(roleDTO, Role.class);
        this.updateById(role);
        //TODO 更新角色菜单关联
    }

    /**
     * 更新角色状态
     *
     * @param roleDTO 角色 DTO
     */
    @Override
    public void updateRoleStatus(RoleDTO roleDTO) {
        Assert.isFalse(roleDTO.getRoleId().equals(1)
                && roleDTO.getDisableFlag().equals(1), "不允许禁用超级管理员角色");
        // 角色id是否存在
        long countId = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, roleDTO.getRoleId()));
        Assert.isFalse(countId > 0, "要更新的角色不存在");

        Role role = Role.builder()
                .roleId(roleDTO.getRoleId())
                .disableFlag(roleDTO.getDisableFlag()).build();
        this.updateById(role);
    }
}
