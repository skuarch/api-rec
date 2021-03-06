CREATE DATABASE  IF NOT EXISTS `regaloenclave` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `regaloenclave`;
-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.1.66    Database: regaloenclave
-- ------------------------------------------------------
-- Server version 5.6.22-log

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
  `address_all` longtext NOT NULL,
  `address_city` varchar(255) DEFAULT NULL,
  `address_country` varchar(255) NOT NULL,
  `address_is_soft_deleted` int(11) DEFAULT '0',
  `address_registration_date` varchar(19) NOT NULL,
  `address_state` varchar(255) NOT NULL,
  `address_zip_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `affiliate`
--

DROP TABLE IF EXISTS `affiliate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate` (
  `affiliate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `affiliate_active` int(11) DEFAULT '0',
  `affiliate_approved` int(11) DEFAULT '0',
  `affiliate_bank` varchar(255) NOT NULL,
  `affiliate_brand` varchar(255) NOT NULL,
  `affiliate_clabe` varchar(255) NOT NULL,
  `creator_person_id` bigint(20) DEFAULT NULL,
  `affiliate_description` longtext NOT NULL,
  `affiliate_discount_percentage` int(11) NOT NULL DEFAULT '0',
  `affiliate_email_notifications` varchar(255) NOT NULL,
  `affiliate_facebook` varchar(255) DEFAULT NULL,
  `affiliate_is_soft_deleted` int(11) DEFAULT '0',
  `affiliate_last_login` varchar(255) DEFAULT NULL,
  `affiliate_logo_path_name` varchar(255) DEFAULT NULL,
  `affiliate_owner_account_bank` varchar(255) NOT NULL,
  `affiliate_password` varchar(32) NOT NULL,
  `affiliate_registration_date` varchar(19) NOT NULL,
  `affiliate_tax_company_name` varchar(255) NOT NULL,
  `affiliate_tax_id` varchar(255) NOT NULL,
  `affiliate_url_logo` varchar(255) DEFAULT NULL,
  `affiliate_website` varchar(255) DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affiliate`
--

LOCK TABLES `affiliate` WRITE;
/*!40000 ALTER TABLE `affiliate` DISABLE KEYS */;
/*!40000 ALTER TABLE `affiliate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `affiliate_category`
--

DROP TABLE IF EXISTS `affiliate_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate_category` (
  `affiliate_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FK_l4gllx6ierqbkrxv0xh6o6bm1` (`affiliate_id`),
  KEY `UK_so8jwpxf6uf1vhxi0esjptocs` (`category_id`),
  CONSTRAINT `FK_l4gllx6ierqbkrxv0xh6o6bm1` FOREIGN KEY (`affiliate_id`) REFERENCES `affiliate` (`affiliate_id`),
  CONSTRAINT `FK_so8jwpxf6uf1vhxi0esjptocs` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affiliate_category`
--

LOCK TABLES `affiliate_category` WRITE;
/*!40000 ALTER TABLE `affiliate_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `affiliate_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `affiliate_establishment`
--

DROP TABLE IF EXISTS `affiliate_establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate_establishment` (
  `establishment_id` bigint(20) NOT NULL,
  `affiliate_id` bigint(20) NOT NULL,
  PRIMARY KEY (`establishment_id`,`affiliate_id`),  
  KEY `FK_3ur9xjpwu02ohsn69gn2uawm0` (`affiliate_id`),
  CONSTRAINT `FK_3ur9xjpwu02ohsn69gn2uawm0` FOREIGN KEY (`affiliate_id`) REFERENCES `affiliate` (`affiliate_id`),
  CONSTRAINT `FK_r72ghcevu3xuooeq1ee51gak1` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affiliate_establishment`
--

LOCK TABLES `affiliate_establishment` WRITE;
/*!40000 ALTER TABLE `affiliate_establishment` DISABLE KEYS */;
/*!40000 ALTER TABLE `affiliate_establishment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `affiliate_type`
--

DROP TABLE IF EXISTS `affiliate_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate_type` (
  `affiliate_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `affiliate_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`affiliate_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affiliate_type`
--

LOCK TABLES `affiliate_type` WRITE;
/*!40000 ALTER TABLE `affiliate_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `affiliate_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_response`
--

DROP TABLE IF EXISTS `bank_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_response` (
  `bank_response_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_response_email_depositor` varchar(255) DEFAULT NULL,
  `bank_response_text` varchar(768) DEFAULT NULL,
  `transfer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bank_response_id`),
  KEY `FK_p9f2cgyb8dbqhojnhw82hhrex` (`transfer_id`),
  CONSTRAINT `FK_p9f2cgyb8dbqhojnhw82hhrex` FOREIGN KEY (`transfer_id`) REFERENCES `transfer` (`transfer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_response`
--

LOCK TABLES `bank_response` WRITE;
/*!40000 ALTER TABLE `bank_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashier`
--

DROP TABLE IF EXISTS `cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashier` (
  `cashier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cashier_active` int(11) DEFAULT '0',
  `chashier_is_soft_deleted` int(11) DEFAULT '0',
  `cashier_last_login` varchar(255) DEFAULT NULL,
  `cashier_password` varchar(32) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cashier_id`),
  KEY `FK_4yi5tl14tl3q5hxnd49pxx0yd` (`person_id`),
  CONSTRAINT `FK_4yi5tl14tl3q5hxnd49pxx0yd` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier`
--

LOCK TABLES `cashier` WRITE;
/*!40000 ALTER TABLE `cashier` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashier_secret_consumed`
--

DROP TABLE IF EXISTS `cashier_secret_consumed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashier_secret_consumed` (
  `cashier_secret_consumed_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cashier_secret_consumed_amount` decimal(19,2) NOT NULL,
  `cashier_secret_consumed_date` varchar(19) NOT NULL,
  `cashier_secret_consumed_is_soft_deleted` int(11) DEFAULT '0',
  `cashier_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `secret_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cashier_secret_consumed_id`),
  KEY `FK_1tgndjesbsaiqhu6qyouu6hck` (`cashier_id`),
  KEY `FK_955gb3m5yutx9xm13sr31uugh` (`recipient_id`),
  KEY `FK_o9v179s557psi4pyyas39wjv0` (`secret_id`),
  CONSTRAINT `FK_1tgndjesbsaiqhu6qyouu6hck` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`cashier_id`),
  CONSTRAINT `FK_955gb3m5yutx9xm13sr31uugh` FOREIGN KEY (`recipient_id`) REFERENCES `recipient` (`recipient_id`),
  CONSTRAINT `FK_o9v179s557psi4pyyas39wjv0` FOREIGN KEY (`secret_id`) REFERENCES `secret` (`secret_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier_secret_consumed`
--

LOCK TABLES `cashier_secret_consumed` WRITE;
/*!40000 ALTER TABLE `cashier_secret_consumed` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashier_secret_consumed` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_active` int(11) DEFAULT '0',
  `affiliate_approved` int(11) DEFAULT '0',
  `company_bank` varchar(255) NOT NULL,
  `company_brand` varchar(255) NOT NULL,
  `company_clabe` varchar(255) NOT NULL,
  `creator_person_id` bigint(20) DEFAULT NULL,
  `company_description` longtext NOT NULL,
  `company_discount_percentage` int(11) NOT NULL DEFAULT '0',
  `company_email_notifications` varchar(255) NOT NULL,
  `company_facebook` varchar(255) DEFAULT NULL,
  `company_is_soft_deleted` int(11) DEFAULT '0',
  `company_last_login` varchar(255) DEFAULT NULL,
  `company_logo_path_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) NOT NULL,
  `company_owner_account_bank` varchar(255) NOT NULL,
  `company_password` varchar(32) NOT NULL,
  `company_registration_date` varchar(19) NOT NULL,
  `company_tax_company_name` varchar(255) NOT NULL,
  `company_taxId` varchar(255) DEFAULT NULL,
  `company_url_logo` varchar(255) DEFAULT NULL,
  `company_website` varchar(255) DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_category`
--

DROP TABLE IF EXISTS `company_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_category` (
  `company_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FK_1tk1qw5xdqrqc6oxvrywhtg6a` (`company_id`),
  KEY `UK_ti49c9ppy55770hqiqeoyon2t` (`category_id`),
  CONSTRAINT `FK_1tk1qw5xdqrqc6oxvrywhtg6a` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `FK_ti49c9ppy55770hqiqeoyon2t` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_category`
--

LOCK TABLES `company_category` WRITE;
/*!40000 ALTER TABLE `company_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_establishment`
--

DROP TABLE IF EXISTS `company_establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_establishment` (
  `company_id` bigint(20) NOT NULL,
  `establishment_id` bigint(20) NOT NULL,
  KEY `FK_m45t35kleewmv9ahuxtxp4x6` (`company_id`),
  KEY `UK_ih597btkhgoqjn4tnk87mq3lp` (`establishment_id`),
  CONSTRAINT `FK_ih597btkhgoqjn4tnk87mq3lp` FOREIGN KEY (`establishment_id`) REFERENCES `establishment` (`establishment_id`),
  CONSTRAINT `FK_m45t35kleewmv9ahuxtxp4x6` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_establishment`
--

LOCK TABLES `company_establishment` WRITE;
/*!40000 ALTER TABLE `company_establishment` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_establishment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_mail`
--

DROP TABLE IF EXISTS `configuration_mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_mail` (
  `configuration_mail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `configuration_is_soft_deleted` int(11) DEFAULT '0',
  `configuration_mail_smtp_host` varchar(255) NOT NULL,
  `configuration_mail_smtp_port` int(11) NOT NULL,
  PRIMARY KEY (`configuration_mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_mail`
--

LOCK TABLES `configuration_mail` WRITE;
/*!40000 ALTER TABLE `configuration_mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration_mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_mail_authentication`
--

DROP TABLE IF EXISTS `configuration_mail_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_mail_authentication`
--

LOCK TABLES `configuration_mail_authentication` WRITE;
/*!40000 ALTER TABLE `configuration_mail_authentication` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration_mail_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact_is_soft_deleted` int(11) DEFAULT '0',
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `FK_9nnd5r5dva32jo5mxn4bfvtet` (`person_id`),
  CONSTRAINT `FK_9nnd5r5dva32jo5mxn4bfvtet` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depositor`
--

DROP TABLE IF EXISTS `depositor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depositor` (
  `depositor_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `depositor_email` varchar(255) NOT NULL,
  `depositor_is_soft_deleted` int(11) DEFAULT '0',
  `depositor_last_name` varchar(255) NOT NULL,
  `depositor_name` varchar(255) NOT NULL,
  `depositor_phone` varchar(255) NOT NULL,
  PRIMARY KEY (`depositor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depositor`
--

LOCK TABLES `depositor` WRITE;
/*!40000 ALTER TABLE `depositor` DISABLE KEYS */;
/*!40000 ALTER TABLE `depositor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `establishment`
--

DROP TABLE IF EXISTS `establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `establishment`
--

LOCK TABLES `establishment` WRITE;
/*!40000 ALTER TABLE `establishment` DISABLE KEYS */;
/*!40000 ALTER TABLE `establishment` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `establishment_cashier`
--

LOCK TABLES `establishment_cashier` WRITE;
/*!40000 ALTER TABLE `establishment_cashier` DISABLE KEYS */;
/*!40000 ALTER TABLE `establishment_cashier` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `establishment_category`
--

LOCK TABLES `establishment_category` WRITE;
/*!40000 ALTER TABLE `establishment_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `establishment_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `freelancer`
--

DROP TABLE IF EXISTS `freelancer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freelancer`
--

LOCK TABLES `freelancer` WRITE;
/*!40000 ALTER TABLE `freelancer` DISABLE KEYS */;
/*!40000 ALTER TABLE `freelancer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_configuration`
--

DROP TABLE IF EXISTS `general_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_configuration` (
  `general_configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `general_configuration_upload_path` varchar(255) DEFAULT NULL,
  `general_configuration_url_static_images` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`general_configuration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_configuration`
--

LOCK TABLES `general_configuration` WRITE;
/*!40000 ALTER TABLE `general_configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_configuration` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_template`
--

LOCK TABLES `mail_template` WRITE;
/*!40000 ALTER TABLE `mail_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_amount` decimal(5,2) NOT NULL,
  `payment_behalf` bigint(20) NOT NULL,
  `payment_behalf_brand` varchar(255) NOT NULL,
  `payment_creation_date` varchar(19) NOT NULL,
  `payment_discount_percentage` tinyint(4) NOT NULL,
  `payment_is_soft_deleted` int(11) DEFAULT '0',
  `payment_last_update` varchar(19) DEFAULT NULL,
  `payment_profit` decimal(5,2) NOT NULL,
  `payment_totla_pay` decimal(5,2) NOT NULL,
  `affiliate_type_id` bigint(20) NOT NULL,
  `cashier_id` bigint(20) NOT NULL,
  `payment_status_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `secret_id` bigint(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_ejr46n6wll5tpp2lgbbasnsk1` (`affiliate_type_id`),
  KEY `FK_8q4146yeetmbadrmulpsxkasr` (`cashier_id`),
  KEY `FK_hb3y13tlenq2wbvy3mm7rcgsk` (`payment_status_id`),
  KEY `FK_fij47jvku9q031gsjw0jr8q9t` (`recipient_id`),
  KEY `FK_nn2exvlu3v66v826p36x2a4tp` (`secret_id`),
  CONSTRAINT `FK_8q4146yeetmbadrmulpsxkasr` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`cashier_id`),
  CONSTRAINT `FK_ejr46n6wll5tpp2lgbbasnsk1` FOREIGN KEY (`affiliate_type_id`) REFERENCES `affiliate_type` (`affiliate_type_id`),
  CONSTRAINT `FK_fij47jvku9q031gsjw0jr8q9t` FOREIGN KEY (`recipient_id`) REFERENCES `recipient` (`recipient_id`),
  CONSTRAINT `FK_hb3y13tlenq2wbvy3mm7rcgsk` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`payment_status_id`),
  CONSTRAINT `FK_nn2exvlu3v66v826p36x2a4tp` FOREIGN KEY (`secret_id`) REFERENCES `secret` (`secret_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_status`
--

DROP TABLE IF EXISTS `payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_status` (
  `payment_status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;

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
  `person_phone` varchar(255) DEFAULT NULL,
  `person_registration_date` varchar(19) NOT NULL,
  `gender_id` bigint(20) NOT NULL,
  `person_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`person_id`),
  KEY `FK_7bqgqlf1ar5j6osfe7lmu1gse` (`gender_id`),
  KEY `FK_6uq8ww4ie2npi5np5jwi8quwn` (`person_type_id`),
  CONSTRAINT `FK_6uq8ww4ie2npi5np5jwi8quwn` FOREIGN KEY (`person_type_id`) REFERENCES `person_type` (`person_type_id`),
  CONSTRAINT `FK_7bqgqlf1ar5j6osfe7lmu1gse` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_type`
--

LOCK TABLES `person_type` WRITE;
/*!40000 ALTER TABLE `person_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipient`
--

DROP TABLE IF EXISTS `recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipient` (
  `recipient_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recipient_email` varchar(255) NOT NULL,
  `recipient_is_soft_deleted` int(11) DEFAULT '0',
  `recipient_last_name` varchar(255) NOT NULL,
  `recipient_name` varchar(255) NOT NULL,
  `recipient_phone` varchar(255) NOT NULL,
  PRIMARY KEY (`recipient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipient`
--

LOCK TABLES `recipient` WRITE;
/*!40000 ALTER TABLE `recipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipient_secret`
--

DROP TABLE IF EXISTS `recipient_secret`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipient_secret` (
  `recipient_id` bigint(20) NOT NULL,
  `secret_id` bigint(20) NOT NULL,
  KEY `FK_boc8a8ss7ar7n353qu8p4nlqw` (`recipient_id`),
  KEY `UK_2lnpedn148dawln6spsu9dyq9` (`secret_id`),
  CONSTRAINT `FK_2lnpedn148dawln6spsu9dyq9` FOREIGN KEY (`secret_id`) REFERENCES `secret` (`secret_id`),
  CONSTRAINT `FK_boc8a8ss7ar7n353qu8p4nlqw` FOREIGN KEY (`recipient_id`) REFERENCES `recipient` (`recipient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipient_secret`
--

LOCK TABLES `recipient_secret` WRITE;
/*!40000 ALTER TABLE `recipient_secret` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipient_secret` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'','text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2',NULL,'org.glassfish.web.RequestFacadeHelper, org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER, org.springframework.web.servlet.DispatcherServlet.CONTEXT, org.jboss.weld.context.conversation.ConversationIdGenerator, org.jboss.weld.context.ConversationContext.conversations, javax.servlet.request.key_size, javax.servlet.request.cipher_suite, javax.servlet.request.ssl_session_id, org.springframework.web.servlet.DispatcherServlet.THEME_SOURCE, org.springframework.web.servlet.DispatcherServlet.LOCALE_RESOLVER, org.springframework.web.servlet.HandlerMapping.bestMatchingPattern, org.jboss.weld.servlet.ConversationContextActivatorCONTEXT_ACTIVATED_IN_REQUEST, org.jboss.weld.context.ignore.guard.marker, org.springframework.web.servlet.DispatcherServlet.OUTPUT_FLASH_MAP, encoding-filter.FILTERED, org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping, org.springframework.web.servlet.DispatcherServlet.FLASH_MAP_MANAGER, org.springframework.web.servlet.HandlerMapping.uriTemplateVariables, org.springframework.web.servlet.DispatcherServlet.THEME_RESOLVER, org.springframework.core.convert.ConversionService, ',NULL,'UTF-8','keep-alive',-1,NULL,'pragma, cache-control, expires, user-agent, host, accept, connection, ','127.0.0.1',443,'en_US','POST',NULL,NULL,'HTTP/1.1','127.0.0.1',59396,NULL,'https','/v1/category/get','/api-rec/v1/category/get','https://localhost/api-rec/v1/category/get','Java/1.8.0_45');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable`
--

LOCK TABLES `responsable` WRITE;
/*!40000 ALTER TABLE `responsable` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secret`
--

DROP TABLE IF EXISTS `secret`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secret` (
  `secret_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `secret_consumed_date` varchar(19) DEFAULT NULL,
  `secret_expiry_date` varchar(19) NOT NULL,
  `secret_generation_date` varchar(19) NOT NULL,
  `secret_is_soft_deleted` int(11) DEFAULT '0',
  `secret_alphanumeric` varchar(255) NOT NULL,
  `secret_value` decimal(7,2) DEFAULT NULL,
  `secret_status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`secret_id`),
  UNIQUE KEY `UK_f58dgelh1yd5goi9idjj90sxq` (`secret_alphanumeric`),
  KEY `FK_q4c3d0ru3cqx9gbaoc43bugxu` (`secret_status_id`),
  CONSTRAINT `FK_q4c3d0ru3cqx9gbaoc43bugxu` FOREIGN KEY (`secret_status_id`) REFERENCES `secret_status` (`secret_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secret`
--

LOCK TABLES `secret` WRITE;
/*!40000 ALTER TABLE `secret` DISABLE KEYS */;
/*!40000 ALTER TABLE `secret` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secret_status`
--

DROP TABLE IF EXISTS `secret_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secret_status` (
  `secret_status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `secret_status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`secret_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secret_status`
--

LOCK TABLES `secret_status` WRITE;
/*!40000 ALTER TABLE `secret_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `secret_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber`
--

DROP TABLE IF EXISTS `subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber` (
  `subscriber_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subscriber_email` varchar(255) NOT NULL,
  `subscriber_is_soft_deleted` int(11) DEFAULT '0',
  `subscriber_registration_date` varchar(19) NOT NULL,
  PRIMARY KEY (`subscriber_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber`
--

LOCK TABLES `subscriber` WRITE;
/*!40000 ALTER TABLE `subscriber` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfer` (
  `transfer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transfer_amount` decimal(7,2) DEFAULT NULL,
  `transfer_date` varchar(19) NOT NULL,
  `transfer_is_soft_deleted` int(11) DEFAULT '0',
  `secret_alphanumeric` varchar(255) NOT NULL,
  `depositor_id` bigint(20) DEFAULT NULL,
  `recipient_id` bigint(20) DEFAULT NULL,
  `transfer_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transfer_id`),
  UNIQUE KEY `UK_spgb1h2qbsbwha11w7pu9ht0y` (`secret_alphanumeric`),
  KEY `FK_q8ov2edjkkitfws54g66d8fml` (`depositor_id`),
  KEY `FK_m7nw2hy8l44dhcnb72t91i54v` (`recipient_id`),
  KEY `FK_ld8mheumfw38vpp1frt1e7i65` (`transfer_type_id`),
  CONSTRAINT `FK_ld8mheumfw38vpp1frt1e7i65` FOREIGN KEY (`transfer_type_id`) REFERENCES `transfer_type` (`transfer_type_id`),
  CONSTRAINT `FK_m7nw2hy8l44dhcnb72t91i54v` FOREIGN KEY (`recipient_id`) REFERENCES `recipient` (`recipient_id`),
  CONSTRAINT `FK_q8ov2edjkkitfws54g66d8fml` FOREIGN KEY (`depositor_id`) REFERENCES `depositor` (`depositor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer_type`
--

DROP TABLE IF EXISTS `transfer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfer_type` (
  `transfer_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transfer_type_is_soft_deleted` int(11) DEFAULT '0',
  `transfer_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transfer_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_type`
--

LOCK TABLES `transfer_type` WRITE;
/*!40000 ALTER TABLE `transfer_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-10 21:21:57
