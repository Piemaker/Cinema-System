-- MariaDB dump 10.17  Distrib 10.4.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	10.4.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Report`
--

DROP TABLE IF EXISTS `Report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Report` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Owner` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  `Report` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Type` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fkAdminID` (`Owner`),
  CONSTRAINT `fkAdminID` FOREIGN KEY (`Owner`) REFERENCES `admins` (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Report`
--

LOCK TABLES `Report` WRITE;
/*!40000 ALTER TABLE `Report` DISABLE KEYS */;
INSERT INTO `Report` VALUES (1,2,'2020-06-11 03:08:15','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 4\nfrom [8] to 9  :  	 3\nfrom [9] to 10  :  	 1\n\nTotal rates :	8\"','Rate Report'),(2,2,'2020-06-11 06:19:45','\"Action: 	3\nAdventure: 	1\nDrama: 	1\nFantasy: 	1\nComedy: 	2\nTotal Number of Geners : 5\n\"','Genre Report'),(3,2,'2020-06-12 04:36:38','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 5\nfrom [8] to 9  :  	 3\nfrom [9] to 10  :  	 1\n\nTotal rates :	9\"','Rate Report'),(4,2,'2020-06-12 05:17:37','\"from [0] to 1  :  	 0\nfrom [1] to 2  :  	 0\nfrom [2] to 3  :  	 0\nfrom [3] to 4  :  	 0\nfrom [4] to 5  :  	 0\nfrom [5] to 6  :  	 0\nfrom [6] to 7  :  	 0\nfrom [7] to 8  :  	 5\nfrom [8] to 9  :  	 4\nfrom [9] to 10  :  	 1\n\nTotal rates :	10\"','Rate Report');
/*!40000 ALTER TABLE `Report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `dateOfBirth` date NOT NULL,
  `age` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (4,'keanu','1964-09-02',56),(6,'Omar','1997-07-16',23),(7,'OAMR','1997-07-16',22),(8,'someone','1990-04-02',30),(9,'someone','1990-04-02',30),(10,'someone','1990-10-02',29),(11,'Mahmoud Men3em','1998-02-23',22);
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `adminID` int(11) NOT NULL,
  `admin_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Genre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Rating` double NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `AdminID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_userID` (`UserID`),
  KEY `fk_adminID` (`AdminID`),
  CONSTRAINT `fk_adminID` FOREIGN KEY (`AdminID`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_userID` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Batman','Action',8,NULL,NULL),(2,'Spiderman','Action',7.5,NULL,NULL),(3,'Shrek','Comedy',8,NULL,NULL),(4,'The Lord of the Rings 1','Fantasy',9.3,NULL,NULL),(12,'The Matrix 2','Action',7.3,NULL,NULL),(13,'Parasite','Drama',8.3,NULL,NULL),(14,'Sonic','Adventure',9,NULL,NULL),(15,'Yes Man!','Comedy',8,NULL,NULL),(16,'Spiderman 2','Action',8,NULL,NULL),(18,'Batman 2','Action',8.5,NULL,NULL);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_logs`
--

DROP TABLE IF EXISTS `system_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_logs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `AdminID` int(11) DEFAULT NULL,
  `MovieID` int(11) DEFAULT NULL,
  `ActorID` int(11) DEFAULT NULL,
  `TimeStamp` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Operation` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LogMessage` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_logs`
--

LOCK TABLES `system_logs` WRITE;
/*!40000 ALTER TABLE `system_logs` DISABLE KEYS */;
INSERT INTO `system_logs` VALUES (2,NULL,3,22,NULL,'2020/06/09 00:53:46','Add','Admin with adminID = 3 has added a movie with movieId = 22'),(3,NULL,3,23,NULL,'2020/06/09 11:00:26','Add','Admin with adminID = 3 has added a movie with movieId = 23'),(4,NULL,3,23,NULL,'2020/06/09 11:49:58','Delete','Admin with adminID = 3 has added a deleted a movie with movieId = 23'),(5,NULL,2,NULL,10,'2020/06/12 01:22:52','Add','Admin with adminID = 2 has added an actor with actorId = 10'),(6,NULL,2,16,NULL,'2020/06/12 01:29:24','Add','Admin with adminID = 2 has added a movie with movieId = 16'),(7,NULL,2,18,NULL,'2020/06/12 05:17:00','Add','Admin with adminID = 2 has added a movie with movieId = 18'),(8,NULL,4,18,NULL,'2020/06/12 05:18:37','Add Rating','User: Omar with User ID = 1 has added rating for a movie with movieId = 18'),(9,NULL,4,18,NULL,'2020/06/12 05:18:37','Add Review','User: Omar with User ID = 1 has added review for a movie with movieId = 18'),(10,NULL,2,18,NULL,'2020/06/12 05:50:33','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(11,NULL,2,18,NULL,'2020/06/12 05:50:56','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(12,NULL,2,18,NULL,'2020/06/12 05:52:06','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(13,NULL,2,18,NULL,'2020/06/12 05:53:37','Update','Admin with adminID = 2 has updated a movie with movieId = 18'),(14,NULL,2,NULL,11,'2020/06/12 05:54:19','Add','Admin with adminID = 2 has added an actor with actorId = 11'),(15,NULL,4,16,NULL,'2020/06/12 05:56:49','Add Rating','User: Omar with User ID = 1 has added rating for a movie with movieId = 16'),(16,NULL,4,16,NULL,'2020/06/12 05:56:49','Add Review','User: Omar with User ID = 1 has added review for a movie with movieId = 16'),(17,NULL,4,16,NULL,'2020/06/12 06:01:55','Update','User: Omar with User ID = 1 has updated rating for a movie with movieId = 16'),(18,NULL,4,16,NULL,'2020/06/12 06:01:55','Update','User: Omar with User ID = 1 has updated review for a movie with movieId = 16');
/*!40000 ALTER TABLE `system_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrating`
--

DROP TABLE IF EXISTS `userrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrating` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MovieID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `UserRating` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MovieID` (`MovieID`),
  KEY `fk_useriduserreview` (`UserID`),
  CONSTRAINT `fk_useriduserreview_` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userrating__fk_1` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrating`
--

LOCK TABLES `userrating` WRITE;
/*!40000 ALTER TABLE `userrating` DISABLE KEYS */;
INSERT INTO `userrating` VALUES (3,3,1,9),(4,3,9,7),(6,3,10,7),(7,1,10,10),(8,1,1,9),(11,1,16,8),(12,18,1,9),(13,16,1,7);
/*!40000 ALTER TABLE `userrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userreview`
--

DROP TABLE IF EXISTS `userreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userreview` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MovieID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Review` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MovieID` (`MovieID`),
  KEY `fk_user_id` (`UserID`),
  CONSTRAINT `fk_user_id_` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userreview_ibfk_1_` FOREIGN KEY (`MovieID`) REFERENCES `movies` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreview`
--

LOCK TABLES `userreview` WRITE;
/*!40000 ALTER TABLE `userreview` DISABLE KEYS */;
INSERT INTO `userreview` VALUES (3,3,1,'Awesome!'),(4,3,9,'Good movie.'),(5,3,10,'Good.'),(6,1,10,'The best in the series!!!!'),(7,1,1,'The story and action are great.'),(8,1,16,':)'),(9,18,1,'Love it!'),(10,16,1,'GOOD MOVIE WATCH IT');
/*!40000 ALTER TABLE `userreview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
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

-- Dump completed on 2020-06-12 14:29:30
