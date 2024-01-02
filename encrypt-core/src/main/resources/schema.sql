DROP TABLE IF EXISTS bank_card;

CREATE TABLE bank_card (
id int primary key auto_increment,
gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
gmt_update timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
card_no varchar(256) NOT NULL DEFAULT '' COMMENT '卡号',
phone varchar(256) NOT NULL DEFAULT '' COMMENT '手机号',
name varchar(256) NOT NULL DEFAULT '' COMMENT '姓名',
id_no varchar(256) NOT NULL DEFAULT '' COMMENT '证件号'
);

CREATE TABLE FOO (ID INT IDENTITY, BAR VARCHAR(64));


DROP TABLE IF EXISTS AA01;

CREATE TABLE AA01 (
                           AAA000 int primary key auto_increment,
                           AAA001 int NOT NULL COMMENT 'bank_card.id',
                           AAA002 varchar(256) NOT NULL DEFAULT '' COMMENT '字段2',
                           AAA003 varchar(256) NOT NULL DEFAULT '' COMMENT '字段3',
                           AAA004 varchar(256) NOT NULL DEFAULT '' COMMENT '字段4',
                           AAA005 varchar(256) NOT NULL DEFAULT '' COMMENT '字段5',
                           AAA006 varchar(256) NOT NULL DEFAULT '' COMMENT '字段6'
);
