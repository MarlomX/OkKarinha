CREATE DATABASE  IF NOT EXISTS `mecanica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mecanica`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mecanica
-- ------------------------------------------------------
-- Server version	8.0.36

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
  `CPF` varchar(12) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Telefone` varchar(15) DEFAULT NULL,
  `Email` varchar(320) DEFAULT NULL,
  PRIMARY KEY (`CPF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('33320359593','Fernando','51 988982030','Emailteste@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Quant_Estoque` int NOT NULL,
  `Preco_Venda` decimal(10,2) NOT NULL,
  `Preco_Compra` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Corrente',20,8.50,3.25);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relacao_servico_produto`
--

DROP TABLE IF EXISTS `relacao_servico_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relacao_servico_produto` (
  `Servico_ID` int NOT NULL,
  `Produto_ID` int NOT NULL,
  `Quant` int NOT NULL,
  KEY `Servico_ID_idx` (`Servico_ID`),
  KEY `Produto_ID_idx` (`Produto_ID`),
  CONSTRAINT `Produto_ID` FOREIGN KEY (`Produto_ID`) REFERENCES `produto` (`ID`),
  CONSTRAINT `Servico_Produto_ID` FOREIGN KEY (`Servico_ID`) REFERENCES `servico` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relacao_servico_produto`
--

LOCK TABLES `relacao_servico_produto` WRITE;
/*!40000 ALTER TABLE `relacao_servico_produto` DISABLE KEYS */;
INSERT INTO `relacao_servico_produto` VALUES (1,1,5);
/*!40000 ALTER TABLE `relacao_servico_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparo_de_moto`
--

DROP TABLE IF EXISTS `reparo_de_moto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reparo_de_moto` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Servico_ID` int NOT NULL,
  `Preco` decimal(10,2) NOT NULL,
  `Placa` varchar(10) NOT NULL,
  `Descricao` varchar(500) NOT NULL,
  `Mecanico` varchar(50) DEFAULT NULL,
  `Modelo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Servico_ID_idx` (`Servico_ID`),
  CONSTRAINT `Servico_Reparo_ID` FOREIGN KEY (`Servico_ID`) REFERENCES `servico` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparo_de_moto`
--

LOCK TABLES `reparo_de_moto` WRITE;
/*!40000 ALTER TABLE `reparo_de_moto` DISABLE KEYS */;
INSERT INTO `reparo_de_moto` VALUES (1,1,180.80,'ABC850','Trocou o oleo da moto','Jorge Lucas','Café Racer');
/*!40000 ALTER TABLE `reparo_de_moto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Cliente_CPF` varchar(50) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Cliente_CPF_idx` (`Cliente_CPF`),
  CONSTRAINT `Cliente_CPF` FOREIGN KEY (`Cliente_CPF`) REFERENCES `cliente` (`CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,'33320359593','2024-03-26');
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-26 18:07:24
