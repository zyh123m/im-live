/*
 Navicat Premium Data Transfer

 Source Server         : mysql.im-live
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : mysql.im-live:3306
 Source Schema         : living

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 15/01/2024 16:00:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间号',
  `room_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '房间名称',
  `cover_img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '分类ID',
  `anchor_id` int(0) NULL DEFAULT NULL COMMENT '主播ID',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '房间状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_stream_time` datetime(0) NULL DEFAULT NULL COMMENT '最后直播时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_id`(`room_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '直播间信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for room_user_ranking
-- ----------------------------
DROP TABLE IF EXISTS `room_user_ranking`;
CREATE TABLE `room_user_ranking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `room_id` int(0) NOT NULL COMMENT '房间号',
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `points` int(0) NOT NULL DEFAULT 0 COMMENT '积分',
  `last_update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_id_user_id`(`room_id`, `user_id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE,
  INDEX `points`(`points`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '直播间用户排行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_user_ranking
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
