-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actors` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `age` int unsigned NOT NULL,
  `AdminID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Admin_ID_idx` (`AdminID`),
  KEY `FK_User_ID` (`UserID`),
  CONSTRAINT `FK_Admin_ID` FOREIGN KEY (`AdminID`) REFERENCES `admins` (`adminID`),
  CONSTRAINT `FK_User_ID` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (12,'Keanu Reeves','1964-09-02',55,NULL,NULL),(13,'Bruce Wills','1955-03-05',65,NULL,NULL),(14,'Jackie Chan','1954-04-07',66,NULL,NULL),(15,'Christian Bale','1974-01-30',46,NULL,NULL),(16,'Hugh Laurie','1959-06-11',61,NULL,NULL),(17,'Hugh Jackman','1968-10-12',51,NULL,NULL),(18,'Ryan Reynolds','1976-10-23',43,NULL,NULL),(19,'Russell Crowe','1964-04-07',56,NULL,NULL),(20,'Patrick Stewart','1940-07-13',79,NULL,NULL),(21,'Robert Downey, Jr.','1965-04-04',55,NULL,NULL),(22,'Chris Hemsworth','1983-08-11',36,NULL,NULL),(23,'Anne Hathaway','1982-11-12',37,NULL,NULL),(24,'Adam Shulman','1981-04-02',39,NULL,NULL),(25,'Natalie Portman','1981-06-09',39,NULL,NULL);
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `adminID` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`adminID`),
  UNIQUE KEY `admin_name_UNIQUE` (`admin_name`),
  UNIQUE KEY `adminID_UNIQUE` (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'mikel','1234'),(2,'omar','1234'),(3,'mohab','1234');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Genre` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Rating` double NOT NULL,
  `UserID` int DEFAULT NULL,
  `AdminID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_adminID` (`AdminID`),
  KEY `fk_userID` (`UserID`),
  CONSTRAINT `fk_adminID` FOREIGN KEY (`AdminID`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_userID` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Batman','Action',8,NULL,NULL),(2,'Spiderman','Action',7.5,NULL,NULL),(3,'Shrek','Comedy',8,NULL,NULL),(4,'The Lord of the Rings 1','Fantasy',9.3,NULL,NULL),(12,'The Matrix 2','Action',7.3,NULL,NULL),(13,'Parasite','Drama',8.3,NULL,NULL),(14,'Sonic','Adventure',9,NULL,NULL),(15,'Yes Man!','Comedy',8,NULL,NULL),(16,'Spiderman 2','Action',8,NULL,NULL),(18,'Batman 2','Action',8.5,NULL,NULL),(19,'Joker','Action',8.5,NULL,NULL),(20,'The Last Airbender','Action',4.1,NULL,NULL),(21,'The Vast Night','Action',6.7,NULL,NULL),(22,'The Shawshank Redemption','Drama',9.3,NULL,NULL),(23,'The Godfather','Drama',9.2,NULL,NULL),(24,' The Lord of the Rings 3','Adventure',9,NULL,NULL),(25,'Disaster Movie (2008)','Comedy',2,NULL,NULL),(26,'Superbabies: Baby Geniuses 2','Comedy',2.4,NULL,NULL),(27,'Manos: The Hands of Fate','Action',2.6,NULL,NULL),(28,'Code Name: K.O.Z.','Action',3,NULL,NULL),(29,' BloodRayne','Action',3.1,NULL,NULL);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Owner` int NOT NULL,
  `Date` datetime DEFAULT NULL,
  `Report` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REPROT_ADMIN_ID_idx` (`Owner`),
  CONSTRAINT `FK_REPROT_ADMIN_ID` FOREIGN KEY (`Owner`) REFERENCES `admins` (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,2,'2020-06-11 03:08:15','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 4\nfrom [8] to 9  :  	 3\nfrom [9] to 10  :  	 1\n\nTotal rates :	8\"','Rate Report'),(2,2,'2020-06-11 06:19:45','\"Action: 	3\nAdventure: 	1\nDrama: 	1\nFantasy: 	1\nComedy: 	2\nTotal Number of Geners : 5\n\"','Genre Report'),(3,2,'2020-06-12 04:36:38','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 5\nfrom [8] to 9  :  	 3\nfrom [9] to 10  :  	 1\n\nTotal rates :	9\"','Rate Report'),(4,2,'2020-06-12 05:17:37','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 5\nfrom [8] to 9  :  	 4\nfrom [9] to 10  :  	 1\n\nTotal rates :	10\"','Rate Report'),(5,2,'2020-06-12 10:31:34','from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 2\nfrom [8] to 9  :  	 6\nfrom [9] to 10  :  	 2\n\nTotal rates :	10','Rate Report'),(6,2,'2020-06-12 10:41:01','Action: 	11\nAdventure: 	2\nDrama: 	3\nFantasy: 	1\nComedy: 	4\nTotal Number of Geners : 5\n','Genre Report');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_logs`
--

DROP TABLE IF EXISTS `system_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_logs` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `AdminID` int DEFAULT NULL,
  `MovieID` int DEFAULT NULL,
  `ActorID` int DEFAULT NULL,
  `TimeStamp` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Operation` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `LogMessage` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SYSLOG_ADMINID_idx` (`AdminID`),
  CONSTRAINT `FK_SYSLOG_ADMIN_ID` FOREIGN KEY (`AdminID`) REFERENCES `admins` (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_logs`
--

LOCK TABLES `system_logs` WRITE;
/*!40000 ALTER TABLE `system_logs` DISABLE KEYS */;
INSERT INTO `system_logs` VALUES (2,NULL,NULL,22,NULL,'2020/06/09 00:53:46','Add','Admin with adminID = 3 has added a movie with movieId = 22'),(3,NULL,NULL,23,NULL,'2020/06/09 11:00:26','Add','Admin with adminID = 3 has added a movie with movieId = 23'),(4,NULL,NULL,23,NULL,'2020/06/09 11:49:58','Delete','Admin with adminID = 3 has added a deleted a movie with movieId = 23'),(5,NULL,NULL,NULL,10,'2020/06/12 01:22:52','Add','Admin with adminID = 2 has added an actor with actorId = 10'),(6,NULL,NULL,16,NULL,'2020/06/12 01:29:24','Add','Admin with adminID = 2 has added a movie with movieId = 16'),(7,NULL,NULL,18,NULL,'2020/06/12 05:17:00','Add','Admin with adminID = 2 has added a movie with movieId = 18'),(8,NULL,NULL,18,NULL,'2020/06/12 05:18:37','Add Rating','User: Omar with User ID = 1 has added rating for a movie with movieId = 18'),(9,NULL,NULL,18,NULL,'2020/06/12 05:18:37','Add Review','User: Omar with User ID = 1 has added review for a movie with movieId = 18'),(10,NULL,NULL,18,NULL,'2020/06/12 05:50:33','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(11,NULL,NULL,18,NULL,'2020/06/12 05:50:56','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(12,NULL,NULL,18,NULL,'2020/06/12 05:52:06','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(13,NULL,NULL,18,NULL,'2020/06/12 05:53:37','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(14,NULL,NULL,NULL,11,'2020/06/12 05:54:19','Add','Admin with adminID = 2 has added an actor with actorId = 11'),(15,NULL,NULL,16,NULL,'2020/06/12 05:56:49','Add Rating','User: Omar with User ID = 1 has added rating for a movie with movieId = 16'),(16,NULL,NULL,16,NULL,'2020/06/12 05:56:49','Add Review','User: Omar with User ID = 1 has added review for a movie with movieId = 16'),(17,NULL,NULL,16,NULL,'2020/06/12 06:01:55','Update','User: Omar with User ID = 1 has updated rating for a movie with movieId = 16'),(18,NULL,NULL,16,NULL,'2020/06/12 06:01:55','Update','User: Omar with User ID = 1 has updated review for a movie with movieId = 16'),(19,NULL,2,19,NULL,'2020/06/12 22:33:39','Add','Admin with adminID = 2 has added a movie with movieId = 19'),(20,NULL,2,20,NULL,'2020/06/12 22:34:01','Add','Admin with adminID = 2 has added a movie with movieId = 20'),(21,NULL,2,21,NULL,'2020/06/12 22:34:17','Add','Admin with adminID = 2 has added a movie with movieId = 21'),(22,NULL,2,22,NULL,'2020/06/12 22:35:01','Add','Admin with adminID = 2 has added a movie with movieId = 22'),(23,NULL,2,23,NULL,'2020/06/12 22:35:18','Add','Admin with adminID = 2 has added a movie with movieId = 23'),(24,NULL,2,24,NULL,'2020/06/12 22:36:08','Add','Admin with adminID = 2 has added a movie with movieId = 24'),(25,NULL,2,25,NULL,'2020/06/12 22:38:09','Add','Admin with adminID = 2 has added a movie with movieId = 25'),(26,NULL,2,26,NULL,'2020/06/12 22:38:41','Add','Admin with adminID = 2 has added a movie with movieId = 26'),(27,NULL,2,27,NULL,'2020/06/12 22:39:02','Add','Admin with adminID = 2 has added a movie with movieId = 27'),(28,NULL,2,28,NULL,'2020/06/12 22:39:25','Add','Admin with adminID = 2 has added a movie with movieId = 28'),(29,NULL,2,29,NULL,'2020/06/12 22:40:18','Add','Admin with adminID = 2 has added a movie with movieId = 29'),(30,NULL,3,NULL,12,'2020/06/12 22:47:13','Add','Admin with adminID = 3 has added an actor with actorId = 12'),(31,NULL,3,NULL,13,'2020/06/12 22:47:58','Add','Admin with adminID = 3 has added an actor with actorId = 13'),(32,NULL,3,NULL,14,'2020/06/12 22:49:00','Add','Admin with adminID = 3 has added an actor with actorId = 14'),(33,NULL,3,NULL,15,'2020/06/12 22:49:48','Add','Admin with adminID = 3 has added an actor with actorId = 15'),(34,NULL,3,NULL,16,'2020/06/12 22:50:34','Add','Admin with adminID = 3 has added an actor with actorId = 16'),(35,NULL,3,NULL,17,'2020/06/12 22:51:23','Add','Admin with adminID = 3 has added an actor with actorId = 17'),(36,NULL,3,NULL,18,'2020/06/12 22:52:18','Add','Admin with adminID = 3 has added an actor with actorId = 18'),(37,NULL,3,NULL,19,'2020/06/12 22:52:38','Add','Admin with adminID = 3 has added an actor with actorId = 19'),(38,NULL,3,NULL,20,'2020/06/12 22:53:09','Add','Admin with adminID = 3 has added an actor with actorId = 20'),(39,NULL,3,NULL,21,'2020/06/12 22:53:35','Add','Admin with adminID = 3 has added an actor with actorId = 21'),(40,NULL,3,NULL,22,'2020/06/12 22:53:52','Add','Admin with adminID = 3 has added an actor with actorId = 22'),(41,NULL,3,NULL,23,'2020/06/12 22:54:31','Add','Admin with adminID = 3 has added an actor with actorId = 23'),(62,1,NULL,19,NULL,'2020/06/13 00:23:01','Add','User: Omar with User ID = 1 has added rating for a movie with movieId = 19'),(63,1,NULL,19,NULL,'2020/06/13 00:23:01','Add','User: Omar with User ID = 1 has added review for a movie with movieId = 19'),(68,NULL,2,NULL,24,'2020/06/13 00:44:43','Add','Admin with adminID = 2 has added an actor with actorId = 24'),(69,NULL,2,NULL,25,'2020/06/13 00:49:34','Add','Admin with adminID = 2 has added an actor with actorId = 25'),(72,1,NULL,20,NULL,'2020/06/13 00:57:13','Add','User: Omar with User ID = 1 has added rating for a movie with movieId = 20'),(73,1,NULL,20,NULL,'2020/06/13 00:57:13','Add','User: Omar with User ID = 1 has added review for a movie with movieId = 20'),(75,1,NULL,20,NULL,'2020/06/13 00:58:50','Update','User: Omar with User ID = 1 has updated rating for a movie with movieId = 20'),(76,1,NULL,20,NULL,'2020/06/13 00:58:50','Update','User: Omar with User ID = 1 has updated review for a movie with movieId = 20');
/*!40000 ALTER TABLE `system_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrating`
--

DROP TABLE IF EXISTS `userrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrating` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `MovieID` int NOT NULL,
  `UserID` int NOT NULL,
  `UserRating` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MovieID` (`MovieID`),
  KEY `fk_useriduserreview` (`UserID`),
  CONSTRAINT `fk_useriduserreview_` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userrating__fk_1` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrating`
--

LOCK TABLES `userrating` WRITE;
/*!40000 ALTER TABLE `userrating` DISABLE KEYS */;
INSERT INTO `userrating` VALUES (3,3,1,9),(4,3,9,7),(6,3,10,7),(7,1,10,10),(8,1,1,8),(11,1,16,8),(12,18,1,9),(13,16,1,7),(14,2,1,7),(15,2,1,7),(16,4,1,10),(17,2,1,7),(18,4,1,10),(19,12,1,7),(20,19,1,7),(21,20,1,3),(22,20,1,3);
/*!40000 ALTER TABLE `userrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userreview`
--

DROP TABLE IF EXISTS `userreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userreview` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `MovieID` int NOT NULL,
  `UserID` int NOT NULL,
  `Review` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MovieID` (`MovieID`),
  KEY `fk_user_id` (`UserID`),
  CONSTRAINT `fk_user_id_` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userreview_ibfk_1_` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreview`
--

LOCK TABLES `userreview` WRITE;
/*!40000 ALTER TABLE `userreview` DISABLE KEYS */;
INSERT INTO `userreview` VALUES (3,3,1,'Awesome!'),(4,3,9,'Good movie.'),(5,3,10,'Good.'),(6,1,10,'The best in the series!!!!'),(7,1,1,'Nice!'),(8,1,16,':)'),(9,18,1,'Love it!'),(10,16,1,'GOOD MOVIE WATCH IT'),(11,2,1,'Good.'),(12,4,1,'The best!'),(13,12,1,'great....'),(14,19,1,'goooooooood'),(15,20,1,'VERY BAD!');
/*!40000 ALTER TABLE `userreview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Omar','admin'),(9,'sayed','admin'),(10,'Ahmed','admin'),(14,'Mahmoud','123'),(16,'mohab','1234');
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

-- Dump completed on 2020-06-13  1:21:40
