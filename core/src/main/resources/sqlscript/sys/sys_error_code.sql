-- 系统错误信息映射表
DROP TABLE IF EXISTS sys_error_code;
CREATE TABLE `sys_error_code` (
	`error_code` VARCHAR (10) NOT NULL COMMENT '错误编码',
	`error_message` VARCHAR (256) COMMENT '错误消息',
	`error_type` VARCHAR (32) NOT NULL COMMENT '消息类型，字典：ERROR_CODE_TYPE',
	`busi_code` VARCHAR (32) COMMENT '业务编码，相关业务',
	`sort_number` INT (11) COMMENT '排序编号',
	`status` TINYINT (2) DEFAULT 1 COMMENT '是否有效，字典VALID_STATUS',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`remarks` VARCHAR (600) COMMENT '备注',
	UNIQUE(error_code)
)ENGINE = INNODB DEFAULT CHARSET utf8 COMMENT '系统错误信息映射';

-- 表数据
insert  into `sys_error_code`(`error_code`,`error_message`,`error_type`,`busi_code`,`sort_number`,`status`,`create_time`,`remarks`) values ('BC0001','用户名不能为空','MESSAGE','LOGIN',NULL,1,'2018-01-30 15:59:01',NULL),('BC0002','密码不能为空','MESSAGE','LOGIN',NULL,1,'2018-01-30 15:59:28',NULL),('EX0001','用户信息不存在','EXCEPTION','LOGIN',NULL,1,'2018-01-30 16:00:37',NULL);