package com.simple.helloblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuTypeEnum {
    /**
     * 目录
     */
    DIRECTORY("D"),
    /**
     * 菜单
     */
    MENU("M"),
    /**
     * 按钮
     */
    BUTTON("B");

    private final String type;
}
