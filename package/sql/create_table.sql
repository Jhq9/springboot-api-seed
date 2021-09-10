/*
 Navicat Premium Data Transfer

 Source Server         : wsl
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : operation_platform_v2

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 19/08/2021 09:34:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS springboot_api_seed CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE springboot_api_seed;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          bigint UNSIGNED                                        NOT NULL AUTO_INCREMENT,
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '角色名称',
    `remark`      varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `create_time` timestamp                                              NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp                                              NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint(1)                                             NULL DEFAULT NULL COMMENT '是否已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`              bigint                                                 NOT NULL AUTO_INCREMENT,
    `username`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '用户账号',
    `password`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号密码',
    `mobile`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '手机号',
    `email`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '邮箱',
    `status`          tinyint(1)                                             NOT NULL COMMENT '账号状态:0-锁定;1-有效;9删除',
    `last_login_time` timestamp                                              NULL DEFAULT NULL COMMENT '上次登录时间',
    `create_time`     timestamp                                              NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     timestamp                                              NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`         tinyint(1)                                             NULL DEFAULT NULL COMMENT '是否已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    UNIQUE INDEX `uq_idx_uid_rid` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_BLOB_TRIGGERS`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_NAME`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `BLOB_DATA`     blob                                             NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE,
    INDEX `SCHED_NAME`
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            )
        USING BTREE,
    CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) REFERENCES `QRTZ_TRIGGERS`
            (
             `SCHED_NAME`,
             `TRIGGER_NAME`,
             `TRIGGER_GROUP`
                )
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_CALENDARS`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `CALENDAR`      blob                                             NOT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `CALENDAR_NAME`
            ) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_CRON_TRIGGERS`
(
    `SCHED_NAME`      varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_NAME`    varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP`   varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TIME_ZONE_ID`    varchar(80) CHARACTER SET utf8 COLLATE utf8_bin  NULL DEFAULT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE,
    CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) REFERENCES `QRTZ_TRIGGERS`
            (
             `SCHED_NAME`,
             `TRIGGER_NAME`,
             `TRIGGER_GROUP`
                )
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_FIRED_TRIGGERS`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `ENTRY_ID`          varchar(95) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL,
    `TRIGGER_NAME`      varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP`     varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `INSTANCE_NAME`     varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `FIRED_TIME`        bigint                                           NOT NULL,
    `SCHED_TIME`        bigint                                           NOT NULL,
    `PRIORITY`          int                                              NOT NULL,
    `STATE`             varchar(16) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL,
    `JOB_NAME`          varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `JOB_GROUP`         varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `IS_NONCONCURRENT`  varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NULL DEFAULT NULL,
    `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NULL DEFAULT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `ENTRY_ID`
            ) USING BTREE,
    INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`
        (
         `SCHED_NAME`,
         `INSTANCE_NAME`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`
        (
         `SCHED_NAME`,
         `INSTANCE_NAME`,
         `REQUESTS_RECOVERY`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_FT_J_G`
        (
         `SCHED_NAME`,
         `JOB_NAME`,
         `JOB_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_FT_JG`
        (
         `SCHED_NAME`,
         `JOB_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_FT_T_G`
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_FT_TG`
        (
         `SCHED_NAME`,
         `TRIGGER_GROUP`
            )
        USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_JOB_DETAILS`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `JOB_NAME`          varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `JOB_GROUP`         varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `DESCRIPTION`       varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `JOB_CLASS_NAME`    varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `IS_DURABLE`        varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NOT NULL,
    `IS_NONCONCURRENT`  varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NOT NULL,
    `IS_UPDATE_DATA`    varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NOT NULL,
    `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NOT NULL,
    `JOB_DATA`          blob                                             NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `JOB_NAME`,
         `JOB_GROUP`
            ) USING BTREE,
    INDEX `IDX_QRTZ_J_REQ_RECOVERY`
        (
         `SCHED_NAME`,
         `REQUESTS_RECOVERY`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_J_GRP`
        (
         `SCHED_NAME`,
         `JOB_GROUP`
            )
        USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_LOCKS`
(
    `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `LOCK_NAME`  varchar(40) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `LOCK_NAME`
            ) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_SCHEDULER_STATE`
(
    `SCHED_NAME`        varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `INSTANCE_NAME`     varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `LAST_CHECKIN_TIME` bigint                                           NOT NULL,
    `CHECKIN_INTERVAL`  bigint                                           NOT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `INSTANCE_NAME`
            ) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPLE_TRIGGERS`
(
    `SCHED_NAME`      varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_NAME`    varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP`   varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `REPEAT_COUNT`    bigint                                           NOT NULL,
    `REPEAT_INTERVAL` bigint                                           NOT NULL,
    `TIMES_TRIGGERED` bigint                                           NOT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE,
    CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) REFERENCES `QRTZ_TRIGGERS`
            (
             `SCHED_NAME`,
             `TRIGGER_NAME`,
             `TRIGGER_GROUP`
                )
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPROP_TRIGGERS`
(
    `SCHED_NAME`    varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_NAME`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `STR_PROP_1`    varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `STR_PROP_2`    varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `STR_PROP_3`    varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `INT_PROP_1`    int                                              NULL DEFAULT NULL,
    `INT_PROP_2`    int                                              NULL DEFAULT NULL,
    `LONG_PROP_1`   bigint                                           NULL DEFAULT NULL,
    `LONG_PROP_2`   bigint                                           NULL DEFAULT NULL,
    `DEC_PROP_1`    decimal(13,
                        4)                                           NULL DEFAULT NULL,
    `DEC_PROP_2`    decimal(13,
                        4)                                           NULL DEFAULT NULL,
    `BOOL_PROP_1`   varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NULL DEFAULT NULL,
    `BOOL_PROP_2`   varchar(1) CHARACTER SET utf8 COLLATE utf8_bin   NULL DEFAULT NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE,
    CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) REFERENCES `QRTZ_TRIGGERS`
            (
             `SCHED_NAME`,
             `TRIGGER_NAME`,
             `TRIGGER_GROUP`
                )
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `QRTZ_TRIGGERS`
(
    `SCHED_NAME`     varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_NAME`   varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `TRIGGER_GROUP`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `JOB_NAME`       varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `JOB_GROUP`      varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `DESCRIPTION`    varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `NEXT_FIRE_TIME` bigint                                           NULL DEFAULT NULL,
    `PREV_FIRE_TIME` bigint                                           NULL DEFAULT NULL,
    `PRIORITY`       int                                              NULL DEFAULT NULL,
    `TRIGGER_STATE`  varchar(16) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL,
    `TRIGGER_TYPE`   varchar(8) CHARACTER SET utf8 COLLATE utf8_bin   NOT NULL,
    `START_TIME`     bigint                                           NOT NULL,
    `END_TIME`       bigint                                           NULL DEFAULT NULL,
    `CALENDAR_NAME`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `MISFIRE_INSTR`  smallint                                         NULL DEFAULT NULL,
    `JOB_DATA`       blob                                             NULL,
    PRIMARY KEY
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`
            ) USING BTREE,
    INDEX `IDX_QRTZ_T_J`
        (
         `SCHED_NAME`,
         `JOB_NAME`,
         `JOB_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_JG`
        (
         `SCHED_NAME`,
         `JOB_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_C`
        (
         `SCHED_NAME`,
         `CALENDAR_NAME`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_G`
        (
         `SCHED_NAME`,
         `TRIGGER_GROUP`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_STATE`
        (
         `SCHED_NAME`,
         `TRIGGER_STATE`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_N_STATE`
        (
         `SCHED_NAME`,
         `TRIGGER_NAME`,
         `TRIGGER_GROUP`,
         `TRIGGER_STATE`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_N_G_STATE`
        (
         `SCHED_NAME`,
         `TRIGGER_GROUP`,
         `TRIGGER_STATE`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`
        (
         `SCHED_NAME`,
         `NEXT_FIRE_TIME`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_NFT_ST`
        (
         `SCHED_NAME`,
         `TRIGGER_STATE`,
         `NEXT_FIRE_TIME`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_NFT_MISFIRE`
        (
         `SCHED_NAME`,
         `MISFIRE_INSTR`,
         `NEXT_FIRE_TIME`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`
        (
         `SCHED_NAME`,
         `MISFIRE_INSTR`,
         `NEXT_FIRE_TIME`,
         `TRIGGER_STATE`
            )
        USING BTREE,
    INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`
        (
         `SCHED_NAME`,
         `MISFIRE_INSTR`,
         `NEXT_FIRE_TIME`,
         `TRIGGER_GROUP`,
         `TRIGGER_STATE`
            )
        USING BTREE,
    CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY
        (
         `SCHED_NAME`,
         `JOB_NAME`,
         `JOB_GROUP`
            ) REFERENCES `QRTZ_JOB_DETAILS`
            (
             `SCHED_NAME`,
             `JOB_NAME`,
             `JOB_GROUP`
                )
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = DYNAMIC;

GRANT ALL ON springboot_api_seed.* to sas_test@'%' identified by 'sas_123456';
GRANT ALL ON springboot_api_seed.* to sas_test@'localhost' identified by 'sas_123456';

SET FOREIGN_KEY_CHECKS = 1;
