DROP DATABASE pooparcialfinal;
CREATE DATABASE pooparcialfinal;
USE pooparcialfinal;

-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: pooparcialfinal
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `buscar_cliente` (`nombre`,`direccion`,`telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Fernando Pineda','Direccion test','73721332'),(2,'Cristiano Ronaldo','Portugal','123123'),(14,'asd','sadas','123');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `tarjeta_id` int NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`tarjeta_id`),
  KEY `fk_transaccion_tarjeta1_idx` (`tarjeta_id`),
  CONSTRAINT `fk_transaccion_tarjeta1` FOREIGN KEY (`tarjeta_id`) REFERENCES `tarjeta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (5,'2026-08-10',4,10.00,'test1'),(6,'2026-08-10',4,10.00,'test1'),(7,'2026-08-10',4,10.00,'test1'),(8,'2026-08-10',4,10.00,'test1'),(9,'2026-08-11',4,10.00,'test'),(10,'2026-08-12',4,10.00,'test');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilitador`
--

DROP TABLE IF EXISTS `facilitador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilitador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilitador`
--

LOCK TABLES `facilitador` WRITE;
/*!40000 ALTER TABLE `facilitador` DISABLE KEYS */;
INSERT INTO `facilitador` VALUES (1,'Visa'),(2,'MasterCard'),(3,'AMEX');
/*!40000 ALTER TABLE `facilitador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(18) NOT NULL,
  `fechaVencimiento` date NOT NULL,
  `codigo` varchar(4) NOT NULL,
  `facilitador_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  `tipo_tarjeta` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`,`facilitador_id`,`cliente_id`),
  KEY `fk_tarjeta_facilitador_idx` (`facilitador_id`),
  KEY `fk_tarjeta_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_tarjeta_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_tarjeta_facilitador` FOREIGN KEY (`facilitador_id`) REFERENCES `facilitador` (`id`),
  CONSTRAINT `tarjeta_chk_1` CHECK ((`tipo_tarjeta` in (_utf8mb4'Debito',_utf8mb4'Credito')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
INSERT INTO `tarjeta` VALUES (4,'1234567812345678','2029-07-01','181',1,2,'Debito'),(5,'8765432187654321','2029-09-01','181',1,2,'Credito');
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10  3:24:26
