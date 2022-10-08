-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 sales 的数据库结构
CREATE DATABASE IF NOT EXISTS `sales` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sales`;

-- 导出  表 sales.product 结构
CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `brand` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `madein` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 正在导出表  sales.product 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT IGNORE INTO `product` (`id`, `name`, `brand`, `madein`, `price`) VALUES
	(1, '1', '1', '1', 1),
	(2, '2', '2', '2', 2),
	(3, '3', '3', '3', 3),
	(4, '4', '4', '4', 4),
	(5, '5', '5', '5', 5),
	(6, '6', '6', '6', 6),
	(7, '7', '7', '7', 7),
	(8, '8', '8', '8', 8),
	(9, '9', '9', '9', 9),
	(10, 'q', 'q', 'q', 12),
	(11, 'test', 'test', 'China', 102),
	(12, '找那个333', '侧王妃和', '士大夫', 34),
	(13, '啊是', '撒打发但是', '士大夫', 56),
	(14, '不阿红', '撒旦发射点', '是发', 23),
	(15, '100', '100', '100', 100);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
