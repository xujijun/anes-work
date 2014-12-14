CREATE TABLE `t_sys_menu` (
`id`  varchar(32) NOT NULL ,
`name`  varchar(64) NOT NULL COMMENT '菜单名称' ,
`parent_id`  varchar(32) NULL ,
`url`  varchar(100) NULL COMMENT '链接地址' ,
`order_no`  tinyint NULL ,
`status`  varchar(3) NOT NULL ,
`remark`  varchar(100) NULL ,
`creator`  varchar(20) NOT NULL ,
`create_dt`  datetime NOT NULL ,
`updater`  varchar(20) NULL ,
`update_dt`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `t_sys_permission` (
`id`  varchar(32) NOT NULL ,
`menu_id`  varchar(32) NOT NULL ,
`name`  varchar(64) NOT NULL ,
`cls`  varchar(100) NOT NULL COMMENT '所在类' ,
`method`  varchar(100) NOT NULL COMMENT '所在方法' ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `t_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

CREATE TABLE `t_sys_role` (
`id`  varchar(32) NOT NULL ,
`name`  varchar(10) NOT NULL ,
`status`  varchar(3) NOT NULL COMMENT '状态：ON/OFF' ,
`remark`  varchar(100) NULL ,
`creator`  varchar(20) NOT NULL ,
`create_dt`  datetime NOT NULL ,
`updater`  varchar(20) NULL ,
`update_dt`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `t_sys_user` (
`id`  varchar(32) NOT NULL ,
`code`  varchar(20) NOT NULL COMMENT '登录账号' ,
`password`  varchar(32) NOT NULL COMMENT 'MD5' ,
`name`  varchar(20) NOT NULL COMMENT '姓名' ,
`status`  varchar(3) NOT NULL COMMENT 'ON/OFF' ,
`user_type`  varchar(5) NOT NULL COMMENT 'SYS/COM' ,
`role_id`  varchar(32) NULL COMMENT '所属角色' ,
`remark`  varchar(100) NULL ,
`unlock_dt`  datetime NULL COMMENT '解锁时间' ,
`creator`  varchar(20) NOT NULL ,
`create_dt`  datetime NOT NULL ,
`updator`  varchar(20) NULL ,
`update_dt`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

CREATE TABLE `t_sys_role_menu` (
`id`  varchar(32) NOT NULL ,
`role_id`  varchar(32) NOT NULL ,
`menu_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `t_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

CREATE TABLE `t_sys_role_permission` (
`id`  varchar(32) NOT NULL ,
`role_id`  varchar(32) NOT NULL ,
`permission_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `fk_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `t_sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;


CREATE TABLE `t_sys_dict` (
`id`  varchar(32) NULL ,
`cls_code`  varchar(32) NULL COMMENT '分类编码' ,
`cls_name`  varchar(32) NULL COMMENT '分类名称' ,
`code`  varchar(10) NULL COMMENT '编码' ,
`name`  varchar(20) NULL COMMENT '名称' ,
`status`  varchar(3) NULL ,
`remark`  varchar(100) NULL ,
`creator`  varchar(20) NULL ,
`create_dt`  datetime NULL ,
`updater`  varchar(20) NULL ,
`update_dt`  timestamp NULL ON UPDATE CURRENT_TIMESTAMP 
)
;
