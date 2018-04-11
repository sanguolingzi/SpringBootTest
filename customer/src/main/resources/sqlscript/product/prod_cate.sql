
DROP TABLE IF EXISTS prod_category;
CREATE TABLE prod_category(
    cate_id INT AUTO_INCREMENT PRIMARY KEY,
    cate_name VARCHAR(50) NOT NULL COMMENT '分类名称',
    cate_level TINYINT(2) UNSIGNED ZEROFILL DEFAULT 1 COMMENT '分类级别，大类，小类等区分,从1开始',
    cate_url VARCHAR(200) COMMENT '分类跳转的url地址',
    cate_img_url VARCHAR(200) COMMENT '分类展示图片的url地址',
    parent_cate_id INT COMMENT '父级分类id',
    sort INT(2) COMMENT '排序',
    platform TINYINT(2) UNSIGNED ZEROFILL DEFAULT 1 COMMENT '使用的平台，1=微信/移动端，2=PC端，3=app',
    state TINYINT(2) UNSIGNED ZEROFILL DEFAULT 1 COMMENT '是否生效，是否显示，0失效，1生效，默认1生效',
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP
)ENGINE = INNODB DEFAULT CHARSET utf8 COMMENT '商品分类表';