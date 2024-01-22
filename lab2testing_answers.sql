-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab2testing
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `test_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT 'unchecked',
  PRIMARY KEY (`id`),
  KEY `test_id` (`test_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`test_id`) REFERENCES `tests` (`id`),
  CONSTRAINT `answers_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,1,1,'Changed answer','unchecked'),(2,1,1,'Answer 2 for Question 2','unchecked'),(3,1,1,'Answer 3 for Question 3','unchecked'),(4,1,1,'Answer 4 for Question 4','unchecked'),(5,1,1,'New answer','correct'),(6,2,2,'Answer 1 for Question 1','unchecked'),(7,2,2,'Answer 2 for Question 2','unchecked'),(8,2,2,'Answer 3 for Question 3','unchecked'),(9,2,2,'Answer 4 for Question 4','unchecked'),(10,2,2,'Answer 5 for Question 5','unchecked'),(11,3,3,'Answer 1 for Question 1','unchecked'),(12,3,3,'Answer 2 for Question 2','unchecked'),(13,3,3,'Answer 3 for Question 3','unchecked'),(14,3,3,'Answer 4 for Question 4','unchecked'),(15,3,3,'Answer 5 for Question 5','unchecked'),(16,1,1,'Hello?','unchecked');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23 17:15:35
