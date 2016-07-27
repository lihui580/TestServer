-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.14-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 testserver 的数据库结构
DROP DATABASE IF EXISTS `testserver`;
CREATE DATABASE IF NOT EXISTS `testserver` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `testserver`;


-- 导出  表 testserver.t_admin 结构
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE IF NOT EXISTS `t_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `admin` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理员';

-- 正在导出表  testserver.t_admin 的数据：~0 rows (大约)
DELETE FROM `t_admin`;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;


-- 导出  表 testserver.t_employee 结构
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE IF NOT EXISTS `t_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '工号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `password` text COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_number` (`uuid`,`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺角色';

-- 正在导出表  testserver.t_employee 的数据：~0 rows (大约)
DELETE FROM `t_employee`;
/*!40000 ALTER TABLE `t_employee` DISABLE KEYS */;
INSERT INTO `t_employee` (`id`, `uuid`, `number`, `name`, `password`, `status`, `create_time`, `update_time`, `exist`) VALUES
	(1, '3A71AA0279634D718ECF5503D4748FDF', '001', '小李', '8516C42292E7932976BF83AC511EC09E8EC1FB6FAEFD2C1D42012F3D216DD0EBAA005CEC5C078F8726E96023BA07B3E576E61137CEC5CA2589DCFA18A4A5885455C5D6785AFF73DF13D2D47639715FDE984AEB2CB0BBA161C4D369C7F35E3D34C7C966A3BEB99FB1F30B1EA7475ECC4B7FAFDC1CE0EABB83F84343706D0B2D77', 1, '2016-06-27 11:04:16', '2016-06-27 11:04:16', 1);
/*!40000 ALTER TABLE `t_employee` ENABLE KEYS */;


-- 导出  表 testserver.t_employee_info 结构
DROP TABLE IF EXISTS `t_employee_info`;
CREATE TABLE IF NOT EXISTS `t_employee_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'UUID',
  `employee_number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '工号',
  `mobile` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色电话',
  `resume` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺角色信息';

-- 正在导出表  testserver.t_employee_info 的数据：~0 rows (大约)
DELETE FROM `t_employee_info`;
/*!40000 ALTER TABLE `t_employee_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_employee_info` ENABLE KEYS */;


-- 导出  表 testserver.t_login_log 结构
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE IF NOT EXISTS `t_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `session` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户端',
  `expire` datetime NOT NULL COMMENT '过期时间',
  `user_agent` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录平台',
  `operation` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '操作地址',
  `address` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用登录记录';

-- 正在导出表  testserver.t_login_log 的数据：~0 rows (大约)
DELETE FROM `t_login_log`;
/*!40000 ALTER TABLE `t_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_login_log` ENABLE KEYS */;


-- 导出  表 testserver.t_permission 结构
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE IF NOT EXISTS `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `clazz` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `method` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户的权限表';

-- 正在导出表  testserver.t_permission 的数据：~0 rows (大约)
DELETE FROM `t_permission`;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;


-- 导出  表 testserver.t_role 结构
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `rank` int(11) NOT NULL COMMENT '等级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_name` (`uuid`,`number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺角色等级';

-- 正在导出表  testserver.t_role 的数据：~0 rows (大约)
DELETE FROM `t_role`;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`id`, `uuid`, `number`, `name`, `rank`, `create_time`, `update_time`, `exist`) VALUES
	(1, '3A71AA0279634D718ECF5503D4748FDF', '0001', '总经理', 1, '2016-06-27 11:34:32', '2016-06-27 11:34:32', 1);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;


-- 导出  表 testserver.t_role_permission 结构
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'UUID',
  `number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限名称',
  `clazz` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限作用的类',
  `method` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限作用的方法',
  `selected` int(11) NOT NULL COMMENT '是否选择 0.过时 1.未选中 2.选中',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_number_name_clazz` (`uuid`,`number`,`name`,`clazz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺角色权限';

-- 正在导出表  testserver.t_role_permission 的数据：~0 rows (大约)
DELETE FROM `t_role_permission`;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;


-- 导出  表 testserver.t_store 结构
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE IF NOT EXISTS `t_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺名称',
  `logo` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺logo',
  `resume` varchar(240) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺简述',
  `status` int(11) NOT NULL COMMENT '店铺状态0-关店 1-开店',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shop` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商店';

-- 正在导出表  testserver.t_store 的数据：~0 rows (大约)
DELETE FROM `t_store`;
/*!40000 ALTER TABLE `t_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store` ENABLE KEYS */;


-- 导出  表 testserver.t_store_info 结构
DROP TABLE IF EXISTS `t_store_info`;
CREATE TABLE IF NOT EXISTS `t_store_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `store_name` bigint(20) NOT NULL COMMENT '店铺ID',
  `details` text COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺详情',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_store_name` (`uuid`,`store_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺信息';

-- 正在导出表  testserver.t_store_info 的数据：~0 rows (大约)
DELETE FROM `t_store_info`;
/*!40000 ALTER TABLE `t_store_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store_info` ENABLE KEYS */;


-- 导出  表 testserver.t_store_location 结构
DROP TABLE IF EXISTS `t_store_location`;
CREATE TABLE IF NOT EXISTS `t_store_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `store_name` bigint(20) NOT NULL COMMENT '店铺id',
  `lat` double NOT NULL COMMENT '维度',
  `lng` double NOT NULL COMMENT '经度',
  `address` varchar(240) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_store_name` (`uuid`,`store_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺位置';

-- 正在导出表  testserver.t_store_location 的数据：~0 rows (大约)
DELETE FROM `t_store_location`;
/*!40000 ALTER TABLE `t_store_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store_location` ENABLE KEYS */;


-- 导出  表 testserver.t_store_log 结构
DROP TABLE IF EXISTS `t_store_log`;
CREATE TABLE IF NOT EXISTS `t_store_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'UUID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `store_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺名称',
  `store_role_id` bigint(20) NOT NULL,
  `store_role_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `store_employee_id` bigint(20) NOT NULL COMMENT '店铺角色ID',
  `store_employee__name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺角色名称',
  `operation` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '操作地址',
  `operation_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '操作类型',
  `params` text COLLATE utf8_unicode_ci NOT NULL COMMENT '入参',
  `result` text COLLATE utf8_unicode_ci NOT NULL COMMENT '出参',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺操作记录';

-- 正在导出表  testserver.t_store_log 的数据：~0 rows (大约)
DELETE FROM `t_store_log`;
/*!40000 ALTER TABLE `t_store_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store_log` ENABLE KEYS */;


-- 导出  表 testserver.t_store_role_employee 结构
DROP TABLE IF EXISTS `t_store_role_employee`;
CREATE TABLE IF NOT EXISTS `t_store_role_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `store_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺名称',
  `role_number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色序号',
  `employee_number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '雇员序号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺和角色和雇员的三方关系表';

-- 正在导出表  testserver.t_store_role_employee 的数据：~0 rows (大约)
DELETE FROM `t_store_role_employee`;
/*!40000 ALTER TABLE `t_store_role_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store_role_employee` ENABLE KEYS */;


-- 导出  表 testserver.t_store_setting 结构
DROP TABLE IF EXISTS `t_store_setting`;
CREATE TABLE IF NOT EXISTS `t_store_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'uuid',
  `store_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '店铺ID',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '设置的名称',
  `type` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '设置的类型 1.单选 2.多选',
  `option` int(11) NOT NULL COMMENT '已选项',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='店铺设置\r\n选项设置';

-- 正在导出表  testserver.t_store_setting 的数据：~0 rows (大约)
DELETE FROM `t_store_setting`;
/*!40000 ALTER TABLE `t_store_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store_setting` ENABLE KEYS */;


-- 导出  表 testserver.t_user 结构
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `type` int(11) NOT NULL COMMENT '账号类型1.手机 2.email 3.账号',
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'UUID',
  `status` int(11) NOT NULL COMMENT '状态(0.未激活 1.激活 2.冻结)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户列表';

-- 正在导出表  testserver.t_user 的数据：~2 rows (大约)
DELETE FROM `t_user`;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `user`, `type`, `uuid`, `status`, `create_time`, `update_time`, `exist`) VALUES
	(1, '15577224200', 3, '3A71AA0279634D718ECF5503D4748FDF', 1, '2016-06-27 10:53:46', '2016-06-27 10:53:46', 1),
	(2, '15577224201', 3, '396A0D6DE59D454A85252339482FAB14', 1, '2016-06-27 10:56:06', '2016-06-27 10:56:06', 1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- 导出  表 testserver.t_user_info 结构
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE IF NOT EXISTS `t_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户',
  `image` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '头像',
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
  `signature` varchar(140) COLLATE utf8_unicode_ci NOT NULL COMMENT '签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户资料';

-- 正在导出表  testserver.t_user_info 的数据：~0 rows (大约)
DELETE FROM `t_user_info`;
/*!40000 ALTER TABLE `t_user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_info` ENABLE KEYS */;


-- 导出  表 testserver.t_uuid 结构
DROP TABLE IF EXISTS `t_uuid`;
CREATE TABLE IF NOT EXISTS `t_uuid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uuid` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '全局ID',
  `password` text COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `exist` int(11) NOT NULL COMMENT '是否存在 1.存在 2.不存在',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='全局ID';

-- 正在导出表  testserver.t_uuid 的数据：~2 rows (大约)
DELETE FROM `t_uuid`;
/*!40000 ALTER TABLE `t_uuid` DISABLE KEYS */;
INSERT INTO `t_uuid` (`id`, `uuid`, `password`, `status`, `create_time`, `update_time`, `exist`) VALUES
	(1, '3A71AA0279634D718ECF5503D4748FDF', '73628BB73B63245CEE4E8D3983F8740EE889C924E717E5438DE76379B19B7561A879BE867325FAA9DE8F76EB48BC800B0C1E7B38E53DAC7F62FEAB997F2732FB70FB62B9EA1D210DECDD1C90081AB84447B7EABE2423E4817EA44200FB261B744BAA6241AE5F9FFA94C90B99286B723F60270FDE564F05CC29F101A9E6B4B1B3', 1, '2016-06-27 10:53:46', '2016-06-27 10:53:46', 1),
	(2, '396A0D6DE59D454A85252339482FAB14', 'C5151E9B42D642A13ECA3EA0E8230C1E30FB0A4D517D1A7BECCD847ACB8A511063952793BAF705EFCED7A0A8F0E0DC6B15EA9E116741020CE05C26D376612788DAC98CF6E0E07B617D4C2EFE4643E8172954F014475BEF36F6AAE6262FBA0A4114BDC69B806823D0E528AE3E7B82FBCC92C6BB7DD9B71E8C860ACDBB5B88046E', 1, '2016-06-27 10:56:06', '2016-06-27 10:56:06', 1);
/*!40000 ALTER TABLE `t_uuid` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
