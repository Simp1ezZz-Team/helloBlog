create database helloblog default character set utf8mb4 collate utf8mb4_general_ci;
use helloblog;
create table t_menu
(
    menu_id      int auto_increment comment '主键'
        primary key,
    parent_id    int          default 0  not null comment '父菜单id (paren_id为0且type为M则是一级菜单)',
    menu_type    char                    not null comment '权限类型 (D 目录 M菜单  B按钮)',
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
    charset = utf8mb4
    row_format = DYNAMIC;

create index t_menu_parent_id_index
    on t_menu (parent_id);

INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (1, 0, 'D', '系统管理', '/system', 'system', '', '', 0, 0, 1, 1, '2023-10-22 14:55:55', 1, '2023-10-22 14:55:58', 0);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (2, 1, 'M', '用户管理', 'user', 'user', '/system/user/index', 'system:user:list', 0, 0, 1, 1, '2023-11-12 23:07:46', null, null, 0);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (3, 1, 'M', '角色管理', 'role', 'role', '/system/role/index', 'system:role:list', 0, 0, 2, 1, '2023-11-12 23:09:48', null, null, 0);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (4, 1, 'M', '菜单管理', 'menu', 'menu', '/system/menu/index', 'system:menu:list', 0, 0, 3, 1, '2023-11-12 23:09:49', null, null, 0);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (5, 0, 'D', '日志管理', '/log', 'log', null, '', 0, 0, 1, 1, '2023-11-12 23:12:11', null, null, 1);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (6, 5, 'M', '操作日志', 'operation', null, '/system/log/operation', 'log:operation:list', 0, 0, 1, 1, '2023-11-12 23:14:09', null, null, 1);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (7, 5, 'M', '异常日志', 'exception', null, '/system/log/exception', 'log:exception:list', 0, 0, 1, 1, '2023-11-12 23:14:10', null, null, 1);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (8, 5, 'M', '访问日志', 'visit', null, '/system/log/visit', 'log:visit:list', 0, 0, 1, 1, '2023-11-12 23:14:11', null, null, 1);
INSERT INTO helloblog.t_menu (menu_id, parent_id, menu_type, menu_name, path, icon, component, perms, hidden_flag, disable_flag, order_num, create_by, create_time, update_by, update_time, del_flag) VALUES (9, 5, 'M', '任务日志', 'task', null, '/system/log/task', 'log:task:list', 0, 0, 1, 1, '2023-11-12 23:14:12', null, null, 1);

create table t_role
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
    charset = utf8mb4
    row_format = DYNAMIC;

INSERT INTO helloblog.t_role (role_id, role_name, role_desc, disable_flag, create_by, create_time, update_by, update_time, del_flag) VALUES (1, 'role_all', '超级管理员权限', 0, 1, '2023-10-21 22:26:29', null, null, 0);
INSERT INTO helloblog.t_role (role_id, role_name, role_desc, disable_flag, create_by, create_time, update_by, update_time, del_flag) VALUES (2, 'test1', '测试测试', 0, 1, '2023-10-21 22:28:43', 1, '2023-10-21 22:28:43', 0);

create table t_role_menu
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
    charset = utf8mb4
    row_format = DYNAMIC;

INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (1, 1, 1, 1, '2023-11-12 23:14:46', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (2, 1, 2, 1, '2023-11-12 23:14:54', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (3, 1, 3, 1, '2023-11-12 23:15:34', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (4, 1, 4, 1, '2023-11-12 23:15:35', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (5, 1, 5, 1, '2023-11-12 23:15:36', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (6, 1, 6, 1, '2023-11-12 23:15:37', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (7, 1, 7, 1, '2023-11-12 23:15:39', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (8, 1, 8, 1, '2023-11-12 23:15:41', null, null, 0);
INSERT INTO helloblog.t_role_menu (id, role_id, menu_id, create_by, create_time, update_by, update_time, del_flag) VALUES (9, 1, 9, 1, '2023-11-12 23:15:43', null, null, 0);

create table t_user
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
    charset = utf8mb4
    row_format = DYNAMIC;

INSERT INTO helloblog.t_user (user_id, nickname, username, password, avatar, web_site, intro, email, ip_address, ip_source, login_type, disable_flag, login_time, create_by, create_time, update_by, update_time, del_flag) VALUES (1, '超级管理员', 'admin', '78700e4ec29576fbd7ad2a9b17a3034451f17454a453c39e00638754f0aac849', null, '', '', 'test@qq.com', '', '', 1, 0, null, 1, '2023-10-21 21:56:54', 1, '2023-11-12 23:02:53', 0);

create table t_user_role
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
    charset = utf8mb4
    row_format = DYNAMIC;

INSERT INTO helloblog.t_user_role (id, user_id, role_id, create_by, create_time, update_by, update_time, del_flag) VALUES (1, 1, '1', 1, '2023-11-12 23:03:53', null, null, 0);
