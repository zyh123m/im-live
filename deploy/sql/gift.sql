/*
 Navicat Premium Data Transfer

 Source Server         : mysql.im-live
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : mysql.im-live:3306
 Source Schema         : gift

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 15/01/2024 16:00:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
use gift;
-- ----------------------------
-- Table structure for gift
-- ----------------------------
DROP TABLE IF EXISTS `gift`;
CREATE TABLE `gift`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `gift_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '礼物ID',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '礼物名称',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '礼物图片',
  `category_id` int(0) NOT NULL COMMENT '分类ID',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '礼物信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gift
-- ----------------------------

-- ----------------------------
-- Table structure for gift_category
-- ----------------------------
DROP TABLE IF EXISTS `gift_category`;
CREATE TABLE `gift_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '组别名称',
  `type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '组别类型',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '礼物组别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gift_category
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
