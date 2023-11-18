package com.simple.helloblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginTypeEnum {
    /**
     * 账号密码登录
     */
    USERNAME(0),
    /**
     * 邮箱
     */
    EMAIL(1),
    /**
     * QQ
     */
    QQ(2),
    /**
     * 码云
     */
    GITEE(3),
    /**
     * github
     */
    GITHUB(4);

    private final Integer type;
}
