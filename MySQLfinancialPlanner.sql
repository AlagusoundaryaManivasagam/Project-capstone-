-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: financial_planner
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `budget_entries`
--

DROP TABLE IF EXISTS `budget_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget_entries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `budget_id` int NOT NULL,
  `expense_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entries_id_idx` (`expense_id`),
  KEY `fk_budgets_id_idx` (`budget_id`),
  CONSTRAINT `fk_budgets_id` FOREIGN KEY (`budget_id`) REFERENCES `budgets` (`id`),
  CONSTRAINT `fk_entries_id` FOREIGN KEY (`expense_id`) REFERENCES `entries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget_entries`
--

LOCK TABLES `budget_entries` WRITE;
/*!40000 ALTER TABLE `budget_entries` DISABLE KEYS */;
INSERT INTO `budget_entries` VALUES (1,1,30,'2025-01-15'),(2,5,34,'2025-01-16'),(3,4,35,'2025-01-03'),(4,5,36,'2025-01-17'),(5,8,38,'2025-01-15'),(6,1,40,'2024-12-23');
/*!40000 ALTER TABLE `budget_entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budgets`
--

DROP TABLE IF EXISTS `budgets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budgets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(150) COLLATE utf8mb3_bin NOT NULL,
  `amount` float NOT NULL,
  `user_id` int NOT NULL,
  `month` int NOT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_budget_id_idx` (`user_id`),
  CONSTRAINT `fk_users_budgets_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgets`
--

LOCK TABLES `budgets` WRITE;
/*!40000 ALTER TABLE `budgets` DISABLE KEYS */;
INSERT INTO `budgets` VALUES (1,'Accomodation',1500,1,0,0),(2,'Food',1500,1,0,0),(3,'Entertainment',50,1,0,0),(4,'Accomodation',1500,3,0,0),(5,'Food',1500,3,0,0),(6,'Entertainment',100,3,0,0),(8,'Food',1500,12,0,0),(9,'food',1000,1,0,0),(10,'random',1000,1,12,2024),(11,'r',1000,1,12,2024),(12,'random2',1000,1,12,2025),(13,'random4',40,1,12,2024),(14,'random3',121,1,12,2025),(15,'random5',1001,1,12,2025),(16,'random6',1006,1,12,2024),(17,'random7',1007,1,12,2024);
/*!40000 ALTER TABLE `budgets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `amount` float NOT NULL,
  `date` date NOT NULL,
  `user_id` int NOT NULL,
  `flag` varchar(1) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_id_idx` (`user_id`),
  CONSTRAINT `fk_users_entries_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,'salary',5000,'2025-01-10',1,'i'),(2,'divident',500,'2025-01-10',1,'i'),(3,'salary',5000,'2025-01-07',11,'i'),(4,'rent',1000,'2025-01-16',11,'e'),(5,'rent',1000,'2025-01-13',1,'e'),(12,'salary',6000,'2024-12-29',1,'i'),(13,'salary',5000,'2024-11-29',1,'i'),(15,'divident',50,'2024-11-07',1,'i'),(17,'random',100,'2025-01-14',1,'i'),(25,'Subscription',18,'2025-01-15',1,'e'),(26,'Subscription',50,'2025-01-15',1,'e'),(27,'subscription',30,'2024-11-15',1,'e'),(28,'random',50,'2025-01-15',1,'e'),(29,'random',100,'2025-01-15',1,'e'),(30,'random',1,'2025-01-15',1,'e'),(31,'salary',5000,'2025-01-16',3,'i'),(32,'spousal salary',5000,'2025-01-16',3,'i'),(33,'divident',115,'2025-01-09',3,'i'),(34,'Groceries',400,'2025-01-16',3,'e'),(35,'rent',1000,'2025-01-03',3,'e'),(36,'random',100,'2025-01-17',3,'e'),(37,'salary',5000,'2025-01-22',12,'i'),(38,'Groceries',500,'2025-01-15',12,'e'),(39,'salary',5000,'2024-08-23',1,'i'),(40,'random',50,'2024-12-23',1,'e'),(41,'random',100,'2025-04-17',1,'i');
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `user_role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_id_idx` (`user_id`),
  CONSTRAINT `fk_users_user_roles_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) COLLATE utf8mb3_bin NOT NULL,
  `email` varchar(100) COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Alagusoundarya','abc@gmail.com','$2a$10$IcCwkAXzXiGzgmNRURz67uBo6tFzbTz5OBmDVgF/R.vhEY.5bSS.S'),(3,'Alagu','a@b.com','$2a$10$/INCFLuOEw/5IrqblbtIf.SLNRH4A8AJ.qRiFS2e.GsNsy6uXYNDm'),(4,'as','as@gmail.com','$2a$10$BcMFaEdeus6z8ANBn29SoOkJ2jiYAWo8yX9OFjh5drsU7Eg4KuINW'),(5,'aaa','aaa@gmail.com','$2a$10$CAea4ObQ3KR0XL9KSQW90.F2tYU22aVWwX9teF2pK0hAbhagCgZJq'),(6,'asm','asm@gmail.com','$2a$10$jMtZdLMA.7wEXpmywnt6tuQ1I0ZeS3GuWiyoMV66izhZxzVbCM6re'),(7,'ak','ak@gmail.com','$2a$10$cbEPFupooBhI5FULHLyo7eKPcRC6bBFKsgqYZJc5buK6kN9uYQVJW'),(8,'aks','aks@gmail.com','$2a$10$LP2xowcqHpgc3hD637lKyOcsyzz.Y5hTk7DF8ERN4uG2r3.oOoYda'),(9,'aks1','aks1@gmail.com','$2a$10$0jAvP9tb.5JhkRigQzZVzO38DZ3RotoLk4XsuYIsx8ncg1dlQop.m'),(10,'aks2','aks2@gmail.com','$2a$10$YDZ4M.5zM9LVJFU/kvde8.nst.GBEHn7VeAakL62CObcciCnhnVcq'),(11,'Aks3','Aks3@gmail.com','$2a$10$kqSX9LSOoOP252MHh9ATZ.OTk/w02s8.6xDcKd8xfr8oABhnBvnVO'),(12,'A2','A2@gmail.com','$2a$10$xKhrXCz9tMBaWJN4BqFome4Z.sJaNGu0RXKDDkwQRaE5c/P3ywG/6');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-30 17:23:31
