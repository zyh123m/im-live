/*
 Navicat Premium Data Transfer

 Source Server         : mysql.im-live
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : mysql.im-live:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 15/01/2024 16:00:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anchor_room
-- ----------------------------
DROP TABLE IF EXISTS `anchor_room`;
CREATE TABLE `anchor_room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `anchor_id` int(0) NOT NULL COMMENT '主播ID',
  `room_id` int(0) NOT NULL COMMENT '房间号',
  `create_time` datetime(0) NOT NULL COMMENT '房间创建时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '房间关闭时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_id`(`room_id`) USING BTREE,
  INDEX `anchor_id`(`anchor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '主播直播间关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of anchor_room
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_0
-- ----------------------------

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_1
-- ----------------------------

-- ----------------------------
-- Table structure for user_2
-- ----------------------------
DROP TABLE IF EXISTS `user_2`;
CREATE TABLE `user_2`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_2
-- ----------------------------

-- ----------------------------
-- Table structure for user_3
-- ----------------------------
DROP TABLE IF EXISTS `user_3`;
CREATE TABLE `user_3`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_3
-- ----------------------------

-- ----------------------------
-- Table structure for user_4
-- ----------------------------
DROP TABLE IF EXISTS `user_4`;
CREATE TABLE `user_4`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_4
-- ----------------------------

-- ----------------------------
-- Table structure for user_5
-- ----------------------------
DROP TABLE IF EXISTS `user_5`;
CREATE TABLE `user_5`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_5
-- ----------------------------

-- ----------------------------
-- Table structure for user_6
-- ----------------------------
DROP TABLE IF EXISTS `user_6`;
CREATE TABLE `user_6`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_6
-- ----------------------------

-- ----------------------------
-- Table structure for user_7
-- ----------------------------
DROP TABLE IF EXISTS `user_7`;
CREATE TABLE `user_7`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_7
-- ----------------------------

-- ----------------------------
-- Table structure for user_8
-- ----------------------------
DROP TABLE IF EXISTS `user_8`;
CREATE TABLE `user_8`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_8
-- ----------------------------

-- ----------------------------
-- Table structure for user_9
-- ----------------------------
DROP TABLE IF EXISTS `user_9`;
CREATE TABLE `user_9`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户姓名',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '用户状态;1:正常,2:禁用',
  `source` tinyint(0) NULL DEFAULT 1 COMMENT '用户注册来源;1:PC,2:Android, 3:iOS,4:Wap',
  `referrer` bigint(0) NULL DEFAULT 0 COMMENT '推荐人id',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否会员;0:否,1:是',
  `vip_expire_time` timestamp(0) NULL DEFAULT NULL COMMENT '会员过期时间',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '市',
  `region` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_9
-- ----------------------------

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `anchor_id` int(0) NOT NULL COMMENT '主播id',
  `created_time` datetime(0) NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `anchor_id`(`anchor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户关注主播表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `tag_id` int(0) NOT NULL,
  `created_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_tag
-- ----------------------------

-- ----------------------------
-- Table structure for user_watch_history
-- ----------------------------
DROP TABLE IF EXISTS `user_watch_history`;
CREATE TABLE `user_watch_history`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `anchor_id` int(0) NOT NULL COMMENT '主播ID',
  `room_id` int(0) NOT NULL COMMENT '房间号',
  `watch_start_time` datetime(0) NOT NULL COMMENT '观看开始时间',
  `watch_end_time` datetime(0) NULL DEFAULT NULL COMMENT '观看结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `anchor_id`(`anchor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户浏览历史表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_watch_history
-- ----------------------------

-- ----------------------------
-- Procedure structure for shard_user_table
-- ----------------------------
DROP PROCEDURE IF EXISTS `shard_user_table`;
delimiter ;;
CREATE PROCEDURE `shard_user_table`()
BEGIN
    DECLARE i INT DEFAULT 1;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN END;

    START TRANSACTION;

    WHILE i <= 100 DO
            SET @table = CONCAT('user','_', i);
            SET @sql = CONCAT('ALTER TABLE ', @table, ' MODIFY avatar VARCHAR(500) NULL');
            PREPARE stmt FROM @sql;
            EXECUTE stmt;
            DEALLOCATE PREPARE stmt;

            SET i = i + 1;
        END WHILE;

    COMMIT;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
