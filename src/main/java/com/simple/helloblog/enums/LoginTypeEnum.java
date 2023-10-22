package com.simple.helloblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginTypeEnum {
    EMAIL(1),
    QQ(2),
    GITEE(3),
    GITHUB(4);

    private final Integer type;
}
