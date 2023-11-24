package com.simple.helloblog.model.vo;

import java.util.List;
import lombok.Data;

/**
 * 菜单选项
 *
 * @author simpleZzz
 * @date 2023/11/23 16:00:58
 */
@Data
public class MenuOption {
    /**
     * 菜单id
     */
    private Integer value;
    /**
     * 父菜单id
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String label;
    /**
     * 子菜单列表
     */
    private List<MenuOption> children;
}
