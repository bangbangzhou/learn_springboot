-- 创建测试库
create database  if not exists springboot_mp character set utf8;
use springboot_mp;
-- 创建测试表
CREATE TABLE tb_student (
                            id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                            stuid varchar(40) unique NOT NULL comment '学号',
                            name varchar(30) DEFAULT NULL COMMENT '姓名',
                            age tinyint   COMMENT '年龄',
                            sex tinyint(1) comment '性别 0 男 1 女',
                            dept varchar(2000) DEFAULT NULL COMMENT '院系',
                            address varchar(400) comment '家庭地址',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 插入测试数据
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528247996417, '6840300', '张三', 20, 0, '计算机学院', '广东省广州市天河街100号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854017, '6840000', '李四', 24, 0, '计算机学院', '广东省珠海市香洲路100号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854018, '6840500', '王五', 25, 0, '物理学院', '广东省广州市番禺100号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854019, '6840600', '邹六', 26, 0, '艺术学院', '河南省郑州市100号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854020, '6840700', '韩梅梅', 21, 1, '英语学院', '广东省广州市天河街150号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854021, '6840800', '小芳', 20, 1, '英语学院', '河南省郑州市140号');
INSERT INTO tb_student (id, stuid, name, age, sex, dept, address) VALUES (1692936528352854022, '6840900', '小花', 20, 1, '计算机学院', '广东省深圳市100号');
