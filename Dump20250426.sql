-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: emotracker
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `ip_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `writer` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'이 게시글 너무 좋아요!!!!!!!!!!!!! 징차!!!!!!!!!!','2025-04-19 06:31:15.294963','0:0:0:0:0:0:0:1','익명냥이',NULL,1),(2,'오 좋은 글이에요!!!!','2025-04-19 06:44:11.063498','0:0:0:0:0:0:0:1','익명돌고래',1,1),(4,'나도!222','2025-04-20 05:46:13.858676','0:0:0:0:0:0:0:1','ㅇㅇ',1,1),(5,'나나도!333','2025-04-20 05:46:23.582060','0:0:0:0:0:0:0:1','ㅇㅇㅇ',4,1),(8,'댓글 잘 나오나요??','2025-04-22 21:51:36.504644','0:0:0:0:0:0:0:1','ㅇㅇ',NULL,3),(9,'아이피 왜 안나오죠?','2025-04-22 21:51:55.529184','0:0:0:0:0:0:0:1','ㅇㅇㅇ',8,3),(12,'오오','2025-04-25 06:25:20.986749','0:0:0:0:0:0:0:1','오오',NULL,12),(13,'오오오','2025-04-25 06:25:28.276706','0:0:0:0:0:0:0:1','오오오',12,12);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emotion_entry`
--

DROP TABLE IF EXISTS `emotion_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emotion_entry` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `emotion` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotion_entry`
--

LOCK TABLES `emotion_entry` WRITE;
/*!40000 ALTER TABLE `emotion_entry` DISABLE KEYS */;
INSERT INTO `emotion_entry` VALUES (1,'2025-04-21 05:53:22.541151','2025-04-20','?','좋아!'),(9,'2025-04-21 06:53:21.772662','2025-04-21','?','감격!! 진짜로!!'),(10,'2025-04-21 10:12:07.505035','2025-04-19','?','화가 남!'),(11,'2025-04-21 10:17:41.375737','2025-04-18','?','됨?'),(12,'2025-04-21 10:20:33.649551','2025-04-17','?','갬덩!'),(13,'2025-04-21 10:20:59.751593','2025-04-16','❤️','사랑이다!'),(14,'2025-04-22 19:58:26.645115','2025-04-15','❤️','ㅇㅇ');
/*!40000 ALTER TABLE `emotion_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emotion_record`
--

DROP TABLE IF EXISTS `emotion_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emotion_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `emotion_type` enum('ANGRY','EMPTY','FAILURE','GRATEFUL','HAPPY','LOVE','RELAXED','SAD','SUCCESS','TIRED') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfiu1739sjg5r0sbh7ti7l8ocl` (`user_id`),
  CONSTRAINT `FKfiu1739sjg5r0sbh7ti7l8ocl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotion_record`
--

LOCK TABLES `emotion_record` WRITE;
/*!40000 ALTER TABLE `emotion_record` DISABLE KEYS */;
INSERT INTO `emotion_record` VALUES (1,'2025-04-14 08:35:57.926113','2025-04-14','HAPPY','https://example.com/happy.jpg','오늘은 정말 기분이 좋아요!','2025-04-14 08:35:57.926113',1);
/*!40000 ALTER TABLE `emotion_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `ip_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,NULL,'이건 테스트 글이에요.',NULL,'0:0:0:0:0:0:0:1','첫 글입니다!',NULL),(3,'익명햄스터00','테스트 내용00',NULL,'0:0:0:0:0:0:0:1','테스트 제목00','db738cf8-7e1f-4510-88eb-4f4edc306a09_empty.png'),(11,'ㅇㅇ','ㅇㅇ','2025-04-25 05:45:16.370248','0:0:0:0:0:0:0:1','ㅇㅇ',NULL),(12,'test','test','2025-04-25 06:25:13.914863','0:0:0:0:0:0:0:1','test','ff0945a0-051c-4f5d-8953-1837b904ff10_드로잉.jpg');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1234','testuser',NULL,'','',NULL,NULL),(2,'$2a$10$cYv3qAK0gpMHwhOSUjHfxOWdse4YrFgpxhatDUsm9sRClbVwCkyI2','testuser01',NULL,'testuser@example.com','testuser',NULL,NULL),(3,'admin1234','admin',NULL,'admin@example.com','admin','admin',NULL),(4,'$2a$10$zuemscm05BL1A9AuU3ZaneLDPdzhz/jT0PfWRv.NUBFJ/7TotTUBm','test123',NULL,'test123@test.com','test123','USER',NULL),(5,'$2a$10$8A.48W3rK7cHfiQcMmerQu6IE6ecACT7QsxEdCHLm4Q0zKhGBTBR6','test12',NULL,'test12@test.com','test12','USER',NULL),(6,'$2a$10$52QvUebZ8fhsJEWUrg5Kd.v6K0AqcGAsOFSscxK.hnMrUvtNED9ES','test1234',NULL,'test1234@test.com','test1234','USER',NULL),(7,'$2a$10$EMLjX/ja6jsHBqukyVj5EeQqYP09/nDOHCDHJyeONJPsneCwjtUjK','test12345',NULL,'test12345@test.com','test12345','USER',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-26  6:46:33
