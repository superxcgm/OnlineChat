set names utf8; 
CREATE DATABASE onlinechat;
use onlinechat;

CREATE TABLE  `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_nick` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;

CREATE TABLE  `friends` (
  `user_id_1` int(11) NOT NULL,
  `user_id_2` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `chat_room`(
  `cr_id` int(11) NOT NULL AUTO_INCREMENT,
  `cr_name` varchar(100) NOT NULL,
  PRIMARY KEY (`cr_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

CREATE TABLE  `files` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(100) NOT NULL,
  `f_passwd` varchar(100) DEFAULT NULL,
  `f_owner` int(11) NOT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=MyISAM AUTO_INCREMENT=500 DEFAULT CHARSET=utf8;

CREATE TABLE  `msg_records` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_user_id_send` int(11) NOT NULL,
  `msg_user_id_recv` int(11) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `msg_context` varchar(255) NOT NULL,
  `msg_time` timestamp NOT NULL,
  PRIMARY KEY(`msg_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;