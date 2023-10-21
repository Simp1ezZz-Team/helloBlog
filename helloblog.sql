create database helloblog default character set utf8mb4 collate utf8mb4_general_ci;
use helloblog;
create table helloblog.t_menu
(
    menu_id      int auto_increment comment '主键'
        primary key,
    parent_id    int          default 0  not null comment '父菜单id (paren_id为0且type为M则是一级菜单)',
    menu_type    char                    not null comment '权限类型 (M目录 C菜单 B按钮)',
    menu_name    varchar(50)             not null comment '名称',
    path         varchar(255)            null comment '路由地址',
    icon         varchar(50)             null comment '菜单图标',
    component    varchar(50)             null comment '菜单组件',
    perms        varchar(100) default '' null comment '权限标识',
    hidden_flag  tinyint(1)   default 0  not null comment '是否隐藏 (0否 1是)',
    disable_flag tinyint(1)   default 0  not null comment '是否禁用 (0否 1是)',
    order_num    int          default 1  not null comment '排序',
    create_by    int                     not null comment '创建者',
    create_time  datetime                not null comment '创建时间',
    update_by    int                     null comment '更新者',
    update_time  datetime                null comment '更新时间',
    del_flag     tinyint(1)   default 0  not null comment '逻辑删除标志(0未删除 1已删除)'
)
    row_format = DYNAMIC;

create index t_menu_parent_id_index
    on helloblog.t_menu (parent_id);

create table helloblog.t_role
(
    role_id      int auto_increment comment '主键id'
        primary key,
    role_name    varchar(20)          not null comment '角色名称',
    role_desc    varchar(50)          null comment '角色描述',
    disable_flag tinyint(1) default 0 not null comment '是否禁用 (0否 1是)',
    create_by    int                  not null comment '创建者',
    create_time  datetime             not null comment '创建时间',
    update_by    int                  null comment '更新者',
    update_time  datetime             null comment '更新时间',
    del_flag     tinyint(1) default 0 not null comment '逻辑删除标志(0未删除 1已删除)'
)
    row_format = DYNAMIC;

create table helloblog.t_role_menu
(
    id          int auto_increment comment '主键'
        primary key,
    role_id     int                  not null comment '角色id',
    menu_id     int                  not null comment '菜单id',
    create_by   int                  not null comment '创建者',
    create_time datetime             not null comment '创建时间',
    update_by   int                  null comment '更新者',
    update_time datetime             null comment '更新时间',
    del_flag    tinyint(1) default 0 not null comment '逻辑删除标志(0未删除 1已删除)'
)
    row_format = DYNAMIC;

create table helloblog.t_user
(
    user_id      int auto_increment comment '用户id'
        primary key,
    nickname     varchar(50)             not null comment '用户昵称',
    username     varchar(50)             not null comment '用户名',
    password     varchar(100)            not null comment '用户密码',
    avatar       varchar(255)            null comment '头像',
    web_site     varchar(255) default '' null comment '个人网站',
    intro        varchar(100) default '' null comment '个人简介',
    email        varchar(50)  default '' null comment '邮箱',
    ip_address   varchar(50)  default '' null comment '登录ip',
    ip_source    varchar(50)  default '' null comment '登录地址',
    login_type   tinyint(1)   default 0  not null comment '登录方式 (1邮箱 2QQ 3Gitee 4Github)',
    disable_flag tinyint(1)   default 0  not null comment '是否禁用 (0否 1是)',
    login_time   datetime                null comment '登录时间',
    create_by    int                     not null comment '创建者',
    create_time  datetime                not null comment '创建时间',
    update_by    int                     null comment '更新者',
    update_time  datetime                null comment '更新时间',
    del_flag     tinyint(1)   default 0  not null comment '逻辑删除标志(0未删除 1已删除)'
)
    row_format = DYNAMIC;

create table helloblog.t_user_role
(
    id          int auto_increment comment '主键'
        primary key,
    user_id     int                  not null comment '用户id',
    role_id     varchar(20)          not null comment '角色id',
    create_by   int                  not null comment '创建者',
    create_time datetime             not null comment '创建时间',
    update_by   int                  null comment '更新者',
    update_time datetime             null comment '更新时间',
    del_flag    tinyint(1) default 0 not null comment '逻辑删除标志(0未删除 1已删除)'
)
    row_format = DYNAMIC;

