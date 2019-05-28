CREATE DATABASE  IF NOT EXISTS `turin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `turin`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: turin
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acompanantes_checking`
--

DROP TABLE IF EXISTS `acompanantes_checking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `acompanantes_checking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `id_checking` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acompanantes_checking`
--

LOCK TABLES `acompanantes_checking` WRITE;
/*!40000 ALTER TABLE `acompanantes_checking` DISABLE KEYS */;
INSERT INTO `acompanantes_checking` VALUES (27,9,66);
/*!40000 ALTER TABLE `acompanantes_checking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ckecking`
--

DROP TABLE IF EXISTS `ckecking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ckecking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `numero_personas` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `fecha_registro` date NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `estado` varchar(1) DEFAULT 'A',
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ckecking`
--

LOCK TABLES `ckecking` WRITE;
/*!40000 ALTER TABLE `ckecking` DISABLE KEYS */;
INSERT INTO `ckecking` VALUES (66,15,1,'2019-05-26','2019-05-30','2019-05-27','jinni','I',1),(67,34,1,'2019-05-27','2019-05-31','2019-05-28','jinni','A',1);
/*!40000 ALTER TABLE `ckecking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoDocumento` varchar(2) NOT NULL,
  `documento` varchar(30) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `fechaRegistro` date NOT NULL,
  `extranjero` varchar(1) NOT NULL DEFAULT 'N',
  `usuarioIngreso` varchar(45) DEFAULT NULL,
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documento_UNIQUE` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (3,'PP','15384787','JINNI GIOVANNI ARIAS ROMAN','33333','jigioarias@gmail.com','2018-06-03','2019-05-22','S','jinni',1),(4,'CC','15386565','camilo patiño','aasd','jigioarias@gmail.com','2019-03-15','2019-05-27','S','jinni',1),(5,'PP','15441106','BERNARDO DE JESUS ARIAS','33333','jigioarias@gmail.com','2017-12-25','2019-05-27','N','jinni',1),(6,'CE','3933939','AMPARO GRISALES','4949494','jigioarias@gmail.com','2018-06-30','2019-03-21','N',NULL,1),(7,'CC',' 233093030','CRISANTO VARGAS',' 222222','crisanto@gmail.com','2019-03-05','2019-05-27','N','jinni',1),(9,'PP','222DDD','LOLA CALIMIDADES','2222222','LOLA@GMAIL.COM','2019-02-12','2019-04-05','S',NULL,1),(10,'PP','404040','NUEVAYORK',' ',' ','2019-03-30','2019-03-30','N',NULL,1),(11,'PP','3404044004','PEDRO RIMALES',' ',' ','2019-03-30','2019-03-30','S',NULL,1),(12,'CC',' 404040404','YEYO',' ',' ','2019-03-30','2019-03-30','N',NULL,1),(13,'PP',' 43040401','PATRICIA TOÑA',' ',' ','2019-03-31','2019-03-31','S',NULL,1),(14,'PP','GGHGH','JINNAN',' 453453453','jigioarias@gmail.com','2019-03-29','2019-05-22','S','jinni',1),(15,'CC',' 1040032878','NATALI MONSALVE',' 2324242','SSS@GMSDS.COM','2019-03-30','2019-05-27','N','jinni',1),(16,'PP',' 44949','PARIS HILTON',' ',' ','2019-04-04','2019-04-04','S',NULL,1),(17,'PP','12340987','HUGO LEON','303033','JIGIOARIAS@GMAIL.COM','2019-05-03','2019-05-20','S','jinni',1),(18,'PP','NUEVO',' ASDAS','3333','jigioarias@gmail.com','2019-05-14','2019-05-27','N','jinni',1),(19,'CE',' 2221','DORIS LA MUECA',' ',' ','2019-05-18','2019-05-18','N',NULL,1),(20,'CE','33333349445','PEDRO NAVAJAS',' ',' ','2019-05-18','2019-05-18','S',NULL,1),(21,'CE','3424243','PACHO TUERCAS','33030303','fsdfs@gmail.com','2019-05-18','2019-05-27','N','jinni',1),(22,'PP','67676767','VLADIMIR HERNANDEZ','12233344','jigioarias@gmail.com',NULL,'2019-05-27','S','jinni',1),(23,'PP','223344','Aldo leao ramirez','1222333333','algo@gmail.com','2019-05-25','2019-05-27','S','jinni',1),(24,'PP','337755',' CEPELLINI',' ',' ','2019-05-27','2019-05-27','N',NULL,1),(25,'PP','238989','PALAVECINO','2020202020','PALA@GMAIL.COM','2019-05-16','2019-05-27','S','jinni',1),(26,'PP',' ASDA',' ASDA',' ',' ','2019-05-27','2019-05-27','N',NULL,1),(27,'CE','L3393939','LUZ ELENA ROMAN',' ',' ','2019-05-27','2019-05-27','S',NULL,1),(28,'CE','33030312S','NATALIA VERGARA',' ',' ','2019-05-27','2019-05-27','S',NULL,1),(29,'PP','C2239393','CANARIO','2223434343','CANARIO@FGGMG.XOM','2019-05-26','2019-05-27','S','jinni',1),(30,'PP',' X3339393','LAMICA',' ',' ','2019-05-27','2019-05-27','N',NULL,0),(31,'PP','330303HDHDH','CHECHO ACOSTA','3039393939','DDF@GMAIL.COM','2019-05-17','2019-05-27','S','jinni',1),(32,'CE',' ASDASD','ASDASD',' ',' ','2019-05-27','2019-05-27','S',NULL,1),(33,'PP',' SSS','ASASAADAD',' ',' ','2019-05-27','2019-05-27','S',NULL,1),(34,'PP','P4494949','Papo toribio','2333333333','papo@gmail.com',NULL,'2019-05-28','S','jinni',1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado` (
  `fistName` varchar(50) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('Ravi',1),('Amit',2),('Nitish',3);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `facturas` (
  `numero` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cliente` varchar(45) NOT NULL,
  `nit` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `razonSocial` varchar(100) NOT NULL,
  `resolucion` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `total` int(11) NOT NULL,
  `hotel` int(11) DEFAULT NULL,
  `direccionCliente` varchar(100) NOT NULL,
  `telefonoCliente` varchar(20) NOT NULL,
  `tipodocumentoCliente` varchar(2) NOT NULL,
  `documentoCliente` varchar(30) NOT NULL,
  `fechaEntrada` date DEFAULT NULL,
  `fechaSalida` date DEFAULT NULL,
  `checking` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (13,'2019-05-27','NATALI MONSALVE','1',' +57 (4) 5683199','TURIN','11','LA CEJA',153200,1,'SSS@GMSDS.COM',' 2324242','CC',' 1040032878','2019-05-26','2019-05-30',66);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `habitacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `estado` varchar(3) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (9,30000,1,'DIS','SIN TV Y CON DUCHAS','101',0),(10,350066,4,'DIS','CON TV Y DOS CAMAS NUEVO','102',0),(13,3666,20,'RPF','pruebas','301',0),(14,30000,1,'DIS','as','sda',0),(15,234234,1,'DIS','ASA','asdasd',0),(16,30000,1,'DIS','ASDA','ASDS',0),(17,3242,1,'DIS','ASDAD','ASDAS',0),(18,45000,2,'DIS','TIENE DE TODO','401',0),(19,444949,1,'DIS','ESTA TIENE BALCON','402',0),(20,344450,2,'DIS','QUEDA EN LA ESQUINA TIENE DOS NEVERAS','NUEVA PRUEBA',0),(21,42342,2,'DIS','NUEVA','445',0),(22,555555,8,'DIS','ADASDAD','HABITACION FULL EN LA ESQUINA',0);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitaciones_checking`
--

DROP TABLE IF EXISTS `habitaciones_checking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `habitaciones_checking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ckecking` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitaciones_checking`
--

LOCK TABLES `habitaciones_checking` WRITE;
/*!40000 ALTER TABLE `habitaciones_checking` DISABLE KEYS */;
INSERT INTO `habitaciones_checking` VALUES (58,66,9,1),(59,67,13,1);
/*!40000 ALTER TABLE `habitaciones_checking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hoteles` (
  `codigo` int(11) NOT NULL,
  `nit` varchar(45) NOT NULL,
  `nomgre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `redessociales` varchar(45) DEFAULT NULL,
  `latitud` int(11) DEFAULT NULL,
  `altitud` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoteles`
--

LOCK TABLES `hoteles` WRITE;
/*!40000 ALTER TABLE `hoteles` DISABLE KEYS */;
INSERT INTO `hoteles` VALUES (1,'1','TURIN','LA CEJA',' +57 (4) 5683199','jigioareas@fgf.com','www.hotelturinlaceja.com',0,0),(2,'333','CALIFORNIA','LA CEJA ','234230','JIGIO','DSDAS',0,0);
/*!40000 ALTER TABLE `hoteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos`
--

DROP TABLE IF EXISTS `insumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `insumos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `valor` int(11) NOT NULL,
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
INSERT INTO `insumos` VALUES (1,'TOALLA',1000,1),(2,'TV',30000,1),(3,'CONTROL',20000,1),(4,'NEVERA',44444,1),(5,'DOS CAMAS',333000,1),(6,'4 SABANAS',333000,1);
/*!40000 ALTER TABLE `insumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos_checking`
--

DROP TABLE IF EXISTS `insumos_checking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `insumos_checking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ckecking` int(11) NOT NULL,
  `id_insumo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos_checking`
--

LOCK TABLES `insumos_checking` WRITE;
/*!40000 ALTER TABLE `insumos_checking` DISABLE KEYS */;
/*!40000 ALTER TABLE `insumos_checking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumoshabitacion`
--

DROP TABLE IF EXISTS `insumoshabitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `insumoshabitacion` (
  `habitacion` int(11) NOT NULL,
  `insumo` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT '1',
  PRIMARY KEY (`habitacion`,`insumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumoshabitacion`
--

LOCK TABLES `insumoshabitacion` WRITE;
/*!40000 ALTER TABLE `insumoshabitacion` DISABLE KEYS */;
INSERT INTO `insumoshabitacion` VALUES (0,1,1),(0,2,1),(0,3,1),(9,6,1),(10,1,1),(10,3,1),(13,1,1),(13,2,1),(13,3,1),(13,4,1),(13,5,1),(13,6,1),(17,1,1),(17,2,1),(17,3,1),(18,1,1),(18,2,1),(18,3,1),(19,1,1),(19,2,1),(19,3,1),(19,4,1),(19,6,1),(20,1,1),(20,2,1),(20,3,1),(20,4,1),(20,5,1),(20,6,1),(21,4,1),(22,1,1),(22,2,1),(22,3,1),(22,4,1),(22,5,1),(22,6,1);
/*!40000 ALTER TABLE `insumoshabitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `parametros` (
  `id` int(11) NOT NULL,
  `nombreParametro` varchar(45) NOT NULL,
  `valor` varchar(100) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `valorMaximo` varchar(100) DEFAULT NULL,
  `valorMinimo` varchar(100) DEFAULT NULL,
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES (1,'resolucion','12','N','2000','1',1);
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservas` (
  `idreserva` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `numeroHabitaciones` int(11) NOT NULL,
  `numeroNinos` int(11) NOT NULL DEFAULT '0',
  `numeroAdultos` int(11) NOT NULL,
  `fechaEntrada` date NOT NULL,
  `fechaSalida` date NOT NULL,
  `activa` varchar(1) DEFAULT 'S',
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`idreserva`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'Jinni','jigio','3233',2,2,4,'2019-03-18','2019-03-18','N',1),(2,'sda','asda','asdas',1,1,0,'2019-03-20','2019-03-20','N',2),(3,'23232','asdas','asdas',1,0,1,'2019-03-21','2019-03-28','S',2),(4,'Nombre','jigioarias','djdjdjdjdjd',1,1,0,'2019-03-18','2019-03-18','S',1),(5,'jinni arias','ads','asdasd',2,0,0,'2019-03-19','2019-03-08','S',1),(6,'sada','<zxcz','zxczxcXCZ',1,1,0,'2019-03-18','2019-03-18','S',1),(7,'sd','asdq','asda',1,0,1,'2019-03-22','2019-03-25','S',2),(8,'ALBERTO','gamero','121212',1,0,1,'2019-05-14','2019-05-14','S',2),(9,'jinnito','jigioariassdasd@gmail.com','122222',1,1,1,'2019-05-03','2019-02-03','S',1),(10,'nataly','naty@gmail.com','122222',1,1,1,'2019-05-03','2019-02-03','S',1),(11,'nataly','naty@gmail.com','122222',1,1,1,'2019-05-03','2019-02-03','S',2),(12,'natealy','naty@gmail.com','122222',1,1,1,'2019-05-03','2019-02-03','S',2),(13,'checho','checho@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(14,'bibibian','bibiana@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(15,'gameoftrhones','games@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(16,'gameoftrhones','games@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(17,'gameoftrhones','games@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(18,'gameoftrhones','games@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1),(19,'gameoftrhones','games@gmail.com','122224244',1,0,2,'2019-06-03','2019-06-05','N',1);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `nombre` varchar(50) NOT NULL,
  `hotel` int(11) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN',0),('ASEO',0),('CLIENTE',0),('NUEVO',0),('PRUEBA',0),('REGISTRO',0);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `servicio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `valor` int(11) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Gaseosa',2300,'A',1),(2,'Mesa',23000,'A',1),(3,'EMPANADAS',3200,'A',1),(4,'SEXO',333333,'A',1),(5,'ingrese servicio',0,'A',1),(6,'ESTERA',22222,'A',1),(7,'EMPANADAS CHILENA',33333,'A',1),(8,'LAVANDERIA A MANO',202020202,'A',1),(10,'VOLADORES',22200,'A',1),(11,'CANECAS DE GUARO',222220,'A',1),(12,'LAVAVO',330,'A',1),(13,'MACHETES',20000,'A',1),(14,'CASTAÑO',303030,'A',1),(15,'WISKEY',44555,'A',1),(16,'FUEGO',3030303,'A',1),(17,'wer2er2r2',23333,'A',1);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_ckeking`
--

DROP TABLE IF EXISTS `servicios_ckeking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `servicios_ckeking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_checking` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_ckeking`
--

LOCK TABLES `servicios_ckeking` WRITE;
/*!40000 ALTER TABLE `servicios_ckeking` DISABLE KEYS */;
INSERT INTO `servicios_ckeking` VALUES (28,66,3,1,1);
/*!40000 ALTER TABLE `servicios_ckeking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `id` varchar(20) NOT NULL,
  `clave` varchar(60) NOT NULL,
  `correo` varchar(191) NOT NULL,
  `activado` varchar(1) NOT NULL DEFAULT 'S',
  `fechaCreacion` date NOT NULL,
  `rol` varchar(50) NOT NULL,
  `usuarioCreacion` varchar(45) NOT NULL,
  `hotel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('carolina','qweqw','asdas','N','2019-05-09','ASEO','jINNI',NULL),('FAFA','ASDAS','ASDA','S','2019-05-09','ASEO','jINNI',NULL),('jinni','asda','asda','S','2019-05-09','ADMIN','jINNI',NULL),('maria','ada','asda','S','2019-05-09','NUEVO','jINNI',NULL),('maria2','ada','asda','S','2019-05-09','NUEVO','jINNI',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-27 21:45:40
