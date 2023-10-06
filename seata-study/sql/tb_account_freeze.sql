USE seata_study;
CREATE TABLE `tb_account_freeze`(
                                     `xid` varchar(128) NOT NULL,
                                     `user_id` varchar(255) DEFAULT NULL COMMENT'用户id',
                                     `freeze_money`int(11) unsigned DEFAULT'0'COMMENT'冻结金额',
                                     `state`int(1) DEFAULT NULL COMMENT'事务状态，0:try，1:confirm，2:cancel',
                                     PRIMARY KEY(`xid`)USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;