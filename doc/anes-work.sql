/*
Navicat MySQL Data Transfer

Source Server         : LocalDB
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : anes-work

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2014-12-14 22:55:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` varchar(32) collate utf8_unicode_ci default NULL,
  `cls_code` varchar(32) collate utf8_unicode_ci default NULL COMMENT '分类编码',
  `cls_name` varchar(32) collate utf8_unicode_ci default NULL COMMENT '分类名称',
  `code` varchar(10) collate utf8_unicode_ci default NULL COMMENT '编码',
  `name` varchar(20) collate utf8_unicode_ci default NULL COMMENT '名称',
  `status` varchar(3) collate utf8_unicode_ci default NULL,
  `remark` varchar(100) collate utf8_unicode_ci default NULL,
  `creator` varchar(20) collate utf8_unicode_ci default NULL,
  `create_dt` datetime default NULL,
  `updater` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `name` varchar(64) collate utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) collate utf8_unicode_ci default NULL,
  `url` varchar(100) collate utf8_unicode_ci default NULL COMMENT '链接地址',
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
  `updator` varchar(20) collate utf8_unicode_ci default NULL,
  `update_dt` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `fk_role_id` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
