CREATE DATABASE  IF NOT EXISTS `notecastdbbeta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `notecastdbbeta`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: notecastdbbeta
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
-- Table structure for table `contentsinfo`
--

DROP TABLE IF EXISTS `contentsinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contentsinfo` (
  `contents_id` varchar(45) NOT NULL,
  `contents_base_html` varchar(45) NOT NULL,
  `contents_base_styles` varchar(45) NOT NULL,
  `contents_base_js` varchar(45) NOT NULL,
  `contents_root_folder_location` varchar(45) NOT NULL,
  PRIMARY KEY (`contents_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contentsinfo`
--

LOCK TABLES `contentsinfo` WRITE;
/*!40000 ALTER TABLE `contentsinfo` DISABLE KEYS */;
INSERT INTO `contentsinfo` VALUES ('A','A','a','A','a'),('B','A','a','A','a'),('c','c','c','c','c');
/*!40000 ALTER TABLE `contentsinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notebookinfo`
--

DROP TABLE IF EXISTS `notebookinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notebookinfo` (
  `notebook_id` varchar(45) NOT NULL,
  `notebook_title` varchar(45) DEFAULT NULL,
  `notebook_priority` int DEFAULT NULL,
  `notebook_date_created` datetime(6) DEFAULT NULL,
  `notebook_last_edit` datetime(6) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`notebook_id`),
  UNIQUE KEY `notebook_id_UNIQUE` (`notebook_id`),
  KEY `user_email_idx` (`user_email`),
  CONSTRAINT `user_email` FOREIGN KEY (`user_email`) REFERENCES `userinfo` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notebookinfo`
--

LOCK TABLES `notebookinfo` WRITE;
/*!40000 ALTER TABLE `notebookinfo` DISABLE KEYS */;
INSERT INTO `notebookinfo` VALUES ('n','n',1,'2022-01-22 06:45:00.000000','2022-01-22 06:45:00.000000','u');
/*!40000 ALTER TABLE `notebookinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_of_services`
--

DROP TABLE IF EXISTS `quality_of_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quality_of_services` (
  `qos_id` varchar(45) NOT NULL,
  `qos_service_type` varchar(45) NOT NULL,
  PRIMARY KEY (`qos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quality_of_services`
--

LOCK TABLES `quality_of_services` WRITE;
/*!40000 ALTER TABLE `quality_of_services` DISABLE KEYS */;
INSERT INTO `quality_of_services` VALUES ('q','q');
/*!40000 ALTER TABLE `quality_of_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topicinfo`
--

DROP TABLE IF EXISTS `topicinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topicinfo` (
  `topic_id` varchar(45) NOT NULL,
  `topic_title` varchar(45) DEFAULT NULL,
  `topic_date_created` datetime(6) DEFAULT NULL,
  `topic_last_edit` datetime(6) DEFAULT NULL,
  `notebook_id` varchar(45) DEFAULT NULL,
  `contents_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  UNIQUE KEY `topic_id_UNIQUE` (`topic_id`),
  KEY `notebook_id_idx` (`notebook_id`),
  KEY `contents_id_idx` (`contents_id`),
  CONSTRAINT `contents_id` FOREIGN KEY (`contents_id`) REFERENCES `contentsinfo` (`contents_id`),
  CONSTRAINT `notebook_id` FOREIGN KEY (`notebook_id`) REFERENCES `notebookinfo` (`notebook_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topicinfo`
--

LOCK TABLES `topicinfo` WRITE;
/*!40000 ALTER TABLE `topicinfo` DISABLE KEYS */;
INSERT INTO `topicinfo` VALUES ('t','t','2022-01-22 06:45:00.000000','2022-01-22 06:45:00.000000','n','c');
/*!40000 ALTER TABLE `topicinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `user_name` varchar(100) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_profession` varchar(45) DEFAULT NULL,
  `qos_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_email`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  KEY `qos_id` (`qos_id`),
  CONSTRAINT `qos_id` FOREIGN KEY (`qos_id`) REFERENCES `quality_of_services` (`qos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES ('u','u','u','u','q');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-17 20:37:32
