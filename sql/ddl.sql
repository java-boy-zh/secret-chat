/*
 Navicat Premium Data Transfer

 Source Server         : imchat-mysql
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : 127.0.0.1:3306
 Source Schema         : imchat

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 04/06/2024 10:23:45
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`
(
    `id`                      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
    `sender_id`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '发送者的用户id',
    `receiver_id`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '接受者的用户id',
    `receiver_type`           int                                                           DEFAULT NULL COMMENT '消息接受者的类型，可以作为扩展字段',
    `msg`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '聊天内容',
    `msg_type`                int                                                           NOT NULL COMMENT '消息类型，有文字类、图片类、视频类...等，详见枚举类',
    `chat_time`               datetime                                                      NOT NULL COMMENT '消息的聊天时间，既是发送者的发送时间、又是接受者的接受时间',
    `show_msg_date_time_flag` int                                                           DEFAULT NULL COMMENT '标记存储数据库，用于历史展示。每超过1分钟，则显示聊天时间，前端可以控制时间长短(扩展字段)',
    `video_path`              varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '视频地址',
    `video_width`             int                                                           DEFAULT NULL COMMENT '视频宽度',
    `video_height`            int                                                           DEFAULT NULL COMMENT '视频高度',
    `video_times`             int                                                           DEFAULT NULL COMMENT '视频时间',
    `voice_path`              varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '语音地址',
    `speak_voice_duration`    int                                                           DEFAULT NULL COMMENT '语音时长',
    `is_read`                 tinyint(1) DEFAULT NULL COMMENT '语音消息标记是否已读未读，true: 已读，false: 未读',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='聊天信息存储表';

-- ----------------------------
-- Records of chat_message
-- ----------------------------
BEGIN;
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575523196204220416', '1773180506223362050', '1772830312684302337', NULL, '123hello', 1, '2024-05-07 11:23:42',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575523418099679232', '1772830312684302337', '1773180506223362050', NULL, 'ABC hello jaa', 1,
        '2024-05-07 11:24:35', 1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575525039516942336', '1773180506223362050', '1772830312684302337', NULL, '11', 1, '2024-05-07 11:31:02', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575525061050499072', '1773180506223362050', '1772830312684302337', NULL, '22', 1, '2024-05-07 11:31:07', 0, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575527076908498944', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-07 11:39:07', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575527087872409600', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-07 11:39:10', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575530254634844160', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-07 11:51:45', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575530265011552256', '1772830312684302337', '1773180506223362050', NULL, '432', 1, '2024-05-07 11:51:48', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575530279557398528', '1773180506223362050', '1772830312684302337', NULL, '53425324', 1, '2024-05-07 11:51:51',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575531619524280320', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-07 11:57:10', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575531633076076544', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-07 11:57:14', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575531826072780800', '1773180506223362050', '1772830312684302337', NULL, '11', 1, '2024-05-07 11:58:00', 0, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575532069745065984', '1773180506223362050', '1772830312684302337', NULL, '111', 1, '2024-05-07 11:58:58', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575532574739267584', '1773180506223362050', '1772830312684302337', NULL, '456789', 1, '2024-05-07 12:00:58', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575532596893581312', '1773180506223362050', '1772830312684302337', NULL, '9999', 1, '2024-05-07 12:01:03', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575532619811258368', '1773180506223362050', '1772830312684302337', NULL, '456789132', 1, '2024-05-07 12:01:09',
        0, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575532694088187904', '1772830312684302337', '1773180506223362050', NULL, '456456', 1, '2024-05-07 12:01:27', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533319148535808', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-07 12:03:56', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533328023683072', '1772830312684302337', '1773180506223362050', NULL, '456', 1, '2024-05-07 12:03:58', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533364778369024', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-07 12:04:07', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533378682486784', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-07 12:04:10', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533387142397952', '1773180506223362050', '1772830312684302337', NULL, 'Abc', 1, '2024-05-07 12:04:12', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575533537206206464', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-07 12:04:48', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575855893371944960', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-08 09:25:43', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575855904583319552', '1772830312684302337', '1773180506223362050', NULL, '123', 1, '2024-05-08 09:25:46', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575855952675209216', '1773180506223362050', '1772830312684302337', NULL, '222', 1, '2024-05-08 09:25:58', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575868775610449920', '1773180506223362050', '1772830312684302337', NULL, 'Aaa', 1, '2024-05-08 10:16:55', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575868786947653632', '1772830312684302337', '1773180506223362050', NULL, 'Bbbb', 1, '2024-05-08 10:16:57', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575868799979356160', '1773180506223362050', '1772830312684302337', NULL, 'Ccc', 1, '2024-05-08 10:17:01', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575868818610454528', '1772830312684302337', '1773180506223362050', NULL, 'Ddd', 1, '2024-05-08 10:17:05', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575873798188826624', '1773180506223362050', '1772830312684302337', NULL, '[004][006]', 1,
        '2024-05-08 10:36:52', 1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575873820397666304', '1773180506223362050', '1772830312684302337', NULL, '', 3, '2024-05-08 10:36:58', 0, '',
        NULL, NULL, NULL,
        'http://127.0.0.1:8000/wechat/chat/1773180506223362050/voice/1fc76e62-fffc-45a6-9134-01c72f1a591d.mp3', 3, 1);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575873853595582464', '1773180506223362050', '1772830312684302337', NULL,
        'http://127.0.0.1:8000/wechat/chat/1773180506223362050/photo/c867d6d0-5dbb-4cf2-9fb0-53a45213c95e.PNG', 2,
        '2024-05-08 10:37:05', 0, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('575874953514057728', '1772830312684302337', '1773180506223362050', NULL, '', 3, '2024-05-08 10:41:28', 1, '',
        NULL, NULL, NULL,
        'http://127.0.0.1:8000/wechat/chat/1772830312684302337/voice/be2fc496-91fc-4f32-9c93-e7b86d26f0a9.mp3', 3, 1);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711868458565632', '1772830312684302337', '1773180506223362050', NULL, '123', 1, '2024-05-13 12:20:42', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711879204372480', '1773180506223362050', '1772830312684302337', NULL, '111', 1, '2024-05-13 12:20:45', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711907155214336', '1765267954436894722', '1772830312684302337', NULL, '111', 1, '2024-05-13 12:20:52', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711958195699712', '1772830312684302337', '1765267954436894722', NULL, '333', 1, '2024-05-13 12:21:04', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711980610060288', '1765267954436894722', '1772830312684302337', NULL, '33', 1, '2024-05-13 12:21:09', 0, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577711990508617728', '1772830312684302337', '1765267954436894722', NULL, '434', 1, '2024-05-13 12:21:11', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577712164274438144', '1772830312684302337', '1765267954436894722', NULL, '111', 1, '2024-05-13 12:21:53', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577717485063634944', '1773180506223362050', '1772830312684302337', NULL, '12', 1, '2024-05-13 12:43:01', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577717513350021120', '1772830312684302337', '1765267954436894722', NULL, '22', 1, '2024-05-13 12:43:08', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577719070653153280', '1772830312684302337', '1773180506223362050', NULL, '123', 1, '2024-05-13 12:49:19', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577719088298590208', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-13 12:49:24', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577719108246700032', '1765267954436894722', '1772830312684302337', NULL, '135', 1, '2024-05-13 12:49:28', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577728274008899584', '1773180506223362050', '1772830312684302337', NULL, '1', 1, '2024-05-13 13:25:54', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577728695981047808', '1773180506223362050', '1772830312684302337', NULL, '1', 1, '2024-05-13 13:27:34', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577770929627594752', '1773180506223362050', '1772830312684302337', NULL, '1', 1, '2024-05-13 16:15:24', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771044207591424', '1772830312684302337', '1773180506223362050', NULL, '22', 1, '2024-05-13 16:15:51', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771189968044032', '1765267954436894722', '1772830312684302337', NULL, '1', 1, '2024-05-13 16:16:26', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771200286031872', '1765267954436894722', '1772830312684302337', NULL, '22', 1, '2024-05-13 16:16:28', 0, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771214567636992', '1765267954436894722', '1772830312684302337', NULL, '33', 1, '2024-05-13 16:16:32', 0, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771289775702016', '1772830312684302337', '1772830312684302337', NULL, '321', 1, '2024-05-13 16:16:49', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771332482105344', '1772830312684302337', '1772830312684302337', NULL, '321', 1, '2024-05-13 16:17:00', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577771379483475968', '1765267954436894722', '1772830312684302337', NULL, '312', 1, '2024-05-13 16:17:11', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773253066489856', '1773180506223362050', '1772830312684302337', NULL, '1', 1, '2024-05-13 16:24:38', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773358607761408', '1772830312684302337', '1773180506223362050', NULL, '1', 1, '2024-05-13 16:25:03', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773368384684032', '1773180506223362050', '1772830312684302337', NULL, '2', 1, '2024-05-13 16:25:05', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773429269200896', '1765267954436894722', '1772830312684302337', NULL, '111', 1, '2024-05-13 16:25:20', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773505970438144', '1765267954436894722', '1772830312684302337', NULL, '333', 1, '2024-05-13 16:25:38', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773578020192256', '1772830312684302337', '1765267954436894722', NULL, '123', 1, '2024-05-13 16:25:55', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773634383249408', '1772830312684302337', '1765267954436894722', NULL, '222', 1, '2024-05-13 16:26:08', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773722568491008', '1772830312684302337', '1765267954436894722', NULL, '111', 1, '2024-05-13 16:26:29', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577773795276750848', '1773180506223362050', '1773180506223362050', NULL, '123', 1, '2024-05-13 16:26:47', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577775303414251520', '1773180506223362050', '1773180506223362050', NULL, '[006]', 1, '2024-05-13 16:32:46', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577775319881089024', '1773180506223362050', '1773180506223362050', NULL, '', 3, '2024-05-13 16:32:50', 1, '',
        NULL, NULL, NULL,
        'http://127.0.0.1:8000/wechat/chat/1773180506223362050/voice/a737c53d-1f8f-4a44-8d2a-33b3921ea8f5.mp3', 1, 1);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577775635221446656', '1773180506223362050', '1772830312684302337', NULL, '12', 1, '2024-05-13 16:34:05', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577775661179994112', '1772830312684302337', '1773180506223362050', NULL, '33', 1, '2024-05-13 16:34:12', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776402544197632', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-13 16:37:08', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776416691585024', '1772830312684302337', '1773180506223362050', NULL, '345', 1, '2024-05-13 16:37:12', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776463164473344', '1765267954436894722', '1772830312684302337', NULL, '456', 1, '2024-05-13 16:37:23', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776486765821952', '1772830312684302337', '1765267954436894722', NULL, '444', 1, '2024-05-13 16:37:29', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776498610536448', '1765267954436894722', '1772830312684302337', NULL, '5555', 1, '2024-05-13 16:37:31', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776537386876928', '1772830312684302337', '1765267954436894722', NULL, 'T54t54', 1, '2024-05-13 16:37:41', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776604416049152', '1772830312684302337', '1765267954436894722', NULL, '66', 1, '2024-05-13 16:37:57', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577776615723892736', '1765267954436894722', '1772830312684302337', NULL, '77', 1, '2024-05-13 16:37:59', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577778729581805568', '1773180506223362050', '1772830312684302337', NULL, '1231321', 1, '2024-05-13 16:46:23',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577778749139845120', '1773180506223362050', '1772830312684302337', NULL, '321321', 1, '2024-05-13 16:46:28', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577780965636571136', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-13 16:55:16', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577780986968801280', '1772830312684302337', '1773180506223362050', NULL, '32', 1, '2024-05-13 16:55:21', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781024881115136', '1765267954436894722', '1772830312684302337', NULL, '254254', 1, '2024-05-13 16:55:30', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781074390679552', '1765267954436894722', '1772830312684302337', NULL, '888', 1, '2024-05-13 16:55:42', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781728614023168', '1773180506223362050', '1773180506223362050', NULL, '111', 1, '2024-05-13 16:58:18', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781805441089536', '1773180506223362050', '1772830312684302337', NULL, '111', 1, '2024-05-13 16:58:37', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781833735864320', '1772830312684302337', '1773180506223362050', NULL, '32321', 1, '2024-05-13 16:58:43', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781869353893888', '1772830312684302337', '1773180506223362050', NULL, 'T54t54', 1, '2024-05-13 16:58:52', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781903751380992', '1765267954436894722', '1772830312684302337', NULL, '5555', 1, '2024-05-13 16:59:00', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781917772939264', '1765267954436894722', '1772830312684302337', NULL, '666', 1, '2024-05-13 16:59:03', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577781943140089856', '1765267954436894722', '1772830312684302337', NULL, 'Zzzz', 1, '2024-05-13 16:59:09', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577782008747393024', '1773180506223362050', '1772830312684302337', NULL, 'Ffewfew', 1, '2024-05-13 16:59:25',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577782779421392896', '1772830312684302337', '1773180506223362050', NULL, '123', 1, '2024-05-13 17:02:29', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577782807401594880', '1773180506223362050', '1772830312684302337', NULL, 'Ghtrgt', 1, '2024-05-13 17:02:35', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577782829753040896', '1765267954436894722', '1772830312684302337', NULL, 'Regre', 1, '2024-05-13 17:02:41', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577782861432619008', '1772830312684302337', '1765267954436894722', NULL, '5554', 1, '2024-05-13 17:02:48', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784367632351232', '1773180506223362050', '1772830312684302337', NULL, '111', 1, '2024-05-13 17:08:47', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784384619282432', '1772830312684302337', '1773180506223362050', NULL, '2312', 1, '2024-05-13 17:08:52', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784406224142336', '1765267954436894722', '1772830312684302337', NULL, '555', 1, '2024-05-13 17:08:57', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784452994826240', '1772830312684302337', '1765267954436894722', NULL, '123', 1, '2024-05-13 17:09:08', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784501598420992', '1772830312684302337', '1765267954436894722', NULL, '543', 1, '2024-05-13 17:09:19', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784569583894528', '1765267954436894722', '1772830312684302337', NULL, '432', 1, '2024-05-13 17:09:36', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784930948349952', '1772830312684302337', '1765267954436894722', NULL, '123', 1, '2024-05-13 17:11:02', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577784956227420160', '1765267954436894722', '1772830312684302337', NULL, '1111', 1, '2024-05-13 17:11:08', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577785065291907072', '1773180506223362050', '1772830312684302337', NULL, '11', 1, '2024-05-13 17:11:34', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577785876130562048', '1773180506223362050', '1772830312684302337', NULL, '1', 1, '2024-05-13 17:14:47', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577786482446565376', '1772830312684302337', '1765267954436894722', NULL, '123', 1, '2024-05-13 17:17:12', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577786514545573888', '1772830312684302337', '1765267954436894722', NULL, '111', 1, '2024-05-13 17:17:19', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577786577078452224', '1772830312684302337', '1773180506223362050', NULL, '123', 1, '2024-05-13 17:17:34', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577789651935297536', '1765267954436894722', '1772830312684302337', NULL, '123', 1, '2024-05-13 17:29:47', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577789683484852224', '1772830312684302337', '1765267954436894722', NULL, '111111333', 1, '2024-05-13 17:29:55',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577790495246254080', '1772830312684302337', '1765267954436894722', NULL, '1', 1, '2024-05-13 17:33:08', 1, '',
        NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577790590322737152', '1773180506223362050', '1773180506223362050', NULL, '123', 1, '2024-05-13 17:33:31', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577790626381168640', '1773180506223362050', '1773180506223362050', NULL, '444', 1, '2024-05-13 17:33:40', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577790682765197312', '1773180506223362050', '1772830312684302337', NULL, 'R43r43', 1, '2024-05-13 17:33:53', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577790699869569024', '1772830312684302337', '1773180506223362050', NULL, '253254', 1, '2024-05-13 17:33:57', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577791111494369280', '1772830312684302337', '1765267954436894722', NULL, '11.', 1, '2024-05-13 17:35:35', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577795393950056448', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-13 17:52:36', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577795408772726784', '1772830312684302337', '1773180506223362050', NULL, '222', 1, '2024-05-13 17:52:40', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577795448727666688', '1765267954436894722', '1772830312684302337', NULL, 'Rrr', 1, '2024-05-13 17:52:49', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('577795468998737920', '1772830312684302337', '1765267954436894722', NULL, 'Ttt', 1, '2024-05-13 17:52:54', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('578387925817884672', '1773180506223362050', '1772830312684302337', NULL, '111', 1, '2024-05-15 09:07:07', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('578387934143578112', '1772830312684302337', '1773180506223362050', NULL, '222', 1, '2024-05-15 09:07:09', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580230428984606720', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-20 11:08:34', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580230581544026112', '1772830312684302337', '1773180506223362050', NULL, '456', 1, '2024-05-20 11:09:10', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580231073389084672', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-20 11:11:08', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580231189005074432', '1772830312684302337', '1773180506223362050', NULL, '456', 1, '2024-05-20 11:11:35', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580231257514835968', '1765267954436894722', '1772830312684302337', NULL, '789', 1, '2024-05-20 11:11:51', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580232315888730112', '1773180506223362050', '1772830312684302337', NULL, '123', 1, '2024-05-20 11:16:04', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580232376081186816', '1772830312684302337', '1773180506223362050', NULL, '456', 1, '2024-05-20 11:16:18', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580232435174735872', '1765267954436894722', '1772830312684302337', NULL, '999', 1, '2024-05-20 11:16:32', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580236116121092096', '1773180506223362050', '1772830312684302337', NULL, '456', 1, '2024-05-20 11:31:10', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580236143765749760', '1773180506223362050', '1772830312684302337', NULL, 'Hello', 1, '2024-05-20 11:31:16', 0,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580236188183429120', '1765267954436894722', '1772830312684302337', NULL, '111', 1, '2024-05-20 11:31:27', 1,
        '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `chat_message` (`id`, `sender_id`, `receiver_id`, `receiver_type`, `msg`, `msg_type`, `chat_time`,
                            `show_msg_date_time_flag`, `video_path`, `video_width`, `video_height`, `video_times`,
                            `voice_path`, `speak_voice_duration`, `is_read`)
VALUES ('580236214934700032', '1772830312684302337', '1765267954436894722', NULL, '324rt432r', 1, '2024-05-20 11:31:33',
        1, '', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`               varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
    `belong_user_id`   varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '评论的朋友圈是哪个用户的关联id',
    `father_id`        varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '如果是回复留言，则本条为子留言，需要关联查询',
    `friend_circle_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '评论的那个朋友圈的主键id',
    `comment_user_id`  varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '发布留言的用户id',
    `comment_content`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '留言内容',
    `created_time`     datetime                                                      NOT NULL COMMENT '留言时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `belong_user_id`, `father_id`, `friend_circle_id`, `comment_user_id`, `comment_content`,
                       `created_time`)
VALUES ('1780791009959424001', '1772830312684302337', '', '1780065811064832002', '1772830312684302337', 'Test hahhahah',
        '2024-04-18 10:50:37');
INSERT INTO `comment` (`id`, `belong_user_id`, `father_id`, `friend_circle_id`, `comment_user_id`, `comment_content`,
                       `created_time`)
VALUES ('1780806229956186114', '1773180506223362050', '', '1780429972793888769', '1772830312684302337', '1234546',
        '2024-04-18 11:51:06');
COMMIT;

-- ----------------------------
-- Table structure for friend_circle
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle`;
CREATE TABLE `friend_circle`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `user_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '哪个用户发的朋友圈，用户id',
    `words`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '文字内容',
    `images`       varchar(2560) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片内容，url用逗号分割',
    `video`        varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '单个视频的url',
    `publish_time` datetime                                                     NOT NULL COMMENT '发布朋友圈的时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='朋友圈表';

-- ----------------------------
-- Records of friend_circle
-- ----------------------------
BEGIN;
INSERT INTO `friend_circle` (`id`, `user_id`, `words`, `images`, `video`, `publish_time`)
VALUES ('1780817381322899458', '1772830312684302337', NULL,
        ',http://127.0.0.1:8000/wechat/friendCircleImage/1772830312684302337/7f5d5893-ebff-4e21-8dc8-737ecc23461d.PNG,http://127.0.0.1:8000/wechat/friendCircleImage/1772830312684302337/b38bc252-2639-4824-8524-ec5eacae5573.PNG,http://127.0.0.1:8000/wechat/friendCircleImage/1772830312684302337/cce73860-c5ff-4593-a17a-affacb51aed6.JPG,',
        NULL, '2024-04-18 12:35:24');
COMMIT;

-- ----------------------------
-- Table structure for friend_circle_liked
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_liked`;
CREATE TABLE `friend_circle_liked`
(
    `id`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `belong_user_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '朋友圈归属用户的id',
    `friend_circle_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '点赞的那个朋友圈id',
    `liked_user_id`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '点赞的那个用户id',
    `liked_user_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '点赞用户的昵称',
    `created_time`     datetime                                                     NOT NULL COMMENT '点赞时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点赞朋友圈的朋友';

-- ----------------------------
-- Records of friend_circle_liked
-- ----------------------------
BEGIN;
INSERT INTO `friend_circle_liked` (`id`, `belong_user_id`, `friend_circle_id`, `liked_user_id`, `liked_user_name`,
                                   `created_time`)
VALUES ('1780782051794288642', '1773180506223362050', '1780429972793888769', '1773180506223362050', 'HellWorld',
        '2024-04-18 10:15:01');
INSERT INTO `friend_circle_liked` (`id`, `belong_user_id`, `friend_circle_id`, `liked_user_id`, `liked_user_name`,
                                   `created_time`)
VALUES ('1780782065274781697', '1772830312684302337', '1780065811064832002', '1773180506223362050', 'HellWorld',
        '2024-04-18 10:15:04');
INSERT INTO `friend_circle_liked` (`id`, `belong_user_id`, `friend_circle_id`, `liked_user_id`, `liked_user_name`,
                                   `created_time`)
VALUES ('1780782124674514946', '1772830312684302337', '1780057828784320513', '1772830312684302337', 'Helloimooc4',
        '2024-04-18 10:15:19');
COMMIT;

-- ----------------------------
-- Table structure for friend_request
-- ----------------------------
DROP TABLE IF EXISTS `friend_request`;
CREATE TABLE `friend_request`
(
    `id`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `my_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '添加好友，发起请求的用户id',
    `friend_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '要添加的朋友的id',
    `friend_remark`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '好友的备注名',
    `verify_message` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求的留言，验证消息',
    `verify_status`  int                                                          NOT NULL COMMENT '请求被好友审核的状态，0-待审核；1-已添加，2-已过期',
    `request_time`   datetime                                                     NOT NULL COMMENT '发起请求的时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='好友请求记录表';

-- ----------------------------
-- Records of friend_request
-- ----------------------------
BEGIN;
INSERT INTO `friend_request` (`id`, `my_id`, `friend_id`, `friend_remark`, `verify_message`, `verify_status`,
                              `request_time`)
VALUES ('1778614709408', '1772830312684302337', '1773180506223362050', 'Cat', '123', 1, '2024-04-12 10:42:46');
INSERT INTO `friend_request` (`id`, `my_id`, `friend_id`, `friend_remark`, `verify_message`, `verify_status`,
                              `request_time`)
VALUES ('1789151391056801794', '1765267954436894722', '1772830312684302337', '', '123', 1, '2024-05-11 12:31:47');
COMMIT;

-- ----------------------------
-- Table structure for friendship
-- ----------------------------
DROP TABLE IF EXISTS `friendship`;
CREATE TABLE `friendship`
(
    `id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `my_id`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '自己的用户id',
    `friend_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '我朋友的id',
    `friend_remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '好友的备注名',
    `chat_bg`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '聊天背景，局部',
    `is_msg_ignore` int                                                          NOT NULL COMMENT '是否消息免打扰，0-打扰，不忽略消息(默认)；1-免打扰，忽略消息',
    `is_black`      int                                                          NOT NULL COMMENT '是否拉黑，0-好友(默认)；1-已拉黑',
    `created_time`  datetime                                                     NOT NULL COMMENT '创建时间',
    `updated_time`  datetime                                                     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `my_id` (`my_id`,`friend_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='朋友关系表';

-- ----------------------------
-- Records of friendship
-- ----------------------------
BEGIN;
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1778628006862', '1765564831011119106', '1772830312684302337', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('17786280068623', '1765570891608432642', '1772830312684302337', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('177862800686238', '1765559357532491777', '1772830312684302337', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1778628006862381', '1765555502174392321', '1772830312684302337', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('17786280068623810', '1765275752755318785', '1772830312684302337', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1778628006862381057', '1773180506223362050', '1772830312684302337', 'little dog', NULL, 0, 0,
        '2024-04-12 11:35:37', '2024-04-27 14:57:56');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('177862800689', '1772830312684302337', '1765564831011119106', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('17786280068917', '1772830312684302337', '1765570891608432642', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('177862800689174', '1772830312684302337', '1765559357532491777', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1778628006891741', '1772830312684302337', '1765555502174392321', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-12 11:35:37');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('17786280068917411', '1772830312684302337', '1765275752755318785', '', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-15 11:59:57');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1778628006891741186', '1772830312684302337', '1773180506223362050', 'Cat', NULL, 0, 0, '2024-04-12 11:35:37',
        '2024-04-15 12:00:09');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1789151426150543362', '1772830312684302337', '1765267954436894722', 'Aaa', NULL, 0, 0, '2024-05-11 12:31:55',
        '2024-05-11 12:31:55');
INSERT INTO `friendship` (`id`, `my_id`, `friend_id`, `friend_remark`, `chat_bg`, `is_msg_ignore`, `is_black`,
                          `created_time`, `updated_time`)
VALUES ('1789151426163126274', '1765267954436894722', '1772830312684302337', '', NULL, 0, 0, '2024-05-11 12:31:55',
        '2024-05-11 12:31:55');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
    `wechat_num`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '微信号',
    `wechat_num_img`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '微信号二维码',
    `mobile`           varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '手机号',
    `nickname`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '昵称',
    `real_name`        varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '真实姓名',
    `sex`              int                                                           NOT NULL COMMENT '性别，1:男 0:女 2:保密',
    `face`             varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像',
    `email`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
    `birthday`         date                                                          DEFAULT NULL COMMENT '生日',
    `country`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '国家',
    `province`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '省份',
    `city`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '城市',
    `district`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '区县',
    `chat_bg`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '聊天背景',
    `friend_circle_bg` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '朋友圈背景图',
    `signature`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '我的一句话签名',
    `created_time`     datetime                                                      NOT NULL COMMENT '创建时间',
    `updated_time`     datetime                                                      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `mobile` (`mobile`) USING BTREE,
    UNIQUE KEY `wechat_num` (`wechat_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1753640850245595131', 'Abc123',
        'http://127.0.0.1:9000/itzixi/wechatNumber/ec100ac4-498e-45d4-b5fe-875bbc1c0a96.png', '13966771234',
        '用户13******234', '用户13******234', 2,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', '',
        '1980-01-01', '中国', '辽宁省', '丹东市', '', NULL, NULL, NULL, '2024-02-03 12:45:34', '2024-02-03 13:03:07');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1754350560036847617', 'wxfb57f6d9c4fa',
        'http://127.0.0.1:9000/itzixi/wechatNumber/936d9600-83be-416c-a4ee-65a981067fb8.png', '13966778811',
        '用户13******811', '用户13******811', 2,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-02-05 11:45:42', '2024-02-05 11:45:42');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765267954436894722', '雷神',
        'http://127.0.0.1:9000/itzixi/wechatNumber/d5d6ab81-b02f-46d6-8445-b9c054bd9317.png', '13912340011', '雷神',
        '用户13******001', 2,
        'http://127.0.0.1:9000/itzixi/face/1765267954436894722/9718a00d-c6f1-4a86-ba64-d5077345f465.jpg', '',
        '1980-01-01', '中国', '', '', '',
        'http://127.0.0.1:9000/itzixi/chatBg/1765267954436894722/f6db1591-a1ba-445a-a28f-868cb79c5449.JPG',
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-06 14:47:32', '2024-03-07 09:48:47');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765275752755318785', '美国队长',
        'http://127.0.0.1:9000/itzixi/wechatNumber/1fdac319-46a7-4956-9991-244dc60d5e41.png', '13912340002', '美国队长',
        '用户13******002', 2,
        'http://127.0.0.1:9000/itzixi/face/1765275752755318785/e0053b19-56be-4eee-919d-e5a2698e9545.jpg', '',
        '1980-01-01', '中国', '', '', '',
        'http://127.0.0.1:9000/itzixi/chatBg/1765275752755318785/c0ed23af-ad1b-43fa-8d7d-ae621fb83c6a.PNG',
        'http://127.0.0.1:9000/itzixi/friendCircleBg/1765275752755318785/5ee8ed6e-a2ef-4b1e-af15-3b2fcbe3fcc3.jpg',
        NULL, '2024-03-06 15:18:31', '2024-03-08 11:21:42');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765555502174392321', '钢铁侠',
        'http://127.0.0.1:9000/itzixi/wechatNumber/08574f9a-e36d-4f09-ae39-de4688aa1a26.png', '13912340003', '钢铁侠',
        '用户13******003', 2,
        'http://127.0.0.1:9000/itzixi/face/1765555502174392321/02ecd486-ff61-4c78-b8e9-f26a005acfff.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 09:50:09', '2024-03-07 10:01:46');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765559357532491777', '蜘蛛侠',
        'http://127.0.0.1:9000/itzixi/wechatNumber/897ba253-079c-42ca-bcaf-f2c4aa8fc3b7.png', '13912340004', '蜘蛛侠',
        '用户13******004', 2,
        'http://127.0.0.1:9000/itzixi/face/1765559357532491777/18977eeb-8880-4f09-a598-d7b4eb2dba62.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 10:05:28', '2024-03-07 10:06:11');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765564831011119106', ':-)汤姆',
        'http://127.0.0.1:9000/itzixi/wechatNumber/6888beb9-dcda-4bfa-a331-657ff304bc25.png', '13912340005', ':-)汤姆',
        '用户13******005', 2,
        'http://127.0.0.1:9000/itzixi/face/1765564831011119106/f141f51f-aec5-47e6-ad2f-353044542ea4.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 10:27:13', '2024-03-07 10:27:46');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765570891608432642', '😄笑脸',
        'http://127.0.0.1:9000/itzixi/wechatNumber/3574d776-f8ee-44b9-8cd2-6a34bf3e9b4d.png', '13912340006', '123笑脸',
        '用户13******006', 2,
        'http://127.0.0.1:9000/itzixi/face/1765570891608432642/c6b718a5-31cb-4327-bda2-c7e493319c68.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 10:51:18', '2024-03-07 10:52:11');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765573168645742593', 'Indira',
        'http://127.0.0.1:9000/itzixi/wechatNumber/c067c872-4703-486e-8771-bf80c4f0ea3c.png', '13912340007', 'Indira',
        '用户13******007', 2,
        'http://127.0.0.1:9000/itzixi/face/1765573168645742593/fc97df46-32a6-422b-bd82-e5df3478a146.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 11:00:21', '2024-03-07 11:07:39');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765580733966393346', 'Cyan',
        'http://127.0.0.1:9000/itzixi/wechatNumber/b0596b03-7098-48ed-905c-06434cc4b12f.png', '13912340008', 'Cyan',
        '用户13******008', 2,
        'http://127.0.0.1:9000/itzixi/face/1765580733966393346/4de75943-2a7c-4ecc-82c0-47033cc16317.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 11:30:25', '2024-03-07 11:31:00');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765583805862060034', 'BlackCat',
        'http://127.0.0.1:9000/itzixi/wechatNumber/06f30163-bdd0-4942-80fa-1a6be79ca8da.png', '13912340009', 'BlackCat',
        '用户13******009', 2,
        'http://127.0.0.1:9000/itzixi/face/1765583805862060034/cd73e147-007d-4427-a6c9-3f4d24a7fc88.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 11:42:37', '2024-03-07 11:45:45');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765589375310569473', 'Aida',
        'http://127.0.0.1:9000/itzixi/wechatNumber/b343b4ac-a003-4f8d-b87a-3a1510d8fe70.png', '13912340010', 'Aida',
        '用户13******010', 2,
        'http://127.0.0.1:9000/itzixi/face/1765589375310569473/4f27f294-dc3c-4ff8-ba93-d619073c0b0c.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 12:04:45', '2024-03-07 12:05:16');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765606885284061186', 'Nana',
        'http://127.0.0.1:9000/itzixi/wechatNumber/5c1b491a-92b8-456b-b3ca-db8e970c861c.png', '13912340111', 'Nana',
        '用户13******011', 2,
        'http://127.0.0.1:9000/itzixi/face/1765606885284061186/16e7775f-d04c-4f42-b45a-573bdeae0c05.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-07 13:14:19', '2024-03-07 13:15:56');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1765607922887761921', 'Kira',
        'http://127.0.0.1:9000/itzixi/wechatNumber/a9592807-8fbd-4439-8227-14232e05bc0d.png', '13912340112', 'Kira',
        '用户13******012', 2,
        'http://127.0.0.1:9000/itzixi/face/1765607922887761921/9dcd7835-c0ff-40cb-9d54-084f0878e95c.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/friendCircleBg/1765607922887761921/2f8fda37-2c2a-475c-aad1-28330841811e.jpg',
        NULL, '2024-03-07 13:18:27', '2024-03-08 11:19:57');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1772830312684302337', 'wxImoocHello',
        'http://127.0.0.1:8000/wechat/wechatNumber/1772830312684302337/42ab4802-34be-4a48-8441-1dccfd83b13c.png',
        '13912340000', 'Helloimooc6', '', 0, 'http://127.0.0.1:8000/wechat/face/1772830312684302337/1712463298.jpg', '',
        '1980-01-01', '中国', '北京市', '东城区', '', NULL,
        'http://127.0.0.1:8000/wechat/chatBg/1772830312684302337/b3f6b30f-2f8d-41a4-b3d4-30eb277eb930.PNG',
        'Hello beijing', '2024-03-27 11:37:39', '2024-04-25 10:05:05');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1772835834690981890', 'wx6f1ca5d06b5f',
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', '13912340001',
        '用户13******001', '', 2,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-03-27 11:59:35', '2024-03-27 11:59:35');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1773180506223362050', 'wx123456',
        'http://127.0.0.1:8000/wechat/wechatNumber/1773180506223362050/0259f1df-544d-4c46-abf2-ba335e8fc46d.png',
        '13912345678', 'HellWorld', '', 2, 'http://127.0.0.1:8000/wechat/face/1773180506223362050/1712801524.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:8000/wechat/friendCircleBg/1773180506223362050/17883ed1-5374-43b3-bdfa-37e6672af071.jpg',
        NULL, '2024-03-28 10:49:11', '2024-04-17 10:55:44');
INSERT INTO `users` (`id`, `wechat_num`, `wechat_num_img`, `mobile`, `nickname`, `real_name`, `sex`, `face`, `email`,
                     `birthday`, `country`, `province`, `city`, `district`, `chat_bg`, `friend_circle_bg`, `signature`,
                     `created_time`, `updated_time`)
VALUES ('1777172754837516290', 'wxedbe828a76f6',
        'http://127.0.0.1:8000/wechat/wechatNumber/temp/dfecbf49-f510-4783-8983-ac85cd086402.png', '13999998888',
        '用户13******888', '', 2,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', '',
        '1980-01-01', '中国', '', '', '', NULL,
        'http://127.0.0.1:9000/itzixi/face/1749619640390205441/e9f2be46-56ba-454c-a7ee-3e290bad6a59.jpg', NULL,
        '2024-04-08 11:12:58', '2024-04-08 11:12:58');
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;

-- 敏感词表
CREATE TABLE `sensitive_words`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `words`      varchar(1024) DEFAULT NULL COMMENT '内容',
    `type`       int(11) DEFAULT '0' COMMENT '1=黑名单 2=白名单',
    `is_deleted` int(11) DEFAULT NULL COMMENT '是否被删除 0为删除 1已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词表';
