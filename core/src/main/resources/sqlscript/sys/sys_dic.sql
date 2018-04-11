/* 系统字典表 */
DROP TABLE IF EXISTS sys_dic;
CREATE TABLE `sys_dic` (
	`id` INT (11) AUTO_INCREMENT PRIMARY KEY,
	`dic_key` VARCHAR (24) NOT NULL COMMENT '字典键',
	`dic_value` VARCHAR (96) NOT NULL COMMENT '字典值',
	`dic_type` VARCHAR (180) NOT NULL COMMENT '字典组',
	`sort` INT (11) DEFAULT 1 COMMENT '排序编号',
	`status` INT (2) DEFAULT 1 COMMENT '是否有效，0无效，1有效，默认1有效，字典：VALID_STATUS',
	`create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`locale` VARCHAR (15) DEFAULT 'zh_CN' COMMENT '语言编码，默认zh_CN，字典LANG_LOCAL',
	UNIQUE(dic_type, dic_key)
) ENGINE = INNODB DEFAULT CHARSET utf8 COMMENT '系统字典表';

/* 系统字典表数据 */
insert  into `sys_dic`(`id`,`dic_key`,`dic_value`,`dic_type`,`sort`,`status`,`create_date`,`locale`) values (1,'1','有效','VALID_STATUS',1,1,'2018-01-30 15:08:29','zh_CN'),(2,'0','无效','VALID_STATUS',1,1,'2018-01-30 15:09:05','zh_CN'),(3,'zh_CN','中文','LANG_LOCAL',1,1,'2018-01-30 15:10:17','zh_CN');