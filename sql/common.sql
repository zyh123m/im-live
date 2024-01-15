/*
 Navicat Premium Data Transfer

 Source Server         : mysql.im-live
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : mysql.im-live:3306
 Source Schema         : common

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 15/01/2024 16:00:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_id_generate_config
-- ----------------------------
DROP TABLE IF EXISTS `t_id_generate_config`;
CREATE TABLE `t_id_generate_config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `next_threshold` bigint(0) NULL DEFAULT NULL COMMENT '当前id所在阶段的闻值',
  `init_num` bigint(0) NULL DEFAULT NULL COMMENT '初始化值',
  `current_start` bigint(0) NULL DEFAULT NULL COMMENT '当前id所在阶段的开始值',
  `step` int(0) NULL DEFAULT NULL COMMENT 'id递增区间',
  `is_seq` tinyint(0) NULL DEFAULT NULL COMMENT '是否有序 (0无序，1有序)',
  `id_prefix` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务前缀码如果没有则返回时不携带',
  `version` int(0) NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_id_generate_config
-- ----------------------------
INSERT INTO `t_id_generate_config` VALUES (8, '用户id生成策略', 11650, 10000, 11600, 50, 1, 'user_id', 32, '2023-12-26 08:58:32', '2024-01-15 05:53:45');

SET FOREIGN_KEY_CHECKS = 1;
