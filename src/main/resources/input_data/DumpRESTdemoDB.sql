CREATE DATABASE  IF NOT EXISTS `rest_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rest_demo`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rest_demo
-- ------------------------------------------------------
-- Server version	5.6.10-log

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
-- Table structure for table `podcasts`
--

DROP TABLE IF EXISTS `podcasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `podcasts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(145) NOT NULL,
  `feed` varchar(145) NOT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(500) DEFAULT NULL,
  `link_on_podcastpedia` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `feed_UNIQUE` (`feed`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podcasts`
--

LOCK TABLES `podcasts` WRITE;
/*!40000 ALTER TABLE `podcasts` DISABLE KEYS */;
INSERT INTO `podcasts` VALUES (1,'Quarks & Co - zum Mitnehmen','http://podcast.wdr.de/quarks.xml','2014-01-09 20:21:10','Quarks & Co: Das Wissenschaftsmagazin','http://www.podcastpedia.org/podcasts/1/Quarks-Co-zum-Mitnehmen'),(2,'- The Naked Scientists Podcast - Stripping Down Science','http://www.thenakedscientists.com/naked_scientists_podcast.xml','2014-01-09 20:21:10','The Naked Scientists flagship science show brings you a lighthearted look at the latest scientific breakthroughs, interviews with the world top scientists, answers to your science questions and science experiments to try at home.','http://www.podcastpedia.org/podcasts/792/-The-Naked-Scientists-Podcast-Stripping-Down-Science'),(3,'NPR: TED Radio Hour Podcast','http://www.npr.org/rss/podcast.php?id=510298','2014-01-09 20:21:10','The TED Radio Hour is a journey through fascinating ideas: astonishing inventions, fresh approaches to old problems, new ways to think and create. Based on Talks given by riveting speakers on the world-renowned TED stage, each show is centered on a common theme - such as the source of happiness, crowd-sourcing innovation, power shifts, or inexplicable connections. The TED Radio Hour is hosted by Guy Raz, and is a co-production of NPR & TED. Follow the show @TEDRadioHour.','http://www.podcastpedia.org/podcasts/1183/NPR-TED-Radio-Hour-Podcast');
/*!40000 ALTER TABLE `podcasts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-09 20:21:54
