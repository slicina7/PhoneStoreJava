CREATE DATABASE  IF NOT EXISTS `freedb_RPRbaza1234` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `freedb_RPRbaza1234`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRbaza1234
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Apple'),(105,'HTC'),(13,'Huawei'),(104,'LG'),(106,'Nokia'),(10,'Samsung'),(14,'Xiaomi');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyers`
--

DROP TABLE IF EXISTS `buyers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `account_number` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `account_balance` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `account_number_UNIQUE` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyers`
--

LOCK TABLES `buyers` WRITE;
/*!40000 ALTER TABLE `buyers` DISABLE KEYS */;
INSERT INTO `buyers` VALUES (1,'owner','owner','owner@gmail.com','123456789','owner123',10000),(6,'Emma','Johnson','emma@gmail.com','87654334','emma1234',4168),(7,'Ethan','Davis','ethan@hotmail.com','74839294','ethan123',1152),(8,'Olivia','Smith','olivia@gmail.com','300424','olivia12',170),(9,'William','Brown','william@gmail.com','34567','william1',7947),(10,'Ava','Wilson','ava@etf.unsa.ba','234567','ava12345',40),(11,'Sophia','Garcia','sophia@gmail.com','5654345','sophia12',242),(12,'Isabella','Thompson','isabella@hotmail.com','34566543','isabella',287),(13,'Mia','Robinson','mia@gmail.com','34565678','mia12345',50),(14,'Daniel','Lewis','daniel@etf.unsa.ba','23456','daniel12',4315);
/*!40000 ALTER TABLE `buyers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_id` int NOT NULL,
  `version` varchar(30) NOT NULL,
  `price` int NOT NULL,
  `in_stock` int NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `version_UNIQUE` (`version`),
  KEY `brand_id_idx` (`brand_id`),
  CONSTRAINT `brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (7,1,'iPhone 14 Pro',2700,8,'2022-09-16'),(8,10,'Galaxy S22',1600,10,'2022-02-15'),(9,1,'iPhone 14 Pro Max',3200,9,'2022-09-16'),(10,10,'Galaxy S21',1500,7,'2021-01-28'),(11,10,'Galaxy S20',1200,8,'2020-03-18'),(12,10,'Galaxy A53',700,13,'2022-03-23'),(13,10,'Galaxy A72',800,15,'2021-03-13'),(14,10,'Galaxy A21',400,22,'2020-06-12'),(15,1,'iPhone 13',1700,4,'2021-09-24'),(16,1,'iPhone 12',1400,7,'2020-10-23'),(17,1,'iPhone 11',1000,12,'2019-09-20'),(18,1,'iPhone XS',1400,6,'2017-11-03'),(19,14,'Redmi Note 9',400,13,'2019-01-17'),(20,14,'Redmi Note 12',500,21,'2022-10-27'),(21,14,'Redmi A1',200,27,'2022-10-06'),(22,14,'Poco F4',800,21,'2022-06-23'),(23,14,'Redmi 10C',250,19,'2022-03-21'),(24,14,'Poco M4 Pro',450,24,'2022-02-28'),(25,14,'12X',1000,21,'2020-12-28'),(26,14,'11T',650,17,'2020-09-15'),(27,14,'Redmi K50',700,27,'2022-03-17'),(34,13,'Nova Y61',400,19,'2022-11-16'),(35,13,'Nova Y90',550,23,'2022-07-06'),(36,13,'Nova 8i',500,22,'2022-07-22'),(37,13,'Honor X9a',700,25,'2023-01-06'),(38,13,'Honor 70',1000,24,'2022-06-15'),(39,13,'P50 pro',1500,16,'2021-08-12'),(40,13,'Mate 50 pro',2500,17,'2022-09-21'),(41,104,'G7 ThinQ',400,15,'2018-06-01'),(42,104,'V40 ThinQ',700,0,'2018-10-16'),(43,104,'K92',450,19,'2020-11-06'),(44,104,'Velvet',500,21,'2020-05-15'),(45,105,'Desire 22 Pro',900,17,'2022-07-10'),(46,105,'U20',700,18,'2022-10-01'),(47,106,'G60',500,19,'2022-09-01'),(48,106,'X30',800,22,'2022-09-21'),(49,106,'G21',250,21,'2022-02-15'),(50,106,'C21',200,25,'2022-05-03'),(51,106,'6310',100,30,'2021-08-27');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_id` int NOT NULL,
  `buyer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `phone_id_idx` (`phone_id`),
  KEY `buyer_id_idx` (`buyer_id`),
  CONSTRAINT `buyer_id` FOREIGN KEY (`buyer_id`) REFERENCES `buyers` (`id`),
  CONSTRAINT `phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (31,24,6),(32,38,6),(33,48,6),(34,21,6),(35,14,7),(36,40,7),(37,15,8),(38,50,8),(39,51,8),(40,43,10),(41,51,10),(42,13,11),(43,15,12),(44,46,12),(45,25,12),(46,11,13),(47,16,13),(48,9,14);
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-20 17:16:12
