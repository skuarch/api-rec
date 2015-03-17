 
CREATE DATABASE  IF NOT EXISTS `regaloenclave` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `regaloenclave`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: 192.168.1.65    Database: regaloenclave
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_all` varchar(255) NOT NULL,
  `address_city` varchar(255) DEFAULT NULL,
  `address_country` varchar(255) NOT NULL,
  `address_is_soft_deleted` int(11) DEFAULT '0',
  `address_registration_date` varchar(19) NOT NULL,
  `address_state` varchar(255) NOT NULL,
  `address_zip_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `affiliate`
--

DROP TABLE IF EXISTS `affiliate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate` (
  `affiliate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `affiliate_is_soft_deleted` int(11) DEFAULT '0',
  `affiliate_password` varchar(32) NOT NULL,
  `affiliate_registration_date` varchar(19) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  `freelancer_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`affiliate_id`),
  KEY `FK_e9kw7qq9s07yhbdto2al5o1ov` (`address_id`),
  KEY `FK_jowyvx1elw3n92xp61isevsvc` (`freelancer_id`),
  KEY `FK_d80kivo3i0ylh2ydpt9938kw1` (`person_id`),
  CONSTRAINT `FK_d80kivo3i0ylh2ydpt9938kw1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `FK_e9kw7qq9s07yhbdto2al5o1ov` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK_jowyvx1elw3n92xp61isevsvc` FOREIGN KEY (`freelancer_id`) REFERENCES `freelancer` (`freelancer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `affiliate_establishment`
--

DROP TABLE IF EXISTS `affiliate_establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate_establishment` (
  `affiliate_id` bigint(20) NOT NULL,
  `establishment_id` bigint(20) NOT NULL,
  KEY `FK_3ur9xjpwu02ohsn69gn2uawm0` (`affiliate_id`),
  KEY `UK_r72ghcevu3xuooeq1ee51gak1` (`establishment_id`),
  CONSTRAINT `FK_3ur9xjpwu02ohsn69gn2uawm0` FOREIGN KEY (`affiliate_id`) REFERENCES `affiliate` (`affiliate_id`),
  CONSTRAINT `FK_r72ghcevu3xuooeq1ee51gak1` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cashier`
--

DROP TABLE IF EXISTS `cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashier` (
  `cashier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chashier_is_soft_deleted` int(11) DEFAULT '0',
  `cashier_password` varchar(32) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cashier_id`),
  KEY `FK_4yi5tl14tl3q5hxnd49pxx0yd` (`person_id`),
  CONSTRAINT `FK_4yi5tl14tl3q5hxnd49pxx0yd` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_is_soft_deleted` int(11) DEFAULT '0',
  `category_name` varchar(255) NOT NULL,
  `category_name_en` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `configuration_is_soft_deleted` int(11) DEFAULT '0',
  `configuration_smtp_host` varchar(255) NOT NULL,
  `configuration_smtp_password` varchar(255) NOT NULL,
  `configuration_smtp_port` int(11) NOT NULL,
  `configuration_smtp_username` varchar(255) NOT NULL,
  PRIMARY KEY (`configuration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `establishment`
--

DROP TABLE IF EXISTS `establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establishment` (
  `establishment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `establishment_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `establishment_cashier`
--

DROP TABLE IF EXISTS `establishment_cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establishment_cashier` (
  `establishment_id` bigint(20) NOT NULL,
  `cashier_id` bigint(20) NOT NULL,
  KEY `FK_igf1crpcr2e06uf7lyyxyejhl` (`establishment_id`),
  KEY `UK_kwamy96ao5oaf86qvpwuxlcje` (`cashier_id`),
  CONSTRAINT `FK_igf1crpcr2e06uf7lyyxyejhl` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`),
  CONSTRAINT `FK_kwamy96ao5oaf86qvpwuxlcje` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`cashier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `establishment_category`
--

DROP TABLE IF EXISTS `establishment_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establishment_category` (
  `establishment_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FK_lvi3u3wijmrj00m5ekd7nvo9g` (`establishment_id`),
  KEY `UK_gmr43cqnpkxsyiugikvvk61uj` (`category_id`),
  CONSTRAINT `FK_gmr43cqnpkxsyiugikvvk61uj` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_lvi3u3wijmrj00m5ekd7nvo9g` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `freelancer`
--

DROP TABLE IF EXISTS `freelancer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `freelancer` (
  `freelancer_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `gender_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender_name` varchar(255) NOT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mail_template`
--

DROP TABLE IF EXISTS `mail_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_template` (
  `mail_template_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail_template_display_language` varchar(255) NOT NULL,
  `mail_template_from` varchar(255) NOT NULL,
  `mail_template_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `mail_template_message` text NOT NULL,
  `mail_template_name` varchar(255) NOT NULL,
  `mail_template_subject` varchar(255) NOT NULL,
  PRIMARY KEY (`mail_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_type`
--

DROP TABLE IF EXISTS `person_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_type` (
  `person_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_type_is_soft_deleted` int(11) DEFAULT '0',
  `person_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`person_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsable` (
  `responsable_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `responsable_password` varchar(32) DEFAULT NULL,
  `responsable_is_soft_deleted` tinyint(4) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`responsable_id`),
  KEY `FK_78ecps53vy7pm8ql678ivj01w` (`person_id`),
  CONSTRAINT `FK_78ecps53vy7pm8ql678ivj01w` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `transaction_object_id` bigint(20) NOT NULL,
  `transaction_registration_date` varchar(19) NOT NULL,
  `transaction_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK_lmd9i49v95aja312vekp5afvq` (`transaction_type_id`),
  CONSTRAINT `FK_lmd9i49v95aja312vekp5afvq` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`transaction_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_type` (
  `transaction_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type_is_soft_deleted` int(11) NOT NULL DEFAULT '0',
  `transaction_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-14 15:03:48
