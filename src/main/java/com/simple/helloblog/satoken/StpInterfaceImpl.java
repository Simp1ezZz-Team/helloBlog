package com.simple.helloblog.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token自定义权限校验接口实现类
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 22:14:13
 */
@Service
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {
    /**
     * 角色服务
     */
    private final RoleService roleService;
    /**
     * 菜单服务
     */
    private final MenuService menuService;

    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        for (String roleId : getRoleList(loginId, loginType)) {
            // 根据roleId获取自定义的SaSession，如果不存在则创建一个，自定义的SaSession其实就是一个redis数据
            SaSession saSession = SaSessionCustomUtil.getSessionById(CommonConstant.SA_SESSION_ROLE_PREFIX + roleId, true);
            // 在SaSession中获取权限码集合，如果不存在则通过数据库查找后存入SaSession
            List<String> permIds = saSession.get(SaSession.PERMISSION_LIST, () -> menuService.listPermissionByRoleId(roleId));
            permissionList.addAll(permIds);
        }
        //返回权限码集合
        return permissionList;
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession saSession = StpUtil.getSessionByLoginId(loginId);
        return saSession.get(SaSession.ROLE_LIST, () -> roleService.listRoleByUserId(loginId));
    }
}
