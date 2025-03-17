-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: caciokart
-- ------------------------------------------------------
-- Server version	8.0.40

DROP DATABASE IF EXISTS caciokart;
CREATE DATABASE caciokart;
USE caciokart;


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
-- Table structure for table `acquista`
--

DROP TABLE IF EXISTS `acquista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acquista` (
  `socio` varchar(16) NOT NULL,
  `idProdotto` varchar(5) NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`socio`,`idProdotto`,`data`),
  KEY `idProdotto` (`idProdotto`),
  CONSTRAINT `acquista_ibfk_1` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`),
  CONSTRAINT `acquista_ibfk_2` FOREIGN KEY (`idProdotto`) REFERENCES `concessionaria` (`idProdotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acquista`
--

LOCK TABLES `acquista` WRITE;
/*!40000 ALTER TABLE `acquista` DISABLE KEYS */;
INSERT INTO `acquista` VALUES ('Andrea','5','2025-03-14'),('Andrea','6','2025-03-14'),('Andrea','9','2025-03-14');
/*!40000 ALTER TABLE `acquista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appartenenza`
--

DROP TABLE IF EXISTS `appartenenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appartenenza` (
  `socio` varchar(16) NOT NULL,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`socio`,`nome`),
  KEY `nome_idx` (`nome`),
  CONSTRAINT `appartenenza_ibfk_1` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`),
  CONSTRAINT `nome` FOREIGN KEY (`nome`) REFERENCES `team` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartenenza`
--

LOCK TABLES `appartenenza` WRITE;
/*!40000 ALTER TABLE `appartenenza` DISABLE KEYS */;
INSERT INTO `appartenenza` VALUES ('1111111111111111','CacioTeam'),('2222222222222222','CacioTeam'),('Andrea','DreamTeam'),('Davide','DreamTeam');
/*!40000 ALTER TABLE `appartenenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campionato`
--

DROP TABLE IF EXISTS `campionato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campionato` (
  `idCampionato` varchar(5) NOT NULL,
  `anno` year DEFAULT NULL,
  `cilindrata` int DEFAULT NULL,
  PRIMARY KEY (`idCampionato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campionato`
--

LOCK TABLES `campionato` WRITE;
/*!40000 ALTER TABLE `campionato` DISABLE KEYS */;
INSERT INTO `campionato` VALUES ('C001',2015,125),('C002',2016,125),('C003',2017,125),('C004',2018,125),('C005',2019,50),('C006',2020,150),('C007',2021,125),('C008',2022,150),('C009',2023,150),('C010',2024,50);
/*!40000 ALTER TABLE `campionato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classifica`
--

DROP TABLE IF EXISTS `classifica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classifica` (
  `idGara` varchar(5) NOT NULL,
  `socio` varchar(16) NOT NULL,
  `targa` varchar(6) NOT NULL,
  `bGiro` time DEFAULT NULL,
  `tempTot` time DEFAULT NULL,
  PRIMARY KEY (`idGara`,`socio`,`targa`),
  KEY `targa` (`targa`),
  KEY `socio` (`socio`),
  CONSTRAINT `classifica_ibfk_1` FOREIGN KEY (`targa`) REFERENCES `kart` (`targa`),
  CONSTRAINT `classifica_ibfk_2` FOREIGN KEY (`idGara`) REFERENCES `garas` (`idGara`),
  CONSTRAINT `classifica_ibfk_3` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classifica`
--

LOCK TABLES `classifica` WRITE;
/*!40000 ALTER TABLE `classifica` DISABLE KEYS */;
INSERT INTO `classifica` VALUES ('G0001','Adriano','KRT222','00:01:10','00:11:00'),('G0001','Andrea','KRT000','00:01:05','00:10:30'),('G0001','Davide','KRT111','00:01:02','00:10:15'),('G0002','Alessandro','KRT234','00:01:06','00:10:35'),('G0002','Luca','KRT228','00:01:08','00:10:45'),('G0003','Adriano','KRT323','00:01:09','00:10:55'),('G0003','Andrea','KRT267','00:01:03','00:10:20'),('G0003','Davide','KRT292','00:01:07','00:10:40'),('G0004','Alessandro','KRT347','00:01:02','00:10:10'),('G0004','Luca','KRT333','00:01:04','00:10:25'),('G0005','Andrea','KRT432','00:01:05','00:10:30'),('G0005','Davide','KRT435','00:01:06','00:10:35'),('G0006','Adriano','KRT444','00:01:07','00:10:40'),('G0006','Luca','KRT555','00:01:08','00:10:45'),('G0007','Alessandro','KRT577','00:01:09','00:10:50'),('G0007','Andrea','KRT633','00:01:10','00:11:00'),('G0008','Adriano','KRT666','00:01:03','00:10:18'),('G0008','Davide','KRT654','00:01:02','00:10:12'),('G0009','Alessandro','KRT920','00:01:05','00:10:30'),('G0009','Luca','KRT675','00:01:04','00:10:24'),('G0010','Andrea','KRT347','00:01:06','00:10:36'),('G0010','Davide','KRT435','00:01:07','00:10:42'),('G0011','Adriano','KRT323','00:01:08','00:10:48'),('G0011','Luca','KRT675','00:01:09','00:10:54'),('G0012','Alessandro','KRT920','00:01:10','00:11:00'),('G0012','Andrea','KRT920','00:01:02','00:10:14'),('G0013','Adriano','KRT292','00:01:04','00:10:22'),('G0013','Davide','KRT292','00:01:03','00:10:18'),('G0014','Alessandro','KRT222','00:01:06','00:10:34'),('G0014','Luca','KRT111','00:01:05','00:10:28'),('G0015','Andrea','KRT555','00:01:07','00:10:40'),('G0015','Davide','KRT435','00:01:08','00:10:46'),('G0016','Adriano','KRT555','00:01:09','00:10:52'),('G0016','Luca','KRT292','00:01:10','00:10:58'),('G0017','Alessandro','KRT435','00:01:02','00:10:14'),('G0017','Andrea','KRT435','00:01:03','00:10:18'),('G0018','Adriano','KRT111','00:01:05','00:10:26'),('G0018','Davide','KRT228','00:01:04','00:10:22'),('G0019','Alessandro','KRT347','00:01:07','00:10:34'),('G0019','Luca','KRT000','00:01:06','00:10:30'),('G0020','Adriano','KRT228','00:01:10','00:10:46'),('G0020','Alessandro','KRT000','00:01:03','00:10:18'),('G0020','Andrea','KRT347','00:01:08','00:10:38'),('G0020','Davide','KRT347','00:01:09','00:10:42'),('G0020','Luca','KRT111','00:01:02','00:10:14');
/*!40000 ALTER TABLE `classifica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concessionaria`
--

DROP TABLE IF EXISTS `concessionaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `concessionaria` (
  `idProdotto` varchar(5) NOT NULL,
  `tipol` varchar(100) DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  `prezzo` double DEFAULT NULL,
  PRIMARY KEY (`idProdotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concessionaria`
--

LOCK TABLES `concessionaria` WRITE;
/*!40000 ALTER TABLE `concessionaria` DISABLE KEYS */;
INSERT INTO `concessionaria` VALUES ('10','Corona',150,25),('101','KRTLUC',1,2500),('11','Disco_freno_anteriore',9,30),('12','disco_freno_posteriore',30,80),('13','pastiglie freno',50,30),('14','ruota',12,110),('15','Volante',5,140),('16','Volante2',7,170),('2','KRT577',1,6500),('3','KRT111',10,199.99),('4','KRT633',1,4500),('5','KRT444',12,459.25),('6','KRT555',8,150),('7','Radiatore',2,200),('8','Motore_125cc',9,1500),('9','Carburatore_28',12,170);
/*!40000 ALTER TABLE `concessionaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendente` (
  `dip` varchar(16) NOT NULL,
  `nome` varchar(20) DEFAULT NULL,
  `cognome` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `passw` varchar(100) DEFAULT NULL,
  `dataN` date DEFAULT NULL,
  `ruolo` varchar(30) DEFAULT NULL,
  `oreL` time DEFAULT NULL,
  `stipendio` double DEFAULT NULL,
  PRIMARY KEY (`dip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES ('admin1','Mario','Rossi','mario.rossi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1980-01-15','meccanico','00:00:40',2500),('admin2','Giulia','Bianchi','giulia.bianchi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1985-06-20','gestore','00:00:39',3200.75),('admin3','Luca','Verdi','luca.verdi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1990-03-10','arbitro','00:00:35',2700.5),('admin4','Sara','Neri','sara.neri@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1982-09-25','organizzatore','00:00:40',2900),('admin5','Paolo','Gialli','paolo.gialli@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1978-12-05','proprietario','00:00:40',4000);
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garal`
--

DROP TABLE IF EXISTS `garal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garal` (
  `idGara` varchar(5) NOT NULL,
  `ora` time DEFAULT NULL,
  `idP` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idGara`),
  KEY `idP` (`idP`),
  CONSTRAINT `garal_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `prenotazione` (`idP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garal`
--

LOCK TABLES `garal` WRITE;
/*!40000 ALTER TABLE `garal` DISABLE KEYS */;
INSERT INTO `garal` VALUES ('G100','10:30:00','1'),('G101','12:00:00','10'),('G102','14:15:00','13'),('G103','16:45:00','14'),('G104','18:00:00','15'),('G105','09:30:00','16'),('G106','11:00:00','3'),('G107','13:20:00','4'),('G108','15:10:00','8'),('G109','17:50:00','9');
/*!40000 ALTER TABLE `garal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garas`
--

DROP TABLE IF EXISTS `garas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garas` (
  `idGara` varchar(5) NOT NULL,
  `ora` time DEFAULT NULL,
  `btempo` time DEFAULT NULL,
  `idP` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idGara`),
  KEY `idP` (`idP`),
  CONSTRAINT `garas_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `prenotazione` (`idP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garas`
--

LOCK TABLES `garas` WRITE;
/*!40000 ALTER TABLE `garas` DISABLE KEYS */;
INSERT INTO `garas` VALUES ('G0001','10:00:00','00:01:02','11'),('G0002','10:30:00','00:01:06','12'),('G0003','11:00:00','00:01:03','2'),('G0004','11:30:00','00:01:02','5'),('G0005','12:00:00','00:01:05','6'),('G0006','12:30:00','00:01:07','7'),('G0007','13:00:00','00:01:09','20'),('G0008','13:30:00','00:01:02','21'),('G0009','14:00:00','00:01:04','22'),('G0010','14:30:00','00:01:06','23'),('G0011','15:00:00','00:01:08','24'),('G0012','15:30:00','00:01:02','25'),('G0013','16:00:00','00:01:03','26'),('G0014','16:30:00','00:01:05','27'),('G0015','17:00:00','00:01:07','28'),('G0016','17:30:00','00:01:09','29'),('G0017','18:00:00','00:01:02','30'),('G0018','18:30:00','00:01:04','31'),('G0019','19:00:00','00:01:06','32'),('G0020','19:30:00','00:01:08','33');
/*!40000 ALTER TABLE `garas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kart`
--

DROP TABLE IF EXISTS `kart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kart` (
  `targa` varchar(6) NOT NULL,
  `cilindrata` int DEFAULT NULL,
  `serbatoio` double DEFAULT NULL,
  PRIMARY KEY (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kart`
--

LOCK TABLES `kart` WRITE;
/*!40000 ALTER TABLE `kart` DISABLE KEYS */;
INSERT INTO `kart` VALUES ('KRT000',125,10),('KRT111',150,10),('KRT222',150,10),('KRT228',50,20),('KRT234',150,20),('KRT267',125,20),('KRT292',150,20),('KRT323',125,20),('KRT333',900,20),('KRT347',150,20),('KRT432',50,20),('KRT435',125,20),('KRT444',500,10),('KRT555',125,20),('KRT577',150,20),('KRT633',125,20),('KRT654',125,20),('KRT666',125,20),('KRT675',125,20),('KRT920',50,20),('KRTCON',150,20),('KRTLUC',50,20),('KRTNOL',150,20),('KRTSOC',125,20);
/*!40000 ALTER TABLE `kart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manutenzione`
--

DROP TABLE IF EXISTS `manutenzione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manutenzione` (
  `idM` varchar(5) NOT NULL,
  `tipoInt` varchar(1000) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `dataM` date DEFAULT NULL,
  `targa` varchar(6) NOT NULL,
  PRIMARY KEY (`idM`),
  KEY `targa_idx` (`targa`),
  CONSTRAINT `targa` FOREIGN KEY (`targa`) REFERENCES `kart` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manutenzione`
--

LOCK TABLES `manutenzione` WRITE;
/*!40000 ALTER TABLE `manutenzione` DISABLE KEYS */;
INSERT INTO `manutenzione` VALUES ('1','Esplosione alla ruota sinistra',80,'2025-03-12','KRT267'),('10','Sostituzione olio motore',60,'2025-03-07','KRT292'),('11','Riparazione carburatore',130,'2025-03-08','KRT323'),('12','Controllo sistema elettrico',75,'2025-03-09','KRT333'),('13','Sostituzione pastiglie freno',85,'2025-03-10','KRT347'),('14','Controllo trasmissione',95,'2025-03-11','KRT432'),('15','Riparazione sistema di scarico',140,'2025-03-12','KRT435'),('16','Sostituzione filtri aria',50,'2025-03-13','KRT444'),('17','Verifica batteria',45,'2025-03-14','KRT555'),('18','Sostituzione frizione',120,'2025-03-15','KRT577'),('19','Controllo luci e indicatori',40,'2025-03-16','KRT633'),('2','motore fuso',700,'2025-03-12','KRT000'),('20','Sostituzione gomme posteriori',85,'2025-03-17','KRT654'),('21','Verifica olio freni',70,'2025-03-18','KRT666'),('22','Riparazione sospensioni',150,'2025-03-19','KRT675'),('23','Controllo motore',110,'2025-03-20','KRT920'),('24','Sostituzione cinghia di distribuzione',130,'2025-03-21','KRT000'),('25','Sostituzione pastiglie freno',90,'2025-03-22','KRT111'),('26','Controllo sistema di raffreddamento',100,'2025-03-23','KRT222'),('27','Riparazione carburatore',125,'2025-03-24','KRT228'),('28','Sostituzione gomme anteriori',80,'2025-03-25','KRT234'),('29','Sostituzione freni posteriori',95,'2025-03-26','KRT267'),('3','Rottura asse anteriore',2000,'2025-03-12','KRT432'),('30','Controllo trasmissione',90,'2025-03-27','KRT292'),('31','Riparazione sistema di scarico',110,'2025-03-28','KRT323'),('32','Verifica batteria',45,'2025-03-29','KRT333'),('33','Sostituzione filtri aria',50,'2025-03-30','KRT347'),('34','Riparazione sospensioni',150,'2025-03-31','KRT432'),('35','Controllo motore e carburatore',140,'2025-04-01','KRT435'),('36','Controllo luci e indicatori',40,'2025-04-02','KRT444'),('37','Sostituzione frizione',120,'2025-04-03','KRT555'),('38','Controllo sistema elettrico',75,'2025-04-04','KRT577'),('39','Sostituzione gomme posteriori',85,'2025-04-05','KRT633'),('4','Sostituzione gomme anteriori',80,'2025-03-01','KRT000'),('40','Verifica olio freni',70,'2025-04-06','KRT654'),('41','Sostituzione pastiglie freno',90,'2025-04-07','KRT666'),('42','Riparazione sistema di scarico',140,'2025-04-08','KRT675'),('43','Controllo sospensioni',100,'2025-04-09','KRT920'),('44','Sostituzione cinghia di distribuzione',110,'2025-04-10','KRT000'),('45','Sostituzione gomme anteriori',80,'2025-04-11','KRT111'),('46','Riparazione carburatore',130,'2025-04-12','KRT222'),('47','Controllo trasmissione',90,'2025-04-13','KRT228'),('48','Controllo luci e indicatori',40,'2025-04-14','KRT267'),('49','Sostituzione freni posteriori',95,'2025-04-15','KRT292'),('5','Controllo motore e carburatore',150,'2025-03-02','KRT111'),('50','Verifica batteria',45,'2025-04-16','KRT323'),('6','Sostituzione freni posteriori',90,'2025-03-03','KRT222'),('7','Riparazione sistema di raffreddamento',120,'2025-03-04','KRT228'),('8','Sostituzione cinghia di distribuzione',110,'2025-03-05','KRT234'),('9','Controllo sospensioni',100,'2025-03-06','KRT267');
/*!40000 ALTER TABLE `manutenzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partecipa`
--

DROP TABLE IF EXISTS `partecipa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partecipa` (
  `idGara` varchar(5) DEFAULT NULL,
  `idCampionato` varchar(5) DEFAULT NULL,
  KEY `idGara` (`idGara`),
  KEY `idCampionato` (`idCampionato`),
  CONSTRAINT `partecipa_ibfk_1` FOREIGN KEY (`idGara`) REFERENCES `garas` (`idGara`),
  CONSTRAINT `partecipa_ibfk_2` FOREIGN KEY (`idCampionato`) REFERENCES `campionato` (`idCampionato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partecipa`
--

LOCK TABLES `partecipa` WRITE;
/*!40000 ALTER TABLE `partecipa` DISABLE KEYS */;
INSERT INTO `partecipa` VALUES ('G0001','C001'),('G0002','C002'),('G0003','C003'),('G0004','C004'),('G0005','C005'),('G0006','C006'),('G0007','C007'),('G0011','C001'),('G0012','C002'),('G0020','C001'),('G0019','C001');
/*!40000 ALTER TABLE `partecipa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenota`
--

DROP TABLE IF EXISTS `prenota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenota` (
  `idP` varchar(5) NOT NULL,
  `socio` char(16) DEFAULT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`idP`,`data`),
  KEY `socio` (`socio`),
  CONSTRAINT `prenota_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `prenotazione` (`idP`),
  CONSTRAINT `prenota_ibfk_2` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenota`
--

LOCK TABLES `prenota` WRITE;
/*!40000 ALTER TABLE `prenota` DISABLE KEYS */;
INSERT INTO `prenota` VALUES ('21','Adriano','2025-03-18'),('23','Adriano','2025-03-22'),('25','Adriano','2025-03-26'),('27','Adriano','2025-03-30'),('30','Adriano','2025-04-03'),('21','Alessandro','2025-03-19'),('23','Alessandro','2025-03-23'),('25','Alessandro','2025-03-27'),('27','Alessandro','2025-03-31'),('30','Alessandro','2025-04-04'),('1','Andrea','2025-03-12'),('10','Andrea','2025-03-14'),('14','Andrea','2025-03-14'),('4','Andrea','2025-03-12'),('9','Andrea','2025-03-14'),('20','Davide','2025-03-17'),('22','Davide','2025-03-21'),('24','Davide','2025-03-25'),('26','Davide','2025-03-29'),('29','Davide','2025-04-02'),('20','Luca','2025-03-16'),('22','Luca','2025-03-20'),('24','Luca','2025-03-24'),('26','Luca','2025-03-28'),('28','Luca','2025-04-01');
/*!40000 ALTER TABLE `prenota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazione`
--

DROP TABLE IF EXISTS `prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotazione` (
  `idP` varchar(5) NOT NULL,
  `dataG` date DEFAULT NULL,
  `fasciaO` time DEFAULT NULL,
  `tipologia` varchar(6) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  PRIMARY KEY (`idP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazione`
--

LOCK TABLES `prenotazione` WRITE;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
INSERT INTO `prenotazione` VALUES ('1','2025-05-12','11:00:00','libera',15),('10','2025-09-18','22:00:00','libera',15),('11','2026-12-24','22:00:00','secca',15),('12','2027-12-09','00:00:00','secca',15),('13','3033-02-09','19:00:00','libera',15),('14','2098-02-03','22:00:00','libera',15),('15','3033-03-03','22:00:00','libera',15),('16','2026-03-03','22:00:00','libera',15),('2','2025-05-04','22:00:00','secca',15),('20','2025-03-16','13:00:00','secca',60),('21','2025-02-16','13:30:00','secca',60),('22','2025-01-16','14:00:00','secca',60),('23','2024-06-16','14:30:00','secca',60),('24','2024-07-16','15:00:00','secca',60),('25','2023-08-16','15:30:00','secca',60),('26','2023-03-16','16:00:00','secca',60),('27','2022-03-16','16:30:00','secca',60),('28','2021-03-16','17:00:00','secca',60),('29','2020-03-16','17:30:00','secca',60),('3','2025-07-12','19:00:00','libera',15),('30','2024-05-16','18:00:00','secca',60),('31','2024-06-16','18:30:00','secca',60),('32','2024-07-16','19:00:00','secca',60),('33','2024-08-16','19:30:00','secca',60),('4','2025-07-30','19:00:00','libera',15),('5','2025-06-23','22:00:00','secca',15),('6','2025-12-03','19:00:00','secca',15),('7','2025-05-21','22:00:00','secca',15),('8','2025-06-21','19:00:00','libera',15),('9','2026-02-28','22:00:00','libera',15);
/*!40000 ALTER TABLE `prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `socio` varchar(16) NOT NULL,
  `nome` varchar(20) DEFAULT NULL,
  `cognome` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `passw` varchar(100) DEFAULT NULL,
  `dataN` date DEFAULT NULL,
  `targa` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`socio`),
  KEY `targa` (`targa`),
  CONSTRAINT `socio_ibfk_1` FOREIGN KEY (`targa`) REFERENCES `kart` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES ('1111111111111111','Davide','Albani','ciao@gmail.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','2025-02-27',NULL),('2222222222222222','Davide','Albani','videogdavide@gmail.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','2025-02-16',NULL),('Adriano','Bacicchi','Gialli','paolo.gialli@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1978-12-05',NULL),('Alessandro','Piacentini','Verdi','luca.verdi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1990-03-10',NULL),('Andrea','Andrea','Renelli','mario.rossi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1980-01-15','KRT444'),('Davide','Albani','Neri','sara.neri@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1982-09-25',NULL),('Luca','Tripodi','Bianchi','giulia.bianchi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1985-06-20',NULL),('S0001','Mario','Rossi','mario.rossi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1990-01-01','T001'),('S0002','Luca','Bianchi','luca.bianchi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1992-02-02','T002'),('S0003','Anna','Verdi','anna.verdi@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1988-03-03','T003'),('S0004','Paolo','Neri','paolo.neri@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1985-04-04','T004'),('S0005','Elisa','Gallo','elisa.gallo@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1993-05-05','T005'),('S0006','Marco','Ferrari','marco.ferrari@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1987-06-06','T006'),('S0007','Sara','Romano','sara.romano@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1991-07-07','T007'),('S0008','Giovanni','Moretti','giovanni.moretti@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1994-08-08','T008'),('S0009','Valentina','Rizzo','valentina.rizzo@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1995-09-09','T009'),('S0010','Antonio','Costa','antonio.costa@example.com','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1986-10-10','T010');
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `nome` varchar(50) NOT NULL,
  `colore` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('CacioTeam','#db0f0f'),('DreamTeam','#badb14');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-16 20:36:05
