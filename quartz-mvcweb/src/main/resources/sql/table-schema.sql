-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.6.24-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 test.schedule_job 结构
CREATE TABLE IF NOT EXISTS `schedule_job` (
  `schedule_job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) NOT NULL,
  `alias_name` varchar(50) NOT NULL,
  `job_group` varchar(50) NOT NULL,
  `job_trigger` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `cron_expression` varchar(50) NOT NULL,
  `is_sync` tinyint(4) NOT NULL,
  `description` text NOT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`schedule_job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  test.schedule_job 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `schedule_job` DISABLE KEYS */;
INSERT INTO `schedule_job` (`schedule_job_id`, `job_name`, `alias_name`, `job_group`, `job_trigger`, `status`, `cron_expression`, `is_sync`, `description`, `gmt_create`, `gmt_modify`) VALUES
	(2, 'dataConversion', '测试', 'ceshi_group', 'ceshi_trigger', '0', '*/2 * * * * ?', 0, 'yes it is', '2016-02-14 15:01:54', '2016-02-27 11:32:16');
/*!40000 ALTER TABLE `schedule_job` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
