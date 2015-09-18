create database if not exists patient_application;

use patient_application;

drop table if exists audit_history;
drop table if exists patient;
drop table if exists users;

CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(64) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(64) DEFAULT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `is_admin` tinyint DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `audit_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `action` varchar(128) DEFAULT NULL,
  `action_date_time` timestamp,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES  users(id),
  FOREIGN KEY (patient_id) REFERENCES  patients(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users` (`id`,`last_name`,`first_name`,`email`,`password`,`is_admin`) VALUES (1,'Pederson','Evan','evan.pederson.1201@gmail.com','xGj/Xo4E07lIdGGuRoyUnXKVMYx4LjqkOCa4x+aUGwLMpzO9TNcwyAtNXSIx1Sve',1);

