/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : fivechess

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-09-26 20:17:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(30) NOT NULL,
  `fileName` varchar(50) DEFAULT NULL,
  `winNum` int(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('clx', 'E:\\filename\\0', '98');
INSERT INTO `user` VALUES ('czf', 'E:\\filename\\0', '75');
INSERT INTO `user` VALUES ('ww', 'E:\\filename\\0', '98');
INSERT INTO `user` VALUES ('wxm', 'E:\\filename\\0', '58');
INSERT INTO `user` VALUES ('zzb', 'E:\\filename\\0', '56');
