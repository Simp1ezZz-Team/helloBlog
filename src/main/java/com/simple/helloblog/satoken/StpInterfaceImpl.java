package com.simple.helloblog.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        //TODO
        return null;
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
        //TODO
        return null;
    }
}
