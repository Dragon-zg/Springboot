USE test;

DROP TABLE IF EXISTS mybatis_user;
CREATE TABLE mybatis_user
(
    id          BIGINT(20) PRIMARY KEY NOT NULL COMMENT '主键ID',
    name        VARCHAR(30)            NULL     DEFAULT NULL COMMENT '姓名',
    age         INT(11)                NULL     DEFAULT NULL COMMENT '年龄',
    email       VARCHAR(50)                     DEFAULT NULL COMMENT '邮箱',
    enable      TINYINT(1)             NOT NULL DEFAULT 1 COMMENT '邮箱',
    gender      TINYINT(1)             NOT NULL COMMENT '性别 1.男性 2.女性',
    create_time DATETIME                        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME                        DEFAULT NULL COMMENT '更新时间',
    delete_flag TINYINT                NOT NULL DEFAULT 0 COMMENT '删除标识符 0.未删除 1.已删除'
);

DROP TABLE IF EXISTS mybatis_example;
CREATE TABLE mybatis_example
(
    id          BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name        VARCHAR(30)            NULL     DEFAULT NULL COMMENT '姓名',
    age         INT(11)                NULL     DEFAULT NULL COMMENT '年龄',
    email       VARCHAR(50)                     DEFAULT NULL COMMENT '邮箱',
    create_time DATETIME                        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME                        DEFAULT NULL COMMENT '更新时间',
    delete_flag TINYINT                NOT NULL DEFAULT 0 COMMENT '删除标识符 0.未删除 1.已删除'
) comment 'Generate插件例子表';