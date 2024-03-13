/*
 Navicat Premium Data Transfer

 Source Server         : mysql.im-live
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : mysql.im-live:3306
 Source Schema         : auth

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 15/01/2024 16:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
use auth;
-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`  (
  `id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `client_id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `client_id_issued_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `client_secret` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `client_secret_expires_at` datetime(0) NULL DEFAULT NULL,
  `client_name` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `client_authentication_methods` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `authorization_grant_types` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `redirect_uris` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `scopes` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `client_settings` varchar(2000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `token_settings` varchar(2000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
INSERT INTO `oauth2_registered_client` VALUES ('e101e068-b7ae-4df1-9b53-d88a2dde0d94', 'client2', '2024-01-04 11:00:36', '$2a$10$ta5B3v1W5JSuggxGjBMk1eY/rkF0Pp0nyfVAkWbLH5IhQwWpmHhg6', NULL, 'e101e068-b7ae-4df1-9b53-d88a2dde0d94', 'client_secret_post', 'refresh_token,client_credentials,password,authorization_code,sms_code', 'http://www.baidu.com', 'http://www.im.com/login', 'openid,profile,USER', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
INSERT INTO `oauth2_registered_client` VALUES ('e1993b40-55d3-4d2d-a7cb-24c003a1e971', 'client3', '2024-01-04 11:00:35', '$2a$10$uBjT0zSbb.hViQ0Iqka3a.LQqRD5pvHXg5NAQCe.Kc7DF7MTYKAeS', NULL, 'e1993b40-55d3-4d2d-a7cb-24c003a1e971', 'client_secret_post', 'refresh_token,client_credentials,password,authorization_code,sms_code', 'http://www.im.com/login', 'http://www.im.com/login', 'openid,profile,USER', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');

SET FOREIGN_KEY_CHECKS = 1;
