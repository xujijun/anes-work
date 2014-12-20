/*
Navicat MySQL Data Transfer

Source Server         : LocalDB
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : anes-work

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2014-12-20 21:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000120', 'SEX', '性别', 'U', '未知', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000121', 'SEX', '性别', 'M', '男', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000122', 'SEX', '性别', 'F', '女', 'ON', null, null, null, null, '2014-12-20 15:01:03');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000123', 'STATUS', '状态', 'DEL', '已删除', 'ON', null, null, null, null, '2014-12-20 17:11:39');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000124', 'STATUS', '状态', 'OFF', '无效', 'ON', null, null, null, null, '2014-12-20 17:11:32');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000125', 'STATUS', '状态', 'ON', '正常', 'ON', null, null, null, null, '2014-12-20 15:02:39');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000126', 'SYSTEM_SETUP', '系统设置', 'LUMS', '30', 'ON', '用户锁定时长（分钟）\r\n', null, null, null, '2014-12-20 15:06:39');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000127', 'SYSTEM_SETUP', '系统设置', 'TTFL', '3', 'ON', '密码错误次数', null, null, null, '2014-12-20 15:06:17');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000013', 'USER_STATUS', '用户状态', 'ON', '正常', 'ON', null, null, null, null, '2014-12-20 16:57:59');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000014', 'USER_STATUS', '用户状态', 'OFF', '停用', 'ON', null, null, null, null, '2014-12-20 16:58:41');
INSERT INTO `t_sys_dict` VALUES ('DICT-0000000002', 'USER_TYPE', '用户类型', 'COM', '普通用户', 'ON', null, null, null, null, '2014-12-20 16:57:08');

-- ----------------------------
-- Table structure for `t_sys_dict_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_org`;
CREATE TABLE `t_sys_dict_org` (
  `ID` varchar(64) NOT NULL COMMENT 'ID标识',
  `CLS_CODE` varchar(64) NOT NULL COMMENT '分类编码',
  `CLS_NAME` varchar(64) NOT NULL COMMENT '分类名称',
  `CODE` varchar(10) NOT NULL COMMENT '编号',
  `NAME` varchar(64) NOT NULL COMMENT '名称',
  `ORDER_NO` decimal(10,0) default NULL COMMENT '排序号',
  `STATUS` varchar(10) NOT NULL COMMENT '状态 ON:有效，OFF:无效,DEL:删除',
  `REMARK` varchar(100) default NULL COMMENT '备注',
  `CREATER` varchar(20) NOT NULL COMMENT '创建者',
  `CREATE_DT` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATER` varchar(20) default NULL COMMENT '更新者',
  `UPDATE_DT` timestamp NULL default '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_sys_dict_org
-- ----------------------------
INSERT INTO `t_sys_dict_org` VALUES ('5ed8df162e884c319ec9881788edd69d', 'USER_TYPE', '用户类型', 'SYS', '系统管理员', null, '1', '', '超级管理员', '2014-07-24 18:01:37', null, '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000005', 'BOOL', '是否', '0', '否', '5', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000006', 'BOOL', '是否', '1', '是', '6', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000007', 'CARD_TYPE', '证件类型', '1', '身份证', '7', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000008', 'CARD_TYPE', '证件类型', '2', '护照', '8', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000009', 'CARD_TYPE', '证件类型', '3', '军官证', '9', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000010', 'CLIENT', '客户端', 'MB', '手机', '10', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000011', 'CLIENT', '客户端', 'PC', '电脑', '11', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000012', 'CLIENT', '客户端', 'PD', '平板电脑', '12', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000017', 'EDUCATION', '学历', '01', '博士', '17', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000018', 'EDUCATION', '学历', '02', '硕士', '18', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000019', 'EDUCATION', '学历', '03', '本科', '19', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000020', 'EDUCATION', '学历', '04', '大专', '20', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000021', 'EDUCATION', '学历', '05', '中专', '21', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000022', 'EDUCATION', '学历', '06', '高中', '22', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000023', 'EDUCATION', '学历', '07', '初中及以下', '23', '1', '备注', 'XYT-Import', '2014-07-14 10:16:35', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000034', 'FS_DOMAIN', '文件服务器域名', 'A', 'A服务器', '34', '1', '备注', 'XYT-Import', '2014-07-14 10:16:36', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000035', 'FS_DOMAIN', '文件服务器域名', 'B', 'B服务器', '35', '1', '备注', 'XYT-Import', '2014-07-14 10:16:36', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000036', 'FS_DOMAIN', '文件服务器域名', 'C', 'C服务器', '36', '1', '备注', 'XYT-Import', '2014-07-14 10:16:36', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000037', 'FS_DOMAIN', '文件服务器域名', 'public', '公用文件服务器', '37', '1', '备注', 'XYT-Import', '2014-07-14 10:16:37', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000076', 'PITURE_SIZE', '图片规格', 'B', '大图片（800*600）', '76', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000077', 'PITURE_SIZE', '图片规格', 'M', '中图片（400*300）', '77', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000078', 'PITURE_SIZE', '图片规格', 'S', '小图片（200*200）', '78', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000088', 'REGION_STATUS', '地区状态', '-1', '删除', '88', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000089', 'REGION_STATUS', '地区状态', '0', '无效', '89', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000090', 'REGION_STATUS', '地区状态', '1', '有效', '90', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000091', 'REGION_TYPE', '地区类型', 'C', '市', '91', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000092', 'REGION_TYPE', '地区类型', 'D', '区/县', '92', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000093', 'REGION_TYPE', '地区类型', 'P', '省', '93', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000094', 'REGION_TYPE', '地区类型', 'S', '街道', '94', '1', '备注', 'XYT-Import', '2014-07-14 10:16:39', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000095', 'REGION_TYPE', '地区类型', 'T', '镇/乡', '95', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000096', 'REGION_TYPE', '地区类型', 'V', '社区/村', '96', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000097', 'REGION_TYPE', '地区类型', 'Z', '大区', '97', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000114', 'SEND_STATUS', '邮件/短信发送状态', '-1', '发送失败', '114', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000115', 'SEND_STATUS', '邮件/短信发送状态', '0', '未发送', '115', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000116', 'SEND_STATUS', '邮件/短信发送状态', '1', '发送成功', '116', '1', '备注', 'XYT-Import', '2014-07-14 10:16:40', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000117', 'SENSITIVE_WORDS_TYPE', '敏感词类型', 'CHAR', '特殊字符', '117', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000118', 'SENSITIVE_WORDS_TYPE', '敏感词类型', 'REGEXP', '正则表达式', '118', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000119', 'SENSITIVE_WORDS_TYPE', '敏感词类型', 'WORDS', '敏感词', '119', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000120', 'SEX', '性别', 'U', '未知', '120', '1', '备注', 'XYT-Import', '2014-07-24 15:50:26', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000121', 'SEX', '性别', 'M', '男', '121', '1', '备注', 'XYT-Import', '2014-07-24 15:50:28', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000122', 'SEX', '性别', 'F', '女', '122', '1', '备注', 'XYT-Import', '2014-07-24 15:50:29', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000123', 'STATUS', '状态', '-1', '逻辑删除', '123', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000124', 'STATUS', '状态', '0', '暂停', '124', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000125', 'STATUS', '状态', '1', '正常', '125', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000126', 'SYSTEM_SETUP', '系统设置', 'LUMS', '30', '126', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000127', 'SYSTEM_SETUP', '系统设置', 'TTFL', '3', '127', '1', '备注', 'XYT-Import', '2014-07-14 10:16:41', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000140', 'URI_PREFIX', 'URI前缀', '/api', 'API', '140', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000141', 'URI_PREFIX', 'URI前缀', '/mb', '移动', '141', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000142', 'URI_PREFIX', 'URI前缀', '/pc', '电脑', '142', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000143', 'URI_PREFIX', 'URI前缀', '/pd', '平板电脑', '143', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000145', 'USER_TYPE', '用户类型', 'MBR', '公众会员', '145', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000146', 'USER_TYPE', '用户类型', 'OPT', '运营商', '146', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');
INSERT INTO `t_sys_dict_org` VALUES ('DICT-0000000147', 'USER_TYPE', '用户类型', 'ORG', '政府部门', '147', '1', '备注', 'XYT-Import', '2014-07-14 10:16:42', '', '0000-00-00 00:00:00');

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
INSERT INTO `t_sys_menu` VALUES ('sys-role', '角色管理', 'sys', '/views/sys/role/list.html', '2', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-20 21:09:21');
INSERT INTO `t_sys_menu` VALUES ('sys', '系统管理', null, null, '1', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-20 21:09:21');
INSERT INTO `t_sys_menu` VALUES ('sys-user', '用户管理', 'sys', '/views/sys/user/list.html', '1', 'ON', null, 'system', '2014-12-19 22:26:57', 'system', '2014-12-20 21:09:21');

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
INSERT INTO `t_sys_permission` VALUES ('sys-user-search', 'sys-user', '搜索', 'com.xjj.anes.mvc.controller.sys.UserController', 'search');
INSERT INTO `t_sys_permission` VALUES ('sys-user-insert', 'sys-user', '新增', 'com.xjj.anes.mvc.controller.sys.UserController', 'insert');

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
INSERT INTO `t_sys_role` VALUES ('super_admin', '系统超级管理员', 'ON', null, 'system', '2014-12-19 22:26:57', null, null);
INSERT INTO `t_sys_role` VALUES ('system_admin', '系统管理员', 'ON', null, 'system', '2014-12-19 22:26:57', null, null);

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
INSERT INTO `t_sys_role_menu` VALUES ('2fffcb508213464e9f1938e28ceb59b1', 'super_admin', 'sys');
INSERT INTO `t_sys_role_menu` VALUES ('5dd7446399ea4144a5ed33959deb69f7', 'super_admin', 'sys-user');
INSERT INTO `t_sys_role_menu` VALUES ('6f127cee730241eba637759dd96acfb8', 'super_admin', 'sys-role');

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
INSERT INTO `t_sys_role_permission` VALUES ('392fd7c3dd45430394a5883e403debde', 'super_admin', 'sys-role-search');
INSERT INTO `t_sys_role_permission` VALUES ('7fc9fc69c5ac4808bdfd6a5c732e95e5', 'super_admin', 'sys-user-search');
INSERT INTO `t_sys_role_permission` VALUES ('d67626a9e54d4d7991cbdf2cfa472ce3', 'super_admin', 'sys-user-insert');

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
INSERT INTO `t_sys_user` VALUES ('administrator', 'administrator', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理员', 'ON', 'ADMIN', 'super_admin', '系统自动创建', null, 'system', '2014-12-19 22:26:57', null, '2014-12-20 14:35:59');
INSERT INTO `t_sys_user` VALUES ('23a26b137b144cf1a639cd22ee01d36d', 'sfdfsd', '25D55AD283AA400AF464C76D713C07AD', 'sssss', 'ON', 'COM', 'system_admin', 'sdfd', null, '超级管理员', '2014-12-20 21:10:21', null, null);
INSERT INTO `t_sys_user` VALUES ('19608315cab44593a851f089d578a9f2', 'sss', '25D55AD283AA400AF464C76D713C07AD', 'fsdfds', 'ON', 'COM', 'super_admin', 'sdfsdf', null, '超级管理员', '2014-12-20 21:10:33', null, null);
