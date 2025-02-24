-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: caciokart
-- ------------------------------------------------------
-- Server version	9.2.0

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
  PRIMARY KEY (`socio`,`idProdotto`),
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
  CONSTRAINT `appartenenza_ibfk_1` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartenenza`
--

LOCK TABLES `appartenenza` WRITE;
/*!40000 ALTER TABLE `appartenenza` DISABLE KEYS */;
/*!40000 ALTER TABLE `appartenenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avviene`
--

DROP TABLE IF EXISTS `avviene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avviene` (
  `idIscr` varchar(5) NOT NULL,
  `idGara` varchar(5) NOT NULL,
  PRIMARY KEY (`idIscr`,`idGara`),
  KEY `idGara` (`idGara`),
  CONSTRAINT `avviene_ibfk_1` FOREIGN KEY (`idIscr`) REFERENCES `iscrizione` (`idIscr`),
  CONSTRAINT `avviene_ibfk_2` FOREIGN KEY (`idGara`) REFERENCES `garas` (`idGara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avviene`
--

LOCK TABLES `avviene` WRITE;
/*!40000 ALTER TABLE `avviene` DISABLE KEYS */;
/*!40000 ALTER TABLE `avviene` ENABLE KEYS */;
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
  PRIMARY KEY (`idCampionato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campionato`
--

LOCK TABLES `campionato` WRITE;
/*!40000 ALTER TABLE `campionato` DISABLE KEYS */;
/*!40000 ALTER TABLE `campionato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `circuito`
--

DROP TABLE IF EXISTS `circuito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `circuito` (
  `idCircuito` varchar(5) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `lunghezza` int DEFAULT NULL,
  `conformazione` int DEFAULT NULL,
  `immagine` blob,
  PRIMARY KEY (`idCircuito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `circuito`
--

LOCK TABLES `circuito` WRITE;
/*!40000 ALTER TABLE `circuito` DISABLE KEYS */;
/*!40000 ALTER TABLE `circuito` ENABLE KEYS */;
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
  `pos` int DEFAULT NULL,
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
  `dip` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`idProdotto`),
  KEY `dip` (`dip`),
  CONSTRAINT `concessionaria_ibfk_1` FOREIGN KEY (`dip`) REFERENCES `dipendente` (`dip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concessionaria`
--

LOCK TABLES `concessionaria` WRITE;
/*!40000 ALTER TABLE `concessionaria` DISABLE KEYS */;
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
  `passw` varchar(50) DEFAULT NULL,
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
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `effettua`
--

DROP TABLE IF EXISTS `effettua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `effettua` (
  `dip` varchar(16) NOT NULL,
  `idM` varchar(5) NOT NULL,
  PRIMARY KEY (`dip`,`idM`),
  KEY `idM` (`idM`),
  CONSTRAINT `effettua_ibfk_1` FOREIGN KEY (`idM`) REFERENCES `manutenzione` (`idM`),
  CONSTRAINT `effettua_ibfk_2` FOREIGN KEY (`dip`) REFERENCES `dipendente` (`dip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `effettua`
--

LOCK TABLES `effettua` WRITE;
/*!40000 ALTER TABLE `effettua` DISABLE KEYS */;
/*!40000 ALTER TABLE `effettua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eseguita`
--

DROP TABLE IF EXISTS `eseguita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eseguita` (
  `idM` varchar(5) NOT NULL,
  `targa` varchar(6) NOT NULL,
  PRIMARY KEY (`idM`,`targa`),
  KEY `targa` (`targa`),
  CONSTRAINT `eseguita_ibfk_1` FOREIGN KEY (`idM`) REFERENCES `manutenzione` (`idM`),
  CONSTRAINT `eseguita_ibfk_2` FOREIGN KEY (`targa`) REFERENCES `kart` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eseguita`
--

LOCK TABLES `eseguita` WRITE;
/*!40000 ALTER TABLE `eseguita` DISABLE KEYS */;
/*!40000 ALTER TABLE `eseguita` ENABLE KEYS */;
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
  `idC` varchar(5) DEFAULT NULL,
  `idP` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idGara`),
  KEY `idP` (`idP`),
  KEY `idC` (`idC`),
  CONSTRAINT `garal_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `prenotazione` (`idP`),
  CONSTRAINT `garal_ibfk_2` FOREIGN KEY (`idC`) REFERENCES `circuito` (`idCircuito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garal`
--

LOCK TABLES `garal` WRITE;
/*!40000 ALTER TABLE `garal` DISABLE KEYS */;
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
  `npart` int DEFAULT NULL,
  `btempo` time DEFAULT NULL,
  `idC` varchar(5) DEFAULT NULL,
  `idP` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idGara`),
  KEY `idP` (`idP`),
  KEY `idC` (`idC`),
  CONSTRAINT `garas_ibfk_1` FOREIGN KEY (`idP`) REFERENCES `prenotazione` (`idP`),
  CONSTRAINT `garas_ibfk_2` FOREIGN KEY (`idC`) REFERENCES `circuito` (`idCircuito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garas`
--

LOCK TABLES `garas` WRITE;
/*!40000 ALTER TABLE `garas` DISABLE KEYS */;
/*!40000 ALTER TABLE `garas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iscrizione`
--

DROP TABLE IF EXISTS `iscrizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iscrizione` (
  `idIscr` varchar(5) NOT NULL,
  `socio` varchar(16) DEFAULT NULL,
  `dataIsc` date DEFAULT NULL,
  `nomeT` varchar(50) DEFAULT NULL,
  `categoria` int DEFAULT NULL,
  `costo` double DEFAULT NULL,
  PRIMARY KEY (`idIscr`),
  KEY `socio` (`socio`),
  KEY `nomeT` (`nomeT`),
  CONSTRAINT `iscrizione_ibfk_1` FOREIGN KEY (`socio`) REFERENCES `socio` (`socio`),
  CONSTRAINT `iscrizione_ibfk_2` FOREIGN KEY (`nomeT`) REFERENCES `team` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iscrizione`
--

LOCK TABLES `iscrizione` WRITE;
/*!40000 ALTER TABLE `iscrizione` DISABLE KEYS */;
/*!40000 ALTER TABLE `iscrizione` ENABLE KEYS */;
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
  PRIMARY KEY (`idM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manutenzione`
--

LOCK TABLES `manutenzione` WRITE;
/*!40000 ALTER TABLE `manutenzione` DISABLE KEYS */;
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
  `socio` char(16) NOT NULL,
  PRIMARY KEY (`idP`,`socio`),
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
  `dataP` date DEFAULT NULL,
  `dataG` date DEFAULT NULL,
  `fasciaO` time DEFAULT NULL,
  `tipologia` varchar(6) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `numP` int DEFAULT NULL,
  PRIMARY KEY (`idP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazione`
--

LOCK TABLES `prenotazione` WRITE;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
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
  `passw` varchar(50) DEFAULT NULL,
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

-- Dump completed on 2025-02-24 17:44:45
