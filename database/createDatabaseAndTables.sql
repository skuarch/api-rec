/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.6.22-log : Database - regaloenclave
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`regaloenclave` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `regaloenclave`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_all` longtext NOT NULL,
  `address_city` varchar(255) DEFAULT NULL,
  `address_country` varchar(255) NOT NULL,
  `address_is_soft_deleted` int(11) DEFAULT '0',
  `address_registration_date` varchar(19) NOT NULL,
  `address_state` varchar(255) NOT NULL,
  `address_zip_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `administrator_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `administrator_active` int(11) DEFAULT '0',
  `administrator_is_soft_deleted` int(11) DEFAULT '0',
  `administrator_last_login` varchar(255) DEFAULT NULL,
  `administrator_password` varchar(32) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`administrator_id`),
  KEY `FK_m5fkk2o3d0dkrqvdgxryu3h3f` (`person_id`),
  CONSTRAINT `FK_m5fkk2o3d0dkrqvdgxryu3h3f` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `affiliate` */

DROP TABLE IF EXISTS `affiliate`;

CREATE TABLE `affiliate` (
  `affiliate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `affiliate_active` int(11) DEFAULT '0',
  `affiliate_bank` varchar(255) NOT NULL,
  `affiliate_brand` varchar(255) NOT NULL,
  `affiliate_clabe` varchar(255) NOT NULL,
  `creator_person_id` bigint(20) DEFAULT NULL,
  `affiliate_description` longtext NOT NULL,
  `affiliate_email_notifications` varchar(255) NOT NULL,
  `affiliate_is_soft_deleted` int(11) DEFAULT '0',
  `affiliate_last_login` varchar(255) DEFAULT NULL,
  `affiliate_logo_path_name` varchar(255) DEFAULT NULL,
  `affiliate_owner_account_bank` varchar(255) NOT NULL,
  `affiliate_password` varchar(32) NOT NULL,
  `affiliate_registration_date` varchar(19) NOT NULL,
  `affiliate_tax_company_name` varchar(255) NOT NULL,
  `affiliate_tax_id` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  `contact_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`affiliate_id`),
  KEY `FK_e9kw7qq9s07yhbdto2al5o1ov` (`address_id`),
  KEY `FK_bqtbaw7tta958a27qbxqgma5y` (`contact_id`),
  KEY `FK_d80kivo3i0ylh2ydpt9938kw1` (`person_id`),
  CONSTRAINT `FK_bqtbaw7tta958a27qbxqgma5y` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`),
  CONSTRAINT `FK_d80kivo3i0ylh2ydpt9938kw1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `FK_e9kw7qq9s07yhbdto2al5o1ov` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `affiliate_category` */

DROP TABLE IF EXISTS `affiliate_category`;

CREATE TABLE `affiliate_category` (
  `affiliate_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `UK_so8jwpxf6uf1vhxi0esjptocs` (`category_id`),
  KEY `FK_l4gllx6ierqbkrxv0xh6o6bm1` (`affiliate_id`),
  CONSTRAINT `FK_l4gllx6ierqbkrxv0xh6o6bm1` FOREIGN KEY (`affiliate_id`) REFERENCES `affiliate` (`affiliate_id`),
  CONSTRAINT `FK_so8jwpxf6uf1vhxi0esjptocs` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `affiliate_establishment` */

DROP TABLE IF EXISTS `affiliate_establishment`;

CREATE TABLE `affiliate_establishment` (
  `establishment_id` bigint(20) NOT NULL,
  `affiliate_id` bigint(20) NOT NULL,
  PRIMARY KEY (`establishment_id`,`affiliate_id`),
  KEY `UK_r72ghcevu3xuooeq1ee51gak1` (`establishment_id`),
  KEY `FK_3ur9xjpwu02ohsn69gn2uawm0` (`affiliate_id`),
  CONSTRAINT `FK_3ur9xjpwu02ohsn69gn2uawm0` FOREIGN KEY (`affiliate_id`) REFERENCES `affiliate` (`affiliate_id`),
  CONSTRAINT `FK_r72ghcevu3xuooeq1ee51gak1` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `affiliate_type` */

DROP TABLE IF EXISTS `affiliate_type`;

CREATE TABLE `affiliate_type` (
  `affiliate_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `affiliate_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`affiliate_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cashier` */

DROP TABLE IF EXISTS `cashier`;

CREATE TABLE `cashier` (
  `cashier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cashier_active` int(11) DEFAULT '0',
  `chashier_is_soft_deleted` int(11) DEFAULT '0',
  `cashier_password` varchar(32) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cashier_id`),
  KEY `FK_4yi5tl14tl3q5hxnd49pxx0yd` (`person_id`),
  CONSTRAINT `FK_4yi5tl14tl3q5hxnd49pxx0yd` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_is_soft_deleted` int(11) DEFAULT '0',
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_active` int(11) DEFAULT '0',
  `company_bank` varchar(255) NOT NULL,
  `company_brand` varchar(255) NOT NULL,
  `company_clabe` varchar(255) NOT NULL,
  `creator_person_id` bigint(20) DEFAULT NULL,
  `company_description` longtext NOT NULL,
  `company_email_notifications` varchar(255) NOT NULL,
  `company_is_soft_deleted` int(11) DEFAULT '0',
  `company_logo_path_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) NOT NULL,
  `company_owner_account_bank` varchar(255) NOT NULL,
  `company_password` varchar(32) NOT NULL,
  `company_registration_date` varchar(19) NOT NULL,
  `company_tax_company_name` varchar(255) NOT NULL,
  `company_taxId` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `tax_contact_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `FK_h2rewspdf9bnwpbt1nauwiaww` (`address_id`),
  KEY `FK_oc5n83kwn0fls2g52uryl0hn6` (`tax_contact_id`),
  KEY `FK_nn3jxyu2gsll02b79en0dwojx` (`person_id`),
  CONSTRAINT `FK_h2rewspdf9bnwpbt1nauwiaww` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK_nn3jxyu2gsll02b79en0dwojx` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `FK_oc5n83kwn0fls2g52uryl0hn6` FOREIGN KEY (`tax_contact_id`) REFERENCES `contact` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `company_category` */

DROP TABLE IF EXISTS `company_category`;

CREATE TABLE `company_category` (
  `company_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `UK_ti49c9ppy55770hqiqeoyon2t` (`category_id`),
  KEY `FK_1tk1qw5xdqrqc6oxvrywhtg6a` (`company_id`),
  CONSTRAINT `FK_1tk1qw5xdqrqc6oxvrywhtg6a` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `FK_ti49c9ppy55770hqiqeoyon2t` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `company_establishment` */

DROP TABLE IF EXISTS `company_establishment`;

CREATE TABLE `company_establishment` (
  `company_id` bigint(20) NOT NULL,
  `establishment_id` bigint(20) NOT NULL,
  KEY `UK_ih597btkhgoqjn4tnk87mq3lp` (`establishment_id`),
  KEY `FK_m45t35kleewmv9ahuxtxp4x6` (`company_id`),
  CONSTRAINT `FK_ih597btkhgoqjn4tnk87mq3lp` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`),
  CONSTRAINT `FK_m45t35kleewmv9ahuxtxp4x6` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `configuration_mail` */

DROP TABLE IF EXISTS `configuration_mail`;

CREATE TABLE `configuration_mail` (
  `configuration_mail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `configuration_is_soft_deleted` int(11) DEFAULT '0',
  `configuration_mail_smtp_host` varchar(255) NOT NULL,
  `configuration_mail_smtp_port` int(11) NOT NULL,
  PRIMARY KEY (`configuration_mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `configuration_mail_authentication` */

DROP TABLE IF EXISTS `configuration_mail_authentication`;

CREATE TABLE `configuration_mail_authentication` (
  `configuration_mail_authentication_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `configuration_mail_authentication_is_soft_deleted` int(11) DEFAULT '0',
  `configuration_mail_authentication_smtp_host` varchar(255) NOT NULL,
  `configuration_mail_authentication_smtp_password` varchar(255) NOT NULL,
  `configuration_mail_authentication_smtp_port` int(11) NOT NULL,
  `configuration_mail_authentication_smtp_require_authentication` int(11) DEFAULT '0',
  `configuration_mail_authentication_smtp_username` varchar(255) NOT NULL,
  PRIMARY KEY (`configuration_mail_authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact_is_soft_deleted` int(11) DEFAULT '0',
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `FK_9nnd5r5dva32jo5mxn4bfvtet` (`person_id`),
  CONSTRAINT `FK_9nnd5r5dva32jo5mxn4bfvtet` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `establishment` */

DROP TABLE IF EXISTS `establishment`;

CREATE TABLE `establishment` (
  `establishment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `establishment_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `establishment_latitude` varchar(255) DEFAULT NULL,
  `establishment_longitude` varchar(255) DEFAULT NULL,
  `establishment_name` varchar(255) NOT NULL,
  `establishment_registration_date` varchar(19) NOT NULL,
  `establishment_subcategory` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `responsable_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`establishment_id`),
  KEY `FK_37m8vultjjon8fm3px5d7ad7l` (`address_id`),
  KEY `FK_psbe1qrqm6dhbcrduqfmsuenc` (`responsable_id`),
  CONSTRAINT `FK_37m8vultjjon8fm3px5d7ad7l` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK_psbe1qrqm6dhbcrduqfmsuenc` FOREIGN KEY (`responsable_id`) REFERENCES `responsable` (`responsable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `establishment_cashier` */

DROP TABLE IF EXISTS `establishment_cashier`;

CREATE TABLE `establishment_cashier` (
  `establishment_id` bigint(20) NOT NULL,
  `cashier_id` bigint(20) NOT NULL,
  KEY `UK_kwamy96ao5oaf86qvpwuxlcje` (`cashier_id`),
  KEY `FK_igf1crpcr2e06uf7lyyxyejhl` (`establishment_id`),
  CONSTRAINT `FK_igf1crpcr2e06uf7lyyxyejhl` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`),
  CONSTRAINT `FK_kwamy96ao5oaf86qvpwuxlcje` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`cashier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `establishment_category` */

DROP TABLE IF EXISTS `establishment_category`;

CREATE TABLE `establishment_category` (
  `establishment_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FK_lvi3u3wijmrj00m5ekd7nvo9g` (`establishment_id`),
  KEY `UK_gmr43cqnpkxsyiugikvvk61uj` (`category_id`),
  CONSTRAINT `FK_gmr43cqnpkxsyiugikvvk61uj` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_lvi3u3wijmrj00m5ekd7nvo9g` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `freelancer` */

DROP TABLE IF EXISTS `freelancer`;

CREATE TABLE `freelancer` (
  `freelancer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `freelancer_active` int(11) DEFAULT '0',
  `freelancer_is_soft_deleted` int(11) DEFAULT '0',
  `freelance_key` varchar(255) DEFAULT NULL,
  `freelancer_last_login` varchar(255) DEFAULT NULL,
  `freelancer_password` varchar(32) NOT NULL,
  `freelance_tax_id` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`freelancer_id`),
  KEY `FK_jdgju3rgb29lsjnr7obrjlinr` (`address_id`),
  KEY `FK_dwwl0ebtyowjj63xir0n2dpt5` (`person_id`),
  CONSTRAINT `FK_dwwl0ebtyowjj63xir0n2dpt5` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `FK_jdgju3rgb29lsjnr7obrjlinr` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `gender` */

DROP TABLE IF EXISTS `gender`;

CREATE TABLE `gender` (
  `gender_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender_name` varchar(255) NOT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `general_configuration` */

DROP TABLE IF EXISTS `general_configuration`;

CREATE TABLE `general_configuration` (
  `general_configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `general_configuration_upload_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`general_configuration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mail_template` */

DROP TABLE IF EXISTS `mail_template`;

CREATE TABLE `mail_template` (
  `mail_template_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail_template_display_language` varchar(255) NOT NULL,
  `mail_template_from` varchar(255) NOT NULL,
  `mail_template_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `mail_template_message` text NOT NULL,
  `mail_template_name` varchar(255) NOT NULL,
  `mail_template_subject` varchar(255) NOT NULL,
  PRIMARY KEY (`mail_template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `partner` */

DROP TABLE IF EXISTS `partner`;

CREATE TABLE `partner` (
  `partner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `partner_active` int(11) DEFAULT '0',
  `partner_is_soft_deleted` int(11) DEFAULT '0',
  `partner_last_login` varchar(255) DEFAULT NULL,
  `partner_password` varchar(32) NOT NULL,
  `partner_registration_date` varchar(19) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`partner_id`),
  KEY `FK_626d31k1qa52jiqobtfl2pxsx` (`person_id`),
  CONSTRAINT `FK_626d31k1qa52jiqobtfl2pxsx` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_email` varchar(128) NOT NULL,
  `person_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `person_last_name` varchar(128) NOT NULL,
  `person_name` varchar(128) NOT NULL,
  `person_phone` varchar(255) NOT NULL,
  `person_registration_date` varchar(19) NOT NULL,
  `gender_id` bigint(20) NOT NULL,
  `person_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`person_id`),
  KEY `FK_7bqgqlf1ar5j6osfe7lmu1gse` (`gender_id`),
  KEY `FK_6uq8ww4ie2npi5np5jwi8quwn` (`person_type_id`),
  CONSTRAINT `FK_6uq8ww4ie2npi5np5jwi8quwn` FOREIGN KEY (`person_type_id`) REFERENCES `person_type` (`person_type_id`),
  CONSTRAINT `FK_7bqgqlf1ar5j6osfe7lmu1gse` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `person_type` */

DROP TABLE IF EXISTS `person_type`;

CREATE TABLE `person_type` (
  `person_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_type_is_soft_deleted` int(11) DEFAULT '0',
  `person_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`person_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_parameter_names` text,
  `request_accept` varchar(255) DEFAULT NULL,
  `request_accept_encoding` varchar(255) DEFAULT NULL,
  `request_attribute_names` text,
  `request_auth_type` varchar(255) DEFAULT NULL,
  `request_charactect_encoding` varchar(255) DEFAULT NULL,
  `request_connection` varchar(255) DEFAULT NULL,
  `request_content_length` int(11) DEFAULT NULL,
  `request_content_type` varchar(255) DEFAULT NULL,
  `request_header_names` varchar(255) DEFAULT NULL,
  `request_local_address` varchar(255) DEFAULT NULL,
  `request_local_port` int(11) DEFAULT NULL,
  `request_locale` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_origin` varchar(255) DEFAULT NULL,
  `request_path_info` varchar(255) DEFAULT NULL,
  `request_protocol` varchar(255) DEFAULT NULL,
  `request_remote_host` varchar(255) DEFAULT NULL,
  `request_remote_port` int(11) DEFAULT NULL,
  `request_remote_user` varchar(255) DEFAULT NULL,
  `request_scheme` varchar(255) DEFAULT NULL,
  `request_servlet_path` varchar(255) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `request_user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `responsable` */

DROP TABLE IF EXISTS `responsable`;

CREATE TABLE `responsable` (
  `responsable_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `responsable_password` varchar(32) DEFAULT NULL,
  `responsable_is_soft_deleted` tinyint(4) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`responsable_id`),
  KEY `FK_78ecps53vy7pm8ql678ivj01w` (`person_id`),
  CONSTRAINT `FK_78ecps53vy7pm8ql678ivj01w` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `subscriber` */

DROP TABLE IF EXISTS `subscriber`;

CREATE TABLE `subscriber` (
  `subscriber_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subscriber_email` varchar(255) NOT NULL,
  `subscriber_is_soft_deleted` int(11) DEFAULT '0',
  `subscriber_registration_date` varchar(19) NOT NULL,
  PRIMARY KEY (`subscriber_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `transaction_object_id` bigint(20) NOT NULL,
  `transaction_registration_date` varchar(19) NOT NULL,
  `transaction_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK_lmd9i49v95aja312vekp5afvq` (`transaction_type_id`),
  CONSTRAINT `FK_lmd9i49v95aja312vekp5afvq` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transaction_type` */

DROP TABLE IF EXISTS `transaction_type`;

CREATE TABLE `transaction_type` (
  `transaction_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `transaction_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
