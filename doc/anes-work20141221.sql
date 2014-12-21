/*
Navicat MySQL Data Transfer

Source Server         : LocalDB
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : anes-work

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2014-12-21 20:28:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_anes_anesthetist`
-- ----------------------------
DROP TABLE IF EXISTS `t_anes_anesthetist`;
CREATE TABLE `t_anes_anesthetist` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `name` varchar(20) collate utf8_unicode_ci NOT NULL,
  `sex` varchar(2) collate utf8_unicode_ci default NULL,
  `status` varchar(3) collate utf8_unicode_ci NOT NULL,
  `creator` varchar(20) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime NOT NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_anes_anesthetist
-- ----------------------------
INSERT INTO `t_anes_anesthetist` VALUES ('3e3ea84b79f54747a1b25fa0439d2b36', '洪木周', 'M', 'ON', 'administrator', '2014-12-21 15:27:48', 'administrator', '2014-12-21 19:58:03');
INSERT INTO `t_anes_anesthetist` VALUES ('f161005f9ebb4e1785b9e2315a1ab39a', '小张', 'M', 'ON', 'administrator', '2014-12-21 15:27:55', 'administrator', '2014-12-21 19:57:54');
INSERT INTO `t_anes_anesthetist` VALUES ('531d3873e4e94fe2af4353f83cfed1cc', 'aaa', 'U', 'ON', 'administrator', '2014-12-21 15:41:30', 'administrator', '2014-12-21 19:57:44');
INSERT INTO `t_anes_anesthetist` VALUES ('4120afe54be14a698d932da3de182da5', 'zzzq', 'M', 'OFF', 'administrator', '2014-12-21 15:41:41', 'administrator', '2014-12-21 15:43:58');

-- ----------------------------
-- Table structure for `t_anes_work`
-- ----------------------------
DROP TABLE IF EXISTS `t_anes_work`;
CREATE TABLE `t_anes_work` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `operation_dt` datetime NOT NULL,
  `department` varchar(2) collate utf8_unicode_ci NOT NULL,
  `admission_no` varchar(10) collate utf8_unicode_ci NOT NULL,
  `patient_name` varchar(20) collate utf8_unicode_ci default NULL,
  `patient_age` varchar(3) collate utf8_unicode_ci default NULL,
  `operation_name` varchar(20) collate utf8_unicode_ci default NULL,
  `anes_method` varchar(2) collate utf8_unicode_ci NOT NULL,
  `anesthetist_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `pump` tinyint(4) default NULL COMMENT '止痛泵',
  `dezocine` tinyint(4) default NULL COMMENT '地佐辛',
  `mepivacaine` tinyint(4) default NULL COMMENT '甲哌卡因',
  `creator` varchar(20) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime NOT NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `fk_anesthetist_id` (`anesthetist_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_anes_work
-- ----------------------------
INSERT INTO `t_anes_work` VALUES ('afb9c91f93de451485040f4a70174387', '2014-12-21 00:00:00', 'G', 'aaaa', '', '0', '', 'A', '3e3ea84b79f54747a1b25fa0439d2b36', '1', '10', '5', 'administrator', '2014-12-21 18:34:38', 'administrator', '2014-12-21 19:47:35');
INSERT INTO `t_anes_work` VALUES ('7064253c435f4e8eaa1388048bac05ea', '2014-12-10 00:00:00', 'E', '1234567890', 'asc', '122', 'sfadsfasdfsad', 'C', 'f161005f9ebb4e1785b9e2315a1ab39a', '1', '0', '0', 'administrator', '2014-12-21 19:43:33', 'administrator', '2014-12-21 20:27:01');

-- ----------------------------
-- Table structure for `t_sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `cls_code` varchar(32) collate utf8_unicode_ci NOT NULL COMMENT '分类编码',
  `cls_name` varchar(32) collate utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `code` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '编码',
  `name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '名称',
  `status` varchar(3) collate utf8_unicode_ci NOT NULL,
  `remark` varchar(100) collate utf8_unicode_ci default NULL,
  `creator` varchar(20) collate utf8_unicode_ci default NULL,
  `create_dt` datetime default NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000001', 'USER_TYPE', '用户类型', 'ADMIN', '系统管理员', 'ON', null, null, null, null, '2014-12-20 15:05:17');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000005', 'BOOL', '是否', 'N', '否', 'ON', null, null, null, null, '2014-12-20 15:04:29');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000006', 'BOOL', '是否', 'Y', '是', 'ON', null, null, null, null, '2014-12-20 15:04:33');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000007', 'CARD_TYPE', '证件类型', '1', '身份证', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000008', 'CARD_TYPE', '证件类型', '2', '护照', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000009', 'CARD_TYPE', '证件类型', '3', '军官证', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000010', 'CLIENT', '客户端', 'MB', '手机', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000011', 'CLIENT', '客户端', 'PC', '电脑', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000012', 'CLIENT', '客户端', 'PD', '平板电脑', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000017', 'EDUCATION', '学历', '01', '博士', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000018', 'EDUCATION', '学历', '02', '硕士', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000019', 'EDUCATION', '学历', '03', '本科', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000020', 'EDUCATION', '学历', '04', '大专', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000021', 'EDUCATION', '学历', '05', '中专', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000022', 'EDUCATION', '学历', '06', '高中', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000023', 'EDUCATION', '学历', '07', '初中及以下', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('1eef5ff0da381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'D', '骨科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('1ef14640da381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'E', '手足外科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('1ef365ceda381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'F', '脑外科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('1ef5e351da381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'G', '口腔科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('1ef8189bda381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'H', '耳鼻喉科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d05c2ecfda381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'A', '气管内全身麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d05dc5c7da381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'B', '腰硬联合麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d05f0ae6da381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'C', '硬膜外麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d060b97fda381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'D', '神经阻滞麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d062d8eeda381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'E', '支气管内麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('d0650c0bda381032b8170c6cf8970b72', 'ANES_METHOD', '麻醉方式', 'F', '强化麻醉', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000120', 'SEX', '性别', 'U', '未知', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000121', 'SEX', '性别', 'M', '男', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000122', 'SEX', '性别', 'F', '女', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('cb64dfbcda371032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'A', '普外科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000124', 'STATUS', '状态', 'OFF', '无效', 'ON', null, null, null, null, '2014-12-20 17:11:32');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000125', 'STATUS', '状态', 'ON', '有效', 'ON', null, null, null, null, '2014-12-21 14:24:26');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000126', 'SYSTEM_SETUP', '系统设置', 'LUMS', '30', 'ON', '用户锁定时长（分钟）\r\n', null, null, null, '2014-12-20 15:06:39');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000127', 'SYSTEM_SETUP', '系统设置', 'TTFL', '3', 'ON', '密码错误次数', null, null, null, '2014-12-20 15:06:17');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000013', 'USER_STATUS', '用户状态', 'ON', '正常', 'ON', null, null, null, null, '2014-12-20 16:57:59');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000014', 'USER_STATUS', '用户状态', 'OFF', '停用', 'ON', null, null, null, null, '2014-12-20 16:58:41');
INSERT INTO `t_sys_dict` VALUES ('1eebe2eada381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'B', '妇科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('1eedfa17da381032b8170c6cf8970b72', 'DEPARTMENT', '科室', 'C', '产科', 'ON', null, null, null, null, null);
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000002', 'USER_TYPE', '用户类型', 'COM', '普通用户', 'ON', null, null, null, null, '2014-12-20 16:57:08');

-- ----------------------------
-- Table structure for `t_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `name` varchar(64) collate utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) collate utf8_unicode_ci default NULL,
  `uri` varchar(100) collate utf8_unicode_ci default NULL COMMENT '链接地址',
  `order_no` tinyint(4) default NULL,
  `status` varchar(3) collate utf8_unicode_ci NOT NULL,
  `remark` varchar(100) collate utf8_unicode_ci default NULL,
  `creator` varchar(20) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime NOT NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('sys-role', '角色管理', 'sys', '/views/sys/role/list.html', '2', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-21 20:13:26');
INSERT INTO `t_sys_menu` VALUES ('sys', '系统管理', null, null, '1', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-21 20:13:26');
INSERT INTO `t_sys_menu` VALUES ('sys-user', '用户管理', 'sys', '/views/sys/user/list.html', '1', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-21 20:13:26');
INSERT INTO `t_sys_menu` VALUES ('anes', '工作量管理', null, null, '2', 'ON', null, 'system', '2014-12-21 15:05:28', 'system', '2014-12-21 20:13:26');
INSERT INTO `t_sys_menu` VALUES ('anes-anesthetist', '医生管理', 'anes', '/views/anes/anesthetist/list.html', '2', 'ON', null, 'system', '2014-12-21 15:05:28', 'system', '2014-12-21 20:13:26');
INSERT INTO `t_sys_menu` VALUES ('anes-work', '工作量管理', 'anes', '/views/anes/work/list.html', '1', 'ON', null, 'system', '2014-12-21 17:43:49', 'system', '2014-12-21 20:13:26');

-- ----------------------------
-- Table structure for `t_sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `menu_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `name` varchar(64) collate utf8_unicode_ci NOT NULL,
  `cls` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '所在类',
  `method` varchar(100) collate utf8_unicode_ci NOT NULL COMMENT '所在方法',
  PRIMARY KEY  (`id`),
  KEY `fk_menu_id` (`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES ('sys-role-search', 'sys-role', '搜索', 'com.xjj.anes.mvc.controller.sys.RoleController', 'search');
INSERT INTO `t_sys_permission` VALUES ('sys-user-delete', 'sys-user', '删除', 'com.xjj.anes.mvc.controller.sys.UserController', 'delete');
INSERT INTO `t_sys_permission` VALUES ('sys-user-search', 'sys-user', '搜索', 'com.xjj.anes.mvc.controller.sys.UserController', 'search');
INSERT INTO `t_sys_permission` VALUES ('sys-user-insert', 'sys-user', '新增', 'com.xjj.anes.mvc.controller.sys.UserController', 'insert');
INSERT INTO `t_sys_permission` VALUES ('sys-user-update', 'sys-user', '修改', 'com.xjj.anes.mvc.controller.sys.UserController', 'update');
INSERT INTO `t_sys_permission` VALUES ('sys-user-resetPwd', 'sys-user', '重置密码', 'com.xjj.anes.mvc.controller.sys.UserController', 'resetPwd');
INSERT INTO `t_sys_permission` VALUES ('sys-role-delete', 'sys-role', '删除', 'com.xjj.anes.mvc.controller.sys.RoleController', 'delete');
INSERT INTO `t_sys_permission` VALUES ('sys-role-insert', 'sys-role', '新增', 'com.xjj.anes.mvc.controller.sys.RoleController', 'insert');
INSERT INTO `t_sys_permission` VALUES ('sys-role-update', 'sys-role', '修改', 'com.xjj.anes.mvc.controller.sys.RoleController', 'update');
INSERT INTO `t_sys_permission` VALUES ('sys-role-getRoleMenus', 'sys-role', '分配权限', 'com.xjj.anes.mvc.controller.sys.RoleController', 'getRoleMenus');
INSERT INTO `t_sys_permission` VALUES ('sys-role-saveRoleMenus', 'sys-role', '保存权限', 'com.xjj.anes.mvc.controller.sys.RoleController', 'saveRoleMenus');
INSERT INTO `t_sys_permission` VALUES ('anes-anesthetist-delete', 'anes-anesthetist', '删除', 'com.xjj.anes.mvc.controller.anes.AnesthetistController', 'delete');
INSERT INTO `t_sys_permission` VALUES ('anes-anesthetist-insert', 'anes-anesthetist', '新增', 'com.xjj.anes.mvc.controller.anes.AnesthetistController', 'insert');
INSERT INTO `t_sys_permission` VALUES ('anes-anesthetist-search', 'anes-anesthetist', '搜索', 'com.xjj.anes.mvc.controller.anes.AnesthetistController', 'search');
INSERT INTO `t_sys_permission` VALUES ('anes-anesthetist-update', 'anes-anesthetist', '修改', 'com.xjj.anes.mvc.controller.anes.AnesthetistController', 'update');
INSERT INTO `t_sys_permission` VALUES ('anes-work-delete', 'anes-work', '删除', 'com.xjj.anes.mvc.controller.anes.WorkController', 'delete');
INSERT INTO `t_sys_permission` VALUES ('anes-work-insert', 'anes-work', '新增', 'com.xjj.anes.mvc.controller.anes.WorkController', 'insert');
INSERT INTO `t_sys_permission` VALUES ('anes-work-search', 'anes-work', '搜索', 'com.xjj.anes.mvc.controller.anes.WorkController', 'search');
INSERT INTO `t_sys_permission` VALUES ('anes-work-update', 'anes-work', '修改', 'com.xjj.anes.mvc.controller.anes.WorkController', 'update');

-- ----------------------------
-- Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `name` varchar(10) collate utf8_unicode_ci NOT NULL,
  `status` varchar(3) collate utf8_unicode_ci NOT NULL COMMENT '状态：ON/OFF',
  `remark` varchar(100) collate utf8_unicode_ci default NULL,
  `creator` varchar(20) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime NOT NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('super_admin', '系统超级管理员', 'ON', null, 'system', '2014-12-21 11:59:01', null, null);
INSERT INTO `t_sys_role` VALUES ('system_admin', '系统管理员', 'ON', null, 'system', '2014-12-21 11:59:01', null, null);
INSERT INTO `t_sys_role` VALUES ('f57c67e2420b4eecba021deb46155e52', '是的飞洒的方式的放', 'ON', '', 'administrator', '2014-12-21 12:28:20', null, null);
INSERT INTO `t_sys_role` VALUES ('1890a55930af42b6ad8561d2b6098c3f', '啊啊啊', 'ON', '实打实的', 'administrator', '2014-12-21 12:28:09', null, null);

-- ----------------------------
-- Table structure for `t_sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `menu_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_role_id` (`role_id`),
  KEY `fk_menu_id` (`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('1e8e35ab95da494d9a134b37a262f126', 'super_admin', 'sys-role');
INSERT INTO `t_sys_role_menu` VALUES ('4a10bab009f4400482b11539066a4bf6', 'super_admin', 'sys-user');
INSERT INTO `t_sys_role_menu` VALUES ('d7636490836e41629228cd5cf2ee94e9', 'super_admin', 'sys');
INSERT INTO `t_sys_role_menu` VALUES ('4b23fd97f9f24b479d4dbf1cde5926ed', 'super_admin', 'anes');
INSERT INTO `t_sys_role_menu` VALUES ('1b0bf04ba8494241b1177f3b8345221f', 'f57c67e2420b4eecba021deb46155e52', 'sys-role');
INSERT INTO `t_sys_role_menu` VALUES ('ec17b30b0c2c4b44a6eeb7a3e9c12087', 'f57c67e2420b4eecba021deb46155e52', 'sys');
INSERT INTO `t_sys_role_menu` VALUES ('543ae56bb8bc43a6be53e9326e768763', 'super_admin', 'anes-anesthetist');
INSERT INTO `t_sys_role_menu` VALUES ('70ce4f98d4074b8590c42c68b8ac212a', 'super_admin', 'anes-work');

-- ----------------------------
-- Table structure for `t_sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `permission_id` varchar(32) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_role_id` (`role_id`),
  KEY `fk_permission_id` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------
INSERT INTO `t_sys_role_permission` VALUES ('af9f998f932e448e88141bddbe1f370f', 'super_admin', 'sys-role-delete');
INSERT INTO `t_sys_role_permission` VALUES ('f5f50cc105934f719f99dd7d42152038', 'super_admin', 'sys-user-update');
INSERT INTO `t_sys_role_permission` VALUES ('4d765b18d08447e6aa468577f954c6cc', 'super_admin', 'sys-user-delete');
INSERT INTO `t_sys_role_permission` VALUES ('c507feba9b424581ae5eaa43e8774c52', 'super_admin', 'sys-user-search');
INSERT INTO `t_sys_role_permission` VALUES ('bfb45e330b394ab59ac481dbda53d1fe', 'super_admin', 'sys-role-getRoleMenus');
INSERT INTO `t_sys_role_permission` VALUES ('b1c7fd70ebdd4cb48e90932d2583a256', 'super_admin', 'sys-user-insert');
INSERT INTO `t_sys_role_permission` VALUES ('5d0b9bd378994d84a856532ad5b005e7', 'super_admin', 'sys-user-resetPwd');
INSERT INTO `t_sys_role_permission` VALUES ('204450e10f32422bbd74207e67a88754', 'super_admin', 'sys-role-update');
INSERT INTO `t_sys_role_permission` VALUES ('1df33712a91d4be893e15ce8d220b508', 'super_admin', 'sys-role-search');
INSERT INTO `t_sys_role_permission` VALUES ('62c7955a67674ce09e390dc1fdcf9f57', 'super_admin', 'sys-role-insert');
INSERT INTO `t_sys_role_permission` VALUES ('7f6aef6505d147798beb871c18fd4937', 'super_admin', 'sys-role-saveRoleMenus');
INSERT INTO `t_sys_role_permission` VALUES ('d94d9de63565426882ae345a540c5869', 'super_admin', 'anes-work-search');
INSERT INTO `t_sys_role_permission` VALUES ('4ec11bff327c48a5b1c254d87060cf81', 'super_admin', 'anes-work-delete');
INSERT INTO `t_sys_role_permission` VALUES ('5a40396796914a3fb71fbab608009a20', 'super_admin', 'anes-anesthetist-search');
INSERT INTO `t_sys_role_permission` VALUES ('4516a62efd0c4ba6b7a2d30440dba9e0', 'super_admin', 'anes-anesthetist-update');
INSERT INTO `t_sys_role_permission` VALUES ('2a0f80272d5947ebabf4b64652892de7', 'super_admin', 'anes-anesthetist-delete');
INSERT INTO `t_sys_role_permission` VALUES ('d63f163d7df54cd1970817d6b9c776a1', 'super_admin', 'anes-anesthetist-insert');
INSERT INTO `t_sys_role_permission` VALUES ('ec70d7ad8208491f8c64f4af583c60d0', 'f57c67e2420b4eecba021deb46155e52', 'sys-role-delete');
INSERT INTO `t_sys_role_permission` VALUES ('b9d0c050455c4f82b0339293f06bcae8', 'f57c67e2420b4eecba021deb46155e52', 'sys-role-update');
INSERT INTO `t_sys_role_permission` VALUES ('63d694b7de1b4e3e9650015fd23f6bcb', 'f57c67e2420b4eecba021deb46155e52', 'sys-role-getRoleMenus');
INSERT INTO `t_sys_role_permission` VALUES ('c6249208e3db429a879c1dde548fa42f', 'f57c67e2420b4eecba021deb46155e52', 'sys-role-search');
INSERT INTO `t_sys_role_permission` VALUES ('77bc6cef390d49a282588e37462cab9a', 'super_admin', 'anes-work-insert');
INSERT INTO `t_sys_role_permission` VALUES ('22cf2c8a1ae547c38ac98c8ef267534f', 'super_admin', 'anes-work-update');

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `code` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '登录账号',
  `password` varchar(32) collate utf8_unicode_ci NOT NULL COMMENT 'MD5',
  `name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '姓名',
  `status` varchar(3) collate utf8_unicode_ci NOT NULL COMMENT 'ON/OFF',
  `user_type` varchar(5) collate utf8_unicode_ci NOT NULL COMMENT 'SYS/COM',
  `role_id` varchar(32) collate utf8_unicode_ci default NULL COMMENT '所属角色',
  `remark` varchar(100) collate utf8_unicode_ci default NULL,
  `unlock_dt` datetime default NULL COMMENT '解锁时间',
  `creator` varchar(20) collate utf8_unicode_ci NOT NULL,
  `create_dt` datetime NOT NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `fk_role_id` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('administrator', 'administrator', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理', 'ON', 'ADMIN', 'super_admin', '系统自动创建', null, 'system', '2014-12-19 22:26:57', '超级管理员', '2014-12-21 14:02:29');
INSERT INTO `t_sys_user` VALUES ('a4e38b5be7c2479298394ffbf3a254d2', 'ddddd', '25D55AD283AA400AF464C76D713C07AD', '11111111111', 'ON', 'COM', 'f57c67e2420b4eecba021deb46155e52', 'aaaaa', null, '超级管理员', '2014-12-20 22:51:43', 'administrator', '2014-12-21 14:01:20');
