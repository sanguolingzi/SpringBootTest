DROP TABLE IF EXISTS prod_cate_spec_rela;
CREATE TABLE prod_cate_spec_rela(
    rela_id INT AUTO_INCREMENT PRIMARY KEY,
    cate_id INT NOT NULL COMMENT '商品分类id',
    spec_id INT NOT NULL COMMENT '商品规格id',
    UNIQUE(cate_id, spec_id)
)ENGINE = INNODB DEFAULT CHARSET utf8 COMMENT '商品分类与规格关系表';

CREATE INDEX index_prod_cate_spec_rela_cate_id ON prod_cate_spec_rela(cate_id);
CREATE INDEX index_prod_cate_spec_rela_spec_id ON prod_cate_spec_rela(spec_id);