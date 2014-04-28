-- MySQL dump 10.13  Distrib 5.6.12, for Win32 (x86)
--
-- Host: localhost    Database: spinncast
-- ------------------------------------------------------
-- Server version	5.6.12-log

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
-- Table structure for table `chem_master`
--

DROP TABLE IF EXISTS `chem_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chem_master` (
  `chem_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chem_component` varchar(45) NOT NULL,
  PRIMARY KEY (`chem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chem_master`
--

LOCK TABLES `chem_master` WRITE;
/*!40000 ALTER TABLE `chem_master` DISABLE KEYS */;
INSERT INTO `chem_master` VALUES (1,'cu'),(2,'al'),(3,'fe');
/*!40000 ALTER TABLE `chem_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chemical_composition_master`
--

DROP TABLE IF EXISTS `chemical_composition_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chemical_composition_master` (
  `ccm_id` int(11) NOT NULL AUTO_INCREMENT,
  `ccm_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ccm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chemical_composition_master`
--

LOCK TABLES `chemical_composition_master` WRITE;
/*!40000 ALTER TABLE `chemical_composition_master` DISABLE KEYS */;
INSERT INTO `chemical_composition_master` VALUES (1,'Cu'),(2,'Sn'),(3,'Zn'),(4,'Pb'),(5,'P'),(6,'Ni'),(7,'Fe'),(8,'Al'),(9,'sI'),(10,'S'),(11,'Mg'),(12,'Mn'),(13,''),(14,'');
/*!40000 ALTER TABLE `chemical_composition_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_master` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(200) DEFAULT NULL,
  `customer_address` varchar(200) DEFAULT NULL,
  `cst_no` varchar(100) DEFAULT NULL,
  `bst_no` varchar(100) DEFAULT NULL,
  `ecc_no` varchar(100) DEFAULT NULL,
  `octroi_no` varchar(100) DEFAULT NULL,
  `vendor_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
INSERT INTO `customer_master` VALUES (1,'TestCust','ABC','123','321','111','222','Abc123'),(2,'Larsen & Tuobro','Kothrud','asdasdadqwe','asdqwepoas','wqeqjask','qweasd','qwqweas'),(3,'MAHINDRA  CIE AUTOMOTIVE LTD','GAT NO 856 TO 860 CHAKAN \nAMBETHAN ROAD, TAL KHED \nPUNE 410 501\n','27580299262 C','27580299262 V','AABCM6632JXM002','0','MFVS0071'),(4,'KALYANI FORGE LTD','KOREGAON BHIMA, TAL SHIRUR \nPUNE412207','27460410005 C','27460410005 V','AAACK7311HXM001','-','TS086'),(5,'TRINITY ENGINEERS PVT LTD','14, D-1, M.I.D.C., CHINCHWAD, \nPUNE 411 019','27600000200 C','27600000200 V','AAACT0262CXM001','183','S253'),(6,'R. D. MINING EQUIPMENTS PVT LTD','A-18/1, MIDC AMBAD NASHIK422 010 ','27970820083 C','27970820083 V','AAFCR0650REM001','-','-'),(7,'ALFA LAVAL INDIA LTD','KASARWADI PUNE','27570247809 C-4','27570247809 V-4','AAACA5899AXM001','15','S6890'),(8,'BHARAT FORGE LTD','MUNDHAWA, PUNE','27590344313 C','27590344313 V','AAACB8519LXM001','-','2067'),(9,'WALCHANDNAGAR INDUSTRIES LTD','INDUSTRIAL MACHINARY DIVISION,\nPO WALCHANDNAGAR, \nDIST. PUNE','27830336809 C ','27830336809 V','AAACW0541MXM001','-','19155'),(10,'ALSTOM INDAI LTD','HYDRO BUSINESS VADODARA,\nVAIERDA ROAD, MANEJA, \nVADODARA 390013','24691700238 C ','24691700238 V','AABCA8679FXM001','-','29692'),(11,'THERMAX LTD','FACTORY MAIN STORE B & H \n(TBW SECTION) STORES D-1 BLOCK,\nMIDC, CHINCHWAD, PUNE 411 019','27630000038 C','27630000038 V','AAACT3910DXM001','PCMC-LBT-0017754','S 0133'),(12,'TENOVA METALS INDIA PVT LTD','PLOT NO R 101, T.T.C. \nINDUSTRIAL AREA, MIDCRABALE, \nNAVI MUMBAI 400701','27585216968 C','27585216968 V','AAMCS6453QEM002','-','-'),(13,'M M FORGINGS LTD PLANT II','ERASANAYAKKANPATTI VIRALIMALAI, PUDUKKOTTAI DISTRICT 621316','731268 DT.01.07.57','33896220600','AAACM2164LXM001','-',''),(14,'M M FORGINGS LTD PLANT I','ANAIKARAIPATTI, SINGAMPUNARI\n POST SIVGANGAI DIST 630502','731268 DT.01.07.57','33896220600','AAACM2164LXM003','-',''),(15,'M M FORGINGS LTD PLANT 4','FORGE SHOP KARANITHANGAL \nVILLAGE MAATHUR POST\n SRIPERUMBUDUR TALUK 602105','731268 DT.01.07.57','33896220600','AAACM2164LXM004','-',''),(16,'BILL FORGE PVT LTD PLANT I','9C BOMMASANDRA INDUSTRIAL \nAREA, BANGALORE 560099','70370035 DT.31.05.1984','29850093683','AAACB8620JXM001','-','-'),(17,'BILL FORGE PVT LTD PLANT II','98-L,PHASE II, KIDAB INDUSTRIAL\nAREA, BANGALORE 562106','70370035 DT.31.05.1984','29850093683','AAACB8620JXM002','-','-'),(18,'BILL FORGE PVT LTD PLANT III','7C KIADB INDUSTRIAL AREA, \nATTIBELE BANGALORE 562107','10171034 DT.03.07.1995','29850093683','AAACB8620JXM003','-','-'),(19,'DANIELI INDIA LTD','7000 CENTRAL EXPRESSWAY,SRI CITY\nSATHYAVEDU MANDAL,CHITTOOR DIST.\nANDHRA PRADESH 517588','28959169702 C ','28959169702 V','AABCG5359EEM003','-',''),(20,'TECHNOCRAFT INDUSTRIES (I) LTD','C5, C2/1, C2.2 MIDC MURBAD\n DIST THANE 421 401','27350002229 C','27350002229 V','AAACT2724PXM001','-',''),(21,'NEW HYDROEQUIPMENTS PVT LTD ','19/1, VITTHALWADI ROAD, \nPUNE 411 051','27490345144 C','27490345144 V','AAACN6445MXM001','-',''),(22,'','','','','','',''),(23,'NEW HYDROEQUIPMENTS PVT LTD','19/1 VITTHALWADI ROAD\nPUNE 411 051','27490345144 C','27490345144 C','AAACN6445MXM001','-',''),(24,'ORIENTAL RUBBER INDUSTRIES LTD','GAT NO 525, KOREGAON BHIMA,\nPUNE 412 216','27070350904 C W.E.F.01.04.2006','27070350904 V W.E.F.01.04.2006','AAACO1592LXM001','-',''),(25,'','','','','','',''),(26,'SMI-AMTEK CRANKSHAFT PVT LTD','PLOT NO 20, PHASE-I, \nINDUATRIAL AREA, DHARUHERA,\nREWARI 122 105','-','06742707885','AANCS4239HEM001','-',''),(27,'VOITH HYDRO PVT LTD','PLOT NO 107-ALINDRA, MANJUSAR\nGIDC ESTATE INDIA \nVADODARA 391 775','24692100970','24692100970','-','-',''),(28,'POLYHYDRON SYSTEMS PVT LTD','PLOT NO 34 & 37 - B, VILLAGE\nKUTTALWADI8,P. O. NAVAGE, \nBELGUM - 590 014','5016214-7','29730008971','AAACO2261NXM001','-','SU-SP06'),(29,'PAN AUTO COMP PVT LTD','SR. NO 429, MEDANKAR WADI, \nCHAKAN ALANDI ROAD,CHAKAN PUNE ','27320410179 C','27320410179 V','AAACJ4658CXM001','-',''),(30,'','','','','','',''),(31,'GEAROCK FORGE PVT LTD','NO 143-B8, BOMMASANDRA INDL. \nAREA ANEKAL TALUK, BANGALORE \n560099','11873334','29730072312','AABCG 1926FXM001','-',''),(32,'SIEMENS LTD','PLOT NO D-41, TTC INDL. AREA,\nMIDC TURBHE, OPP TURBHE MTNL \nEXCHANGE NAVI MUNBAI 400 705','27700387660 C','27700387660 V','AAACM6828MEM007','-','50139584'),(33,'AMW MGM FORGINGS PVT LTD','#82-84, K. R. S. ROAD, METAGALLI POST, MYSORE 570 016 KARNATAKA','29150809346 C W.E.F. 01.08.2008','29150809346 V W.E.F. 01.08.2008','','',''),(34,'AMW MGM FORGINGS PVT LTD','#82-84, K. R. S. ROAD METAGALLI POST MYSORE 570016 KARNATKA','29150908346 C W.E.F. 01.08.2008','29150908346 V W.E.F. 01.08.2008','AAECM8284FXM002','-','S135'),(35,'KALYANI TECHNOFORGE LTD','PLOT NO E-84, M.I.D.C. RANJANGAON, VIL. KAREGAON, TAL SHIRUR DIST PUNE 412 209','27420410027 C w.e.f.:01/04/2006','27420410027 V w.e.f.:01/04/2006','AABCK0618AXM004','-','30193'),(36,'ORIENTAL RUBBER INDUSTRIES LTD','GAT NO 525, KOREGAON BHIMA, PUNE 412216','27070350904 C','27070350904 V','AAACO1592LXM001','-','S0053');
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_personnel`
--

DROP TABLE IF EXISTS `customer_personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_personnel` (
  `personal_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `department` varchar(100) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`personal_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_personnel_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer_master` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_personnel`
--

LOCK TABLES `customer_personnel` WRITE;
/*!40000 ALTER TABLE `customer_personnel` DISABLE KEYS */;
INSERT INTO `customer_personnel` VALUES (1,3,'PURCHASE','VIJAY SHELAKE','9689498534','-'),(2,6,'PURCHASE','SWAPNIL','9225134821','-'),(3,7,'PURCHASE','H K DESHPANDE','-','-'),(4,8,'PURCHASE','KAPIL PANDARE','020-26702442','-'),(5,8,'PURCHASE','KAPIL PANDARE','020-26702442','kapilpandare@bharatforge.com');
/*!40000 ALTER TABLE `customer_personnel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(100) DEFAULT NULL,
  `emp_last_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_composition`
--

DROP TABLE IF EXISTS `grade_composition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_composition` (
  `grade_comp_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_id` int(10) unsigned NOT NULL,
  `ingrediant_type` varchar(45) NOT NULL,
  `ingrediant_name` varchar(45) NOT NULL,
  `min_value` varchar(45) NOT NULL,
  `max_value` varchar(45) NOT NULL,
  PRIMARY KEY (`grade_comp_id`),
  KEY `FK_grade_composition_1` (`grade_id`),
  CONSTRAINT `FK_grade_composition_1` FOREIGN KEY (`grade_id`) REFERENCES `grade_master` (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_composition`
--

LOCK TABLES `grade_composition` WRITE;
/*!40000 ALTER TABLE `grade_composition` DISABLE KEYS */;
INSERT INTO `grade_composition` VALUES (1,1,'C','Cu','0','31'),(2,1,'C','Fe','5','20'),(3,1,'M','Hardening','0','5'),(5,3,'C','Cu','0.0','0.0'),(6,3,'c','Sn','11.0','13.0'),(7,3,'C','Zn','0.0','0.3'),(8,3,'C','Pb','0.1','0.6'),(9,3,'C','Pb','0.0','0.5'),(10,3,'C','Ni','0.0','0.5'),(11,3,'C','Fe','0.0','0.1'),(12,3,'C','Al','0.0','0.01'),(13,3,'C','Sl','0.0','0.02'),(14,3,'C','S','0.0','0.1'),(15,4,'C','Cu','BALANCE','BALANCE'),(16,5,'C','Cu','74','80'),(17,5,'C','Sn','6','8'),(18,5,'C','Zn','0','2'),(19,5,'C','Pb','13','17'),(20,5,'C','Ni','0.5','2'),(21,5,'C','P','0','0.1'),(22,6,'C','Al','0.0','0.01'),(23,6,'C','Cu','85','89'),(24,6,'C','Fe','0.0','0.2'),(25,6,'C','Mn','0.0','0.0'),(26,6,'C','Ni','0.0','2'),(27,6,'C','Pb','2.5','3.5'),(28,6,'C','S','0.0','0.1'),(29,6,'C','Sb','0.0','0.25'),(30,6,'C','Si','0.0','0.01'),(31,6,'C','Sn','6','8'),(32,6,'C','Zn','1.5','3'),(33,7,'C','Sn','13','15'),(34,7,'C','Pb','0.0','1'),(35,7,'C','Zn','0','0.5'),(36,7,'C','Cu','85','87'),(37,7,'C','Al','0.0','0.01'),(38,7,'C','Ni','0.0','1'),(39,7,'C','P','0.0','0.4'),(40,7,'C','Si','0.0','0.01'),(41,7,'C','Fe','0.0','0.2'),(42,7,'C','Mn','0','0.2'),(43,7,'C','S','0.0','0.05'),(44,7,'C','Sb','0','0.2'),(45,8,'C','Sn','4','6'),(46,8,'C','Pb','4','6'),(47,8,'C','Zn','4','6'),(48,8,'C','Cu','83','87'),(49,8,'C','Ni','0.0','2'),(50,8,'C','Mn','0.0','0.1'),(51,8,'C','Si','0.0','0.01'),(52,8,'C','Al','0.0','0.01'),(53,8,'C','Fe','0.0','0.3'),(54,8,'C','P','0.0','0.1'),(55,8,'C','S','0.0','0.1'),(56,8,'C','Sb','0','0.25'),(58,9,'C','Al','0.0','0.01'),(59,9,'C','Cu','84','86'),(60,9,'C','Fe','0.0','0.3'),(61,9,'C','Mn','0.0','0.1'),(62,9,'C','Ni','0.0','2'),(63,9,'C','P','0.0','0.05'),(64,9,'C','Pb','4','6'),(65,9,'C','S','0.0','0.1'),(66,9,'C','Sb','0.0','0.25'),(67,9,'C','Si','0.0','0.01'),(68,9,'C','Sn','4','6'),(69,9,'C','Zn','4','6'),(70,10,'C','Cu','76','83'),(71,10,'C','Al','8.5','10.5'),(72,10,'C','Fe','4','5.5'),(73,10,'C','Ni','4','6'),(74,10,'C','Mn','0.0','3'),(75,10,'C','Pb','0.0','0.03'),(76,10,'C','Si','0.0','0.1'),(77,10,'C','Sn','0.0','0.1'),(78,10,'C','Zn','0.0','0.5'),(79,10,'C','Mg','0.0','0.05'),(80,12,'C','Cu','BALANCE','BALANCE'),(81,12,'C','Sn','0.0','0.1'),(82,12,'C','Zn','0.0','0.5'),(83,12,'C','Pb','0.0','0.03'),(84,12,'C','Ni','4','5.5'),(85,12,'C','Fe','4','5.5'),(86,12,'C','Al','8.8','10'),(87,12,'C','Mn','0.0','3'),(88,12,'C','Si','0.0','0.1'),(89,12,'C','Mg','0.0','0.05'),(92,13,'C','Sn','5','7'),(93,13,'C','Pb','1','3'),(94,13,'C','Zn','2','3'),(95,13,'C','Cu','BALANCE','BALANCE'),(96,14,'C','Sn','6','8'),(97,14,'C','Zn','1.5','3'),(98,14,'C','Pb','2.5','3.5'),(99,14,'C','Ni','0.0','2'),(100,14,'C','Fe','0.0','0.2'),(101,14,'C','Mn','0.0','0.01'),(102,14,'C','Sb','0.0','0.25'),(103,14,'C','Si','0.0','0.01'),(104,14,'C','Cu','BALANCE','BALANCE'),(105,15,'C','Sn','9.5','10.5'),(106,15,'C','Zn','1.5','3'),(107,15,'C','Pb','0.0','1.5'),(108,15,'C','Ni','0.0','1'),(109,15,'C','Fe','0.0','0.15'),(110,15,'C','Al','0.0','0.01'),(111,15,'C','Si','0.0','0.02'),(112,15,'C','Cu','BALANCE','BALANCE'),(113,15,'M','UTS N/mm2','260','-'),(114,15,'M','Yield Stress','120','--'),(115,15,'M','Elongation %','13','-'),(116,15,'M','HARDNESS','',''),(117,15,'M','SPECIFIED','0','0'),(118,15,'M','Actual Values','0','0'),(119,16,'C','Cu','57','61'),(120,16,'C','Sn','0.5','1.5'),(121,16,'C','Mn','1.5','3.5'),(122,16,'C','Fe','-','0.5'),(123,16,'C','Pb','-','0.4'),(124,16,'C','Al','0.5','3'),(125,16,'C','Zn','BALANCE','BALANCE'),(126,17,'C','Cu','57','0.0'),(127,17,'C','Sn','0.0','1'),(128,17,'C','Pb','0.0','0.5'),(129,17,'C','Ni','0.0','1'),(130,17,'C','Fe','0.7','2'),(131,17,'C','Al','0.5','2.5'),(132,17,'C','Mn','1','3'),(133,17,'C','Si','0.0','0.1'),(134,17,'C','Zn','REMINDER','REMINDER'),(135,18,'C','Cu','BALANCE','BALANCE'),(136,18,'C','Sn','9.5','11'),(137,18,'C','Pb','0.0','0.75'),(138,18,'C','P','0.4','1'),(139,18,'C','Ni','0.0','0.5'),(140,18,'C','Zn','0.0','0.5'),(141,19,'C','Sn','6','8'),(142,19,'C','Zn','0.0','0.75'),(143,19,'C','Pb','9','11'),(144,19,'C','Ni','0.0','2'),(145,19,'C','Al','0.0','0.01'),(146,19,'C','Si','0.0','0.02'),(147,19,'C','Fe','0.0','0.35'),(148,19,'C','Sb','0.0','0.5'),(149,19,'C','Cu','BALANCE','BALANCE'),(150,20,'C','Cu','83.5','87'),(151,20,'C','Sn','10.5','12.5'),(152,20,'C','Pb','0.7','2.5'),(153,20,'C','Zn','0','2'),(154,20,'C','Ni','0','2'),(155,20,'C','P','0','0.4'),(156,20,'C','Fe','0','0.2'),(157,20,'C','Sb','0','0.2'),(158,20,'C','S','0','0.08'),(163,21,'C','Cu','BALANCE','BALANCE'),(164,21,'C','Sn','9.0','11.0'),(165,21,'C','Zn','0','0.05'),(166,21,'C','Ni','0','0.25'),(167,21,'C','Pb','0','0.25'),(168,21,'C','P','0','0.15'),(170,22,'C','Cu','BALANCE','BALANCE'),(171,22,'C','Sn','0.0','0.1'),(172,22,'C','Zn','0.0','0.5'),(173,22,'C','Pb','0.0','0.03'),(174,22,'C','Ni','4','5.5'),(175,22,'C','Fe','4','5.5'),(176,22,'C','Al','8.8','10'),(177,22,'C','Mn','0.0','3'),(178,22,'C','Si','0.0','0.1'),(179,22,'C','Mg','0.0','0.05'),(180,23,'C','Cu','81','85'),(181,23,'C','Sn','6','8'),(182,23,'C','Zn','3','5'),(183,23,'C','Pb','5','7'),(184,23,'C','Ni','0.0','2'),(185,23,'C','P','0.0','0.2'),(186,24,'C','Sn','10','-'),(187,24,'C','Cu','BALANCE','BALANCE'),(188,24,'C','Pb','-','0.25'),(189,24,'C','P','0.5','-'),(190,24,'C','Zn','-','0.05'),(191,24,'C','Ni','-','0.1'),(192,24,'C','Fe','-','0.1'),(193,24,'C','Al','-','0.01'),(194,24,'C','Si','-','0.02'),(196,26,'C','Cu','85','88.5'),(197,26,'C','Sn','11.2','13'),(198,26,'C','Zn','0.0','0.03'),(199,26,'C','Ni','1.2','2'),(200,26,'C','P','0.0','0.3'),(201,26,'C','Pb','0.0','0.5'),(202,26,'C','Fe','0.0','0.1'),(203,26,'C','S','0.0','0.05'),(204,26,'C','Al','0.0','0.05'),(205,26,'C','Sb','0.0','0.2'),(206,27,'C','Cu','BALANCE','BALANCE'),(207,27,'C','Sn','10','11.5'),(208,27,'C','Zn','0.0','0.05'),(209,27,'C','Pb','0.0','0.25'),(210,27,'C','P','0.5','1'),(211,27,'C','Ni','0.5','1'),(212,27,'C','Fe','0.0','0.1'),(213,27,'C','Al','0.0','0.01'),(214,27,'C','Mn','0.0','0.05'),(215,27,'C','Sb','0.0','0.05'),(216,27,'C','Si','0.0','0.05'),(217,27,'C','S','0','0.05'),(218,29,'C','Cu','BALANCE','BALANCE'),(219,29,'C','Sn','9.0','11.0'),(220,29,'C','Zn','-','1'),(221,29,'C','Pb','13','17'),(222,29,'C','P','-','0.10'),(223,29,'C','Ni','0.0','2'),(224,29,'C','Fe','-','0.15'),(225,29,'C','Al','-','0.01'),(226,29,'C','Si','-','0.02'),(227,29,'C','Mn','-','0.2'),(228,30,'C','Cu','73','77'),(229,30,'C','Sn','7','9'),(230,30,'C','Pb','13','17'),(231,30,'C','Ni','0.0','2'),(232,31,'C','Cu','84','88'),(233,31,'C','Sn','12','15'),(234,31,'C','Sb','0.0','0.05'),(235,31,'C','Pb','0.0','0.3'),(236,31,'C','Zn','0.0','0.3'),(237,31,'C','Fe','0.0','0.2'),(238,31,'C','Ni','0.0','1'),(239,31,'C','P','0.15','0.5'),(240,31,'C','Al','0.0','0.01'),(241,31,'C','Si','0.0','0.01'),(242,32,'C','Cu','55','60'),(243,32,'C','Zn','BALANCE','BALANCE'),(244,32,'C','Sn','0.0','1.5'),(245,32,'C','Pb','0.5','2'),(246,33,'C','Cu','60','66'),(247,33,'C','Sn','0.0','0.2'),(248,33,'C','Pb','0.0','0.2'),(249,33,'C','Zn','22','28'),(250,33,'C','Fe','2','4'),(251,33,'C','Al','5','7.5'),(252,33,'C','Mn','2.5','5'),(253,34,'C','Cu','BALANCE','BALANCE'),(254,34,'C','Sn','9.0','11.0'),(255,34,'C','Zn','0.0','1'),(256,34,'C','Pb','8.5','11'),(257,34,'C','Ni','0.0','2'),(258,34,'C','P','0.0','0.1'),(259,35,'C','Sn','4','6'),(260,35,'C','Zn','0.0','1'),(261,35,'C','Pb','18','23'),(262,35,'C','Ni','0.0','2'),(263,35,'','Fe','0.0','0.35'),(264,35,'C','Sb','0.0','0.5'),(265,35,'C','Cu','REMAINDER ','REMAINDER'),(266,36,'C','Sn','0.0','0.2'),(267,36,'C','Pb','0.0','0.2'),(268,36,'C','Zn','BALANCE','BALANCE'),(269,36,'C','Mn','0.0','4'),(270,36,'C','Al','3','6'),(271,36,'C','Fe','1.5','3.25'),(272,36,'C','Cu','55','0'),(273,37,'C','Al','8.5','10.5'),(274,37,'C','Fe','1.5','3.5'),(275,37,'C','Mn','0.0','1'),(276,37,'C','Ni','0.0','1'),(277,37,'C','Zn','0.0','0.5'),(278,37,'C','Sn','0.0','1'),(279,37,'C','Pb','0.0','0.05'),(280,37,'C','Si','0.0','0.25'),(281,37,'C','Mg','0.0','0.05'),(282,37,'C','Cu','BALANCE','BALANCE'),(283,38,'C','Al','0.0','0.01'),(284,38,'C','Cu','85','87'),(285,38,'C','Fe','0.0','0.3'),(286,38,'C','Mn','0.0','0.1'),(287,38,'C','Ni','0.0','0.75'),(288,38,'C','Pb','2','4'),(289,38,'C','S','0.0','0.1'),(290,38,'C','Sb','0.0','0.3'),(291,38,'C','Si','0.0','0.01'),(292,38,'C','Sn','8.3','10'),(293,38,'C','Zn','1','2.5');
/*!40000 ALTER TABLE `grade_composition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_master`
--

DROP TABLE IF EXISTS `grade_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_master` (
  `grade_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(45) NOT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_master`
--

LOCK TABLES `grade_master` WRITE;
/*!40000 ALTER TABLE `grade_master` DISABLE KEYS */;
INSERT INTO `grade_master` VALUES (1,'CU31'),(3,'BS 1400 PB2'),(4,'PH. BRONZE (SAE62)'),(5,'CUSN7 PB15'),(6,'EN 1982 CC492K'),(7,'AL.111.5475.03'),(8,'EN 1982 CC491K-GZ'),(9,'EN 1982 CC491K-GS'),(10,'EN 1982 CC333G-GZ'),(12,'BS 1400 AB2'),(13,'IS 1458 CLASS II'),(14,'BS 1400 LG4'),(15,'IS 306 GR. II'),(16,'Mn BRONZE'),(17,'BS 1400 HTB1'),(18,'BS 1400 PB4'),(19,'IS 318 LTB3'),(20,'CU SN 12 PB'),(21,'IS 28 GRADE IV'),(22,'ALBC3'),(23,'DIN 1705 RG7'),(24,'IS 28 GRADE II'),(26,'CU SN 12'),(27,'BS 1400 PB1'),(28,'CuZn25Al5Mn4Fe3'),(29,'CuPb 15 Sn 10'),(30,'Cu Pb 15 Sn 7'),(31,'PBC3'),(32,'BRASS'),(33,'UNS C86300'),(34,'BS 1400 LB2'),(35,'IS 318 LTB 6'),(36,'IS 304 HTB 2'),(37,'IS 305 AB 1'),(38,'Cu Sn7 ZnPb'),(39,'LBC 5');
/*!40000 ALTER TABLE `grade_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_hdr`
--

DROP TABLE IF EXISTS `inv_hdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_hdr` (
  `inv_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inv_date` datetime NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `purchase_order_no` text,
  `purchase_order_date` datetime NOT NULL,
  `mode_of_transport` varchar(45) DEFAULT NULL,
  `vehicle_no` varchar(45) DEFAULT NULL,
  `net_total_amount` decimal(15,2) NOT NULL DEFAULT '0.00',
  `freight_insurance` float NOT NULL,
  `grand_total` decimal(15,2) NOT NULL DEFAULT '0.00',
  `tcNo` varchar(45) DEFAULT NULL,
  `irNo` varchar(45) DEFAULT NULL,
  `lrNo` varchar(45) DEFAULT NULL,
  `paymentTerms` varchar(45) DEFAULT NULL,
  `pkg_frwd_chg` float NOT NULL,
  `inv_issue_date` datetime DEFAULT NULL,
  `removal_date` datetime DEFAULT NULL,
  `li_amount_total` decimal(15,2) NOT NULL DEFAULT '0.00',
  `bed_rate` float NOT NULL,
  `ed_cess_rate` float NOT NULL,
  `shs_cess` float NOT NULL,
  `vat_or_cst` float NOT NULL,
  `inv_no` varchar(45) DEFAULT NULL,
  `delivery_to` varchar(200) DEFAULT NULL,
  `delivery_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`inv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_hdr`
--

LOCK TABLES `inv_hdr` WRITE;
/*!40000 ALTER TABLE `inv_hdr` DISABLE KEYS */;
INSERT INTO `inv_hdr` VALUES (1,'2014-03-26 00:00:00',1,'1','2014-03-26 00:00:00','Truck','MH 12 AB 1234',1500.00,50,1550.00,NULL,NULL,NULL,NULL,0,NULL,NULL,0.00,0,0,0,0,NULL,NULL,NULL),(2,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0.00,0,0.00,NULL,NULL,NULL,NULL,0,NULL,NULL,0.00,0,0,0,0,NULL,NULL,NULL),(3,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0.00,0,0.00,NULL,NULL,NULL,NULL,0,NULL,NULL,0.00,0,0,0,0,NULL,NULL,NULL),(4,'2014-03-03 00:00:00',0,'123','2014-03-03 00:00:00','Test',NULL,0.00,0,0.00,NULL,NULL,NULL,NULL,0,NULL,NULL,0.00,0,0,0,0,NULL,NULL,NULL),(35,'2014-03-18 00:00:00',0,'150','2014-03-24 00:00:00','By Road','123456A',0.00,0,0.00,'123','Test321','123','New',0,NULL,NULL,0.00,0,0,0,0,NULL,NULL,NULL),(36,'2014-03-11 00:00:00',0,'0','2014-03-26 00:00:00','New','abcd',101000.00,500,101500.00,'Test','Test','123','New',1000,'2014-03-31 00:00:00','2014-03-31 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(37,'2014-03-17 00:00:00',0,'0','2014-03-17 00:00:00','Test','abcd',200.00,20,30.00,'Test','Test','123','New',120,'2014-03-09 00:00:00','2014-03-16 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(38,'2014-03-17 00:00:00',1,'0','2014-03-18 00:00:00','By Road','abcd',50.00,50,50.00,'Test','Test321','123','New',50,'2014-03-17 00:00:00','2014-03-18 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(39,'2014-03-16 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',50.00,50,50.00,'2134','3234','123','321',50,'2014-03-16 00:00:00','2014-03-17 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(40,'2014-03-17 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',4550.00,50,50.00,'2134','3234','123','321',50,'2014-03-24 00:00:00','2014-03-25 00:00:00',4500.00,0,0,0,0,NULL,NULL,NULL),(41,'2014-03-10 00:00:00',1,'1234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',0.00,0,0.00,'1001','1002','123','321',0,'2014-03-10 00:00:00','2014-03-10 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(42,'2014-03-10 00:00:00',1,'1250','2014-03-10 00:00:00','By Road','MH 70 AB 1234',2450.00,100,2844.00,'1001','1002','123','New',50,'2014-03-10 00:00:00','2014-03-10 00:00:00',2400.00,0,0,0,0,NULL,NULL,NULL),(43,'2014-03-11 00:00:00',1,'550','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1550.00,200,1936.00,'2134','3234','123','321',50,'2014-03-10 00:00:00','2014-03-11 00:00:00',1500.00,0,0,0,0,NULL,NULL,NULL),(44,'2014-03-18 00:00:00',1,'234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',1750.00,1000,2960.00,'2134','3234','123','321',500,'2014-03-10 00:00:00','2014-03-10 00:00:00',1250.00,0,0,0,0,NULL,NULL,NULL),(45,'2014-03-17 00:00:00',1,'0','2014-03-26 00:00:00','By Road','MH 70 AB 1234',300.00,100,436.00,'2134','3234','123','321',100,'2014-03-17 00:00:00','2014-03-18 00:00:00',200.00,0,0,0,0,NULL,NULL,NULL),(46,'2014-03-10 00:00:00',1,'1200','2014-03-17 00:00:00','By Road','MH 70 AB 1234',0.00,150,0.00,'2134','3234','123','321',250,'2014-03-11 00:00:00','2014-03-18 00:00:00',0.00,0,0,0,0,NULL,NULL,NULL),(47,'2014-03-18 00:00:00',1,'0','2014-03-10 00:00:00','By Road','MH 70 AB 1234',6700.00,200,7704.00,'2134','3234','123','321',200,'2014-03-17 00:00:00','2014-03-17 00:00:00',6500.00,0,0,0,0,NULL,NULL,NULL),(48,'2014-03-17 00:00:00',1,'1500','2014-03-17 00:00:00','By Road','MH 70 AB 1234',2124.00,500,2878.88,'2134','123','123','123',100,'2014-03-17 00:00:00','2014-03-11 00:00:00',2024.00,0,0,0,0,NULL,NULL,NULL),(49,'2014-03-18 00:00:00',1,'0','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1500.00,1000,2859.56,'2134','123','123','123',500,'2014-03-18 00:00:00','2014-03-18 00:00:00',1000.00,12,2,1,5,NULL,NULL,NULL),(50,'2014-04-11 00:00:00',3,'3800054427','2014-03-08 00:00:00','BY ROAD','-',39774.00,0,46924.60,'006','-','-','30 DAYS',0,'2014-04-11 09:01:00','2014-04-11 09:30:00',39774.00,12,2,1,5,'002','',''),(51,'2014-04-01 00:00:00',4,'1011302279','2014-03-25 00:00:00','BY ROAD','-',506020.00,0,596992.27,'','-','-','0',0,'2014-04-01 15:52:00','2014-04-01 15:52:00',506020.00,12,2,1,5,'001',NULL,NULL),(52,'2014-04-21 00:00:00',5,'PO/2013/2590','2014-03-12 00:00:00','BY ROAD','-',23400.00,0,27606.85,'015','-','-','0',0,'2014-04-21 09:00:00','2014-04-21 09:30:00',23400.00,12,2,1,5,'003','',''),(53,'2014-04-04 00:00:00',6,'RD/PO/B-0283','2014-03-10 00:00:00','BY ROAD','-',159375.00,0,188027.44,'002','-','-','0',0,'2014-04-04 12:58:00','2014-04-04 12:58:00',159375.00,12,2,1,5,'004','',''),(54,'2014-04-04 00:00:00',6,'RD/PO/B-0284','2014-03-16 00:00:00','BY ROAD','-',13875.00,0,16369.45,'003','-','-','0',0,'2014-04-04 12:00:00','2014-04-04 13:40:00',13875.00,12,2,1,5,'005','',''),(55,'2014-04-12 00:00:00',12,'TMPL/R/1202/L(`R)/336','2014-03-11 00:00:00','BY ROAD','-',37000.00,0,43651.86,'008','-','-','0',0,'2014-04-12 09:30:00','2014-04-12 09:45:00',37000.00,12,2,1,5,'006','TENOVA METAL INDIA PVT LTD','PLOT NO 28-33, NEAR GURUNANAK ROLLING MILLS, OPP.ATAGAON RAILWAY STATION, ATAGAON TAL SHAHPUR THANE 421601'),(56,'2014-04-06 00:00:00',13,'4100071070','2014-03-21 00:00:00','BY ROAD','-',125800.00,0,144175.86,'-','-','-','0',0,'2014-04-06 14:50:00','2014-04-06 14:50:00',125800.00,12,2,1,2,'007','',''),(57,'2014-04-07 00:00:00',4,'1011302264','2014-03-24 00:00:00','BY ROAD','-',116937.00,0,137959.94,'004, 005','-','-','0',0,'2014-04-07 15:42:00','2014-04-07 15:42:00',116937.00,12,2,1,5,'008','',''),(58,'2014-04-08 00:00:00',10,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240.00,0,1185313.51,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240.00,12,2,1,2,'009','',''),(59,'2014-04-08 00:00:00',13,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240.00,0,1185313.50,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240.00,12,2,1,2,'009','',''),(60,'2014-04-09 00:00:00',7,'3050265','2014-03-11 00:00:00','BY ROAD','-',72690.00,0,85758.21,'-','-','-','0',0,'2014-04-09 10:16:00','2014-04-09 10:16:00',72690.00,12,2,1,5,'60','',''),(61,'2014-04-09 00:00:00',7,'3047184','2014-01-31 00:00:00','BY ROAD','-',54654.00,0,64479.69,'-','-','-','0',0,'2014-04-09 10:23:00','2014-04-09 10:23:00',54654.00,12,2,1,5,'61','',''),(62,'2014-04-09 00:00:00',31,'3130003604','2014-02-15 00:00:00','BY ROAD','-',709994.00,0,813704.25,'-','-','-','AGAINST DELIVERY',3994,'2014-04-09 10:36:00','2014-04-09 10:36:00',706000.00,12,2,1,2,'62','',''),(63,'2014-04-09 00:00:00',32,'4505015012','2014-02-13 00:00:00','BY ROAD','-',198800.00,0,234540.27,'-','-','-','0',0,'2014-04-09 10:46:00','2014-04-09 10:46:00',198800.00,12,2,1,5,'63','',''),(65,'2014-04-09 00:00:00',21,'364','2014-01-22 00:00:00','BY ROAD','-',60000.00,0,70786.80,'-','-','-','0',0,'2014-04-09 14:52:00','2014-04-09 14:52:00',60000.00,12,2,1,5,'64','',''),(66,'2014-04-11 00:00:00',29,'2/M/13-14','2014-04-07 00:00:00','BY ROAD','MH12GR5470',51062.50,0,60242.51,'007','-','-','AGAINST DELIVERY',0,'2014-04-11 09:53:00','2014-04-11 09:53:00',51062.50,12,2,1,5,'66','',''),(67,'2014-04-12 00:00:00',20,'MBD/C5/345','2014-03-31 00:00:00','BY ROAD','-',119787.50,0,141322.89,'009','-','-','30 DAYS',0,'2014-04-12 12:09:00','2014-04-12 12:09:00',119787.50,12,2,1,5,'67','',''),(68,'2014-04-12 00:00:00',26,'13-14/POG/0544','2014-02-20 00:00:00','BY ROAD','-',762100.00,8000,881421.47,'010','-','-',' COD BASIS',4000,'2014-04-12 14:01:00','2014-04-12 14:01:00',758100.00,12,2,1,2,'68','',''),(69,'2014-04-16 00:00:00',8,'4600457105','2014-03-28 00:00:00','BY ROAD','-',9600.00,0,11325.89,'012','-','-','30 DAYS',0,'2014-04-16 09:00:00','2014-04-16 09:30:00',9600.00,12,2,1,5,'69','',''),(70,'2014-04-16 00:00:00',8,'4600446864','2014-02-05 00:00:00','BY ROAD','-',1070000.00,0,1262364.60,'011','-','-','30 DAYS',0,'2014-04-16 09:00:00','2014-04-16 09:30:00',1070000.00,12,2,1,5,'70','',''),(71,'2014-04-16 00:00:00',34,'P2P/000018/1415','2014-04-09 00:00:00','BY ROAD','-',19000.00,0,21775.37,'-','-','-','IMMEDIATE',0,'2014-04-16 09:15:00','2014-04-16 09:30:00',19000.00,12,2,1,2,'71','',''),(72,'2014-04-16 00:00:00',8,'4600420330','2013-08-29 00:00:00','BY ROAD','-',5990.00,0,7066.89,'013','-','-','30 DAYS',0,'2014-04-16 09:36:00','2014-04-16 09:36:00',5990.00,12,2,1,5,'72','',''),(73,'2014-04-16 00:00:00',35,'4400024857','2014-04-13 00:00:00','BY ROAD','-',277500.00,0,327388.94,'014','-','-','IMMEDIATE',0,'2014-04-16 11:54:00','2014-04-16 11:54:00',277500.00,12,2,1,5,'73','JWALA INDUSTRIES','W 106, S BLOCK MIDC BHOSARI PUNE 26'),(74,'2014-04-16 00:00:00',35,'4400024857','2014-04-13 00:00:00','BY ROAD','-',29160.00,0,34402.38,'015','-','-','IMMEDIATE',0,'2014-04-16 16:58:00','2014-04-16 16:58:00',29160.00,12,2,1,5,'74','',''),(75,'2014-04-18 00:00:00',35,'4400024857','2014-04-13 00:00:00','BY ROAD','-',277500.00,0,327388.95,'-','-','-','IMMEDIATE',0,'2014-04-18 09:50:00','2014-04-18 10:00:00',277500.00,12,2,1,5,'75','JWALA INDUSTRIES','W-106, S BLOCK, MIDC, BHOSARI, PUNE 26'),(76,'2014-04-20 00:00:00',13,'4505015012','2014-02-13 00:00:00','BY ROAD','-',198800.00,0,234540.27,'-','-','-','30 DAYS',0,'2014-04-20 13:21:00','2014-04-20 14:21:00',198800.00,12,2,1,5,'76','',''),(77,'2014-04-20 00:00:00',32,'4505015012','2014-02-13 00:00:00','BY ROAD','-',0.00,0,0.00,'-','-','-','30 DAYS',0,'2014-04-20 13:21:00','2014-04-20 14:21:00',0.00,0,0,0,0,'76','',''),(78,'2014-04-20 00:00:00',1,'1500','2014-04-29 00:00:00','','',0.00,0,0.00,'','','','123',0,'2014-04-20 17:15:00','2014-04-20 17:15:00',0.00,0,0,0,0,'1234567','',''),(79,'2014-04-20 00:00:00',1,'150000','2014-04-22 00:00:00','','',0.00,100,0.00,'','','','123',100,'2014-04-20 17:39:00','2014-04-20 17:39:00',0.00,10,5,3,0,'123456789','',''),(80,'2014-04-20 00:00:00',1,'150000','2014-04-22 00:00:00','','',1400.00,0,1607.83,'','','','123',0,'2014-04-20 17:56:00','2014-04-20 17:56:00',1400.00,10,10,5,3,'12345678','',''),(81,'2014-04-21 00:00:00',7,'3051845','2014-04-01 00:00:00','BY ROAD','MH 12 FD 5098',130400.00,0,153843.31,'016','-','001','30 DAYS',0,'2014-04-21 09:00:00','2014-04-21 09:30:00',130400.00,12,2,1,5,'1','',''),(82,'2014-04-21 00:00:00',7,'3051845','2014-04-01 00:00:00','BY ROAD','MH 12 FD 5098',129400.00,0,220372.27,'016','-','001','30 DAYS',0,'2014-04-21 17:11:00','2014-04-21 17:11:00',129400.00,12,2,1,5,'1','',''),(83,'2014-04-21 00:00:00',7,'3051845','2014-04-01 00:00:00','BY ROAD','MH 12 FD 5098',130400.00,0,153843.31,'016','-','001','30 DAYS',0,'2014-04-21 17:18:00','2014-04-21 17:18:00',130400.00,12,2,1,5,'999999','',''),(84,'2014-04-22 00:00:00',36,'4500001973','2014-03-23 00:00:00','BY ROAD','-',21000.00,0,24775.38,'-','-','-','0',0,'2014-04-22 10:18:00','2014-04-22 10:18:00',21000.00,12,2,1,5,'78','',''),(85,'2014-04-23 00:00:00',32,'4505105505','2014-03-28 00:00:00','BY ROAD','-',-508500.00,0,-599918.10,'-','-','-','0',0,'2014-04-23 13:27:00','2014-04-23 13:27:00',-508500.00,12,2,1,5,'90','SIEMENS LTD- VADAPE','BLOCK A1, B2 & C4, SHREE RAJLAXMI LOGISTIC 421302 TAL. BHIWANDI INDIA'),(86,'2014-04-23 00:00:00',32,'4505103782','2014-03-27 00:00:00','BY ROAD','-',206500.00,0,243624.56,'-','-','-','0',0,'2014-04-23 13:41:00','2014-04-23 13:41:00',206500.00,12,2,1,5,'89','SIEMENS LTD - VADAPE','BLOCK A1, B2 & C4, SHREE RAJLAXMI LOGISTIC 421302 TAL. BHIWANDI INDIA'),(87,'2014-04-23 00:00:00',32,'4505121638','2014-04-04 00:00:00','BY ROAD','-',117600.00,0,138742.12,'-','-','-','0',0,'2014-04-23 14:25:00','2014-04-23 14:25:00',117600.00,12,2,1,5,'88','SIEMENS LTD- VADAPE','BLOCK A1, B2 & C4, SHREE RAJLAXMI LOGISTIC 421302 TAL. BHIWANDI INDIA'),(88,'2014-04-22 00:00:00',32,'4505121638','2014-04-04 00:00:00','BY ROAD','MH 12 FD 5098',55200.00,0,65123.86,'-','-','-','0',0,'2014-04-22 14:54:00','2014-04-22 14:54:00',55200.00,12,2,1,5,'91','',''),(89,'2014-04-23 00:00:00',32,'4505105505','2014-03-28 00:00:00','BY ROAD','-',169500.00,0,199972.71,'-','-','-','0',0,'2014-04-23 16:08:00','2014-04-23 16:08:00',169500.00,12,2,1,5,'93','SIEMENS LTD - VADAPE','BLOCK A1, B2& C4, SHREE RAJLAXMI LOGISTIC 421302 TAL BJIWANDI INDIA'),(90,'2014-04-23 00:00:00',32,'4505103782','2014-03-27 00:00:00','BY ROAD','-',206500.00,0,243624.57,'-','-','-','0',0,'2014-04-23 16:41:00','2014-04-23 16:41:00',206500.00,12,2,1,5,'94','SIEMENS LTD - VADAPE','BLOCK A1, B2& C4, SHREE RAJLAXMI LOGISTIC 421302 TAL BJIWANDI INDIA'),(91,'2014-04-23 00:00:00',32,'4505121638','2014-04-04 00:00:00','BY ROAD','-',117600.00,0,138742.12,'-','-','-','0',0,'2014-04-23 16:52:00','2014-04-23 16:52:00',117600.00,12,2,1,5,'95','SIEMENS LTD - VADAPE','BLOCK A1, B2& C4, SHREE RAJLAXMI LOGISTIC 421302 TAL BHIWANDI INDIA'),(92,'2014-04-25 00:00:00',4,'3011300924','2013-12-27 00:00:00','BY ROAD','-',0.00,0,69087.91,'016','-','-','0',0,'2014-04-25 10:28:00','2014-04-25 10:28:00',0.00,12,2,1,5,'96','',''),(93,'2014-04-25 00:00:00',4,'3011300924','2013-12-27 00:00:00','BY ROAD','MH 12 GR 5470',45750.00,0,53974.94,'016','-','-','0',0,'2014-04-25 10:45:00','2014-04-25 10:45:00',45750.00,12,2,1,5,'97','',''),(94,'2014-04-26 00:00:00',7,'3051845','2014-04-01 00:00:00','BY ROAD','-',130400.00,0,153843.31,'023','-','-','30 DAYS',0,'2014-04-26 10:00:00','2014-04-26 10:00:00',130400.00,12,2,1,5,'98','',''),(95,'2014-04-26 00:00:00',7,'3053354','2014-04-19 00:00:00','BY ROAD','MH 12 GR 5470',22890.00,0,27005.17,'024','-','-','30 DAYS',0,'2014-04-26 10:13:00','2014-04-26 10:13:00',22890.00,12,2,1,5,'99','',''),(96,'2014-04-26 00:00:00',34,'P2P/000018/1415','2014-04-09 00:00:00','BY ROAD','-',19000.00,0,21775.37,'025','-','-','0',0,'2014-04-26 14:49:00','2014-04-26 14:49:00',19000.00,12,2,1,2,'100','',''),(97,'2014-04-26 00:00:00',7,'3053984','2014-04-26 00:00:00','BY ROAD','MH 12 FD 5098',49800.00,0,58753.04,'-','-','-','30 DAYS',0,'2014-04-26 14:56:00','2014-04-26 14:56:00',49800.00,12,2,1,5,'101','',''),(98,'2014-04-27 00:00:00',17,'4100071070','2014-03-21 00:00:00','BY ROAD','-',181450.00,0,207954.76,'-','-','-','30 DAYS',0,'2014-04-27 14:27:00','2014-04-27 14:27:00',181450.00,12,2,1,2,'102','','');
/*!40000 ALTER TABLE `inv_hdr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_hdr_bkp`
--

DROP TABLE IF EXISTS `inv_hdr_bkp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_hdr_bkp` (
  `inv_id` int(10) unsigned NOT NULL DEFAULT '0',
  `inv_date` datetime NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `purchase_order_no` text,
  `purchase_order_date` datetime NOT NULL,
  `mode_of_transport` varchar(45) DEFAULT NULL,
  `vehicle_no` varchar(45) DEFAULT NULL,
  `net_total_amount` float NOT NULL,
  `freight_insurance` float NOT NULL,
  `grand_total` float NOT NULL,
  `tcNo` varchar(45) DEFAULT NULL,
  `irNo` varchar(45) DEFAULT NULL,
  `lrNo` varchar(45) DEFAULT NULL,
  `paymentTerms` varchar(45) DEFAULT NULL,
  `pkg_frwd_chg` float NOT NULL,
  `inv_issue_date` datetime DEFAULT NULL,
  `removal_date` datetime DEFAULT NULL,
  `li_amount_total` float NOT NULL,
  `bed_rate` float NOT NULL,
  `ed_cess_rate` float NOT NULL,
  `shs_cess` float NOT NULL,
  `vat_or_cst` float NOT NULL,
  `inv_no` varchar(45) DEFAULT NULL,
  `delivery_to` varchar(200) DEFAULT NULL,
  `delivery_address` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_hdr_bkp`
--

LOCK TABLES `inv_hdr_bkp` WRITE;
/*!40000 ALTER TABLE `inv_hdr_bkp` DISABLE KEYS */;
INSERT INTO `inv_hdr_bkp` VALUES (1,'2014-03-26 00:00:00',1,'1','2014-03-26 00:00:00','Truck','MH 12 AB 1234',1500,50,1550,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(2,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(3,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(4,'2014-03-03 00:00:00',0,'123','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(35,'2014-03-18 00:00:00',0,'150','2014-03-24 00:00:00','By Road','123456A',0,0,0,'123','Test321','123','New',0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(36,'2014-03-11 00:00:00',0,'0','2014-03-26 00:00:00','New','abcd',101000,500,101500,'Test','Test','123','New',1000,'2014-03-31 00:00:00','2014-03-31 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(37,'2014-03-17 00:00:00',0,'0','2014-03-17 00:00:00','Test','abcd',200,20,30,'Test','Test','123','New',120,'2014-03-09 00:00:00','2014-03-16 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(38,'2014-03-17 00:00:00',1,'0','2014-03-18 00:00:00','By Road','abcd',50,50,50,'Test','Test321','123','New',50,'2014-03-17 00:00:00','2014-03-18 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(39,'2014-03-16 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',50,50,50,'2134','3234','123','321',50,'2014-03-16 00:00:00','2014-03-17 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(40,'2014-03-17 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',4550,50,50,'2134','3234','123','321',50,'2014-03-24 00:00:00','2014-03-25 00:00:00',4500,0,0,0,0,NULL,NULL,NULL),(41,'2014-03-10 00:00:00',1,'1234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',0,0,0,'1001','1002','123','321',0,'2014-03-10 00:00:00','2014-03-10 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(42,'2014-03-10 00:00:00',1,'1250','2014-03-10 00:00:00','By Road','MH 70 AB 1234',2450,100,2844,'1001','1002','123','New',50,'2014-03-10 00:00:00','2014-03-10 00:00:00',2400,0,0,0,0,NULL,NULL,NULL),(43,'2014-03-11 00:00:00',1,'550','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1550,200,1936,'2134','3234','123','321',50,'2014-03-10 00:00:00','2014-03-11 00:00:00',1500,0,0,0,0,NULL,NULL,NULL),(44,'2014-03-18 00:00:00',1,'234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',1750,1000,2960,'2134','3234','123','321',500,'2014-03-10 00:00:00','2014-03-10 00:00:00',1250,0,0,0,0,NULL,NULL,NULL),(45,'2014-03-17 00:00:00',1,'0','2014-03-26 00:00:00','By Road','MH 70 AB 1234',300,100,436,'2134','3234','123','321',100,'2014-03-17 00:00:00','2014-03-18 00:00:00',200,0,0,0,0,NULL,NULL,NULL),(46,'2014-03-10 00:00:00',1,'1200','2014-03-17 00:00:00','By Road','MH 70 AB 1234',0,150,0,'2134','3234','123','321',250,'2014-03-11 00:00:00','2014-03-18 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(47,'2014-03-18 00:00:00',1,'0','2014-03-10 00:00:00','By Road','MH 70 AB 1234',6700,200,7704,'2134','3234','123','321',200,'2014-03-17 00:00:00','2014-03-17 00:00:00',6500,0,0,0,0,NULL,NULL,NULL),(48,'2014-03-17 00:00:00',1,'1500','2014-03-17 00:00:00','By Road','MH 70 AB 1234',2124,500,2878.88,'2134','123','123','123',100,'2014-03-17 00:00:00','2014-03-11 00:00:00',2024,0,0,0,0,NULL,NULL,NULL),(49,'2014-03-18 00:00:00',1,'0','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1500,1000,2859.56,'2134','123','123','123',500,'2014-03-18 00:00:00','2014-03-18 00:00:00',1000,12,2,1,5,NULL,NULL,NULL),(50,'2014-04-04 00:00:00',3,'3800054427','2014-03-08 00:00:00','BY ROAD','-',39774,0,46924.6,'001','-','-','30 DAYS',0,'2014-04-04 14:16:00','2014-04-04 14:16:00',39774,12,2,1,5,'002','',''),(51,'2014-04-01 00:00:00',4,'1011302279','2014-03-25 00:00:00','BY ROAD','-',245220,0,289306,'','-','-','0',0,'2014-04-01 15:52:00','2014-04-01 15:52:00',245220,12,2,1,5,'001',NULL,NULL),(52,'2014-04-04 00:00:00',5,'PO/2013/2590','2014-03-12 00:00:00','BY ROAD','-',23400,0,27606.8,'-','-','-','0',0,'2014-04-04 13:38:00','2014-04-04 13:38:00',23400,12,2,1,5,'003','',''),(53,'2014-04-04 00:00:00',6,'RD/PO/B-0283','2014-03-10 00:00:00','BY ROAD','-',159375,0,188027,'002','-','-','0',0,'2014-04-04 12:58:00','2014-04-04 12:58:00',159375,12,2,1,5,'004','',''),(54,'2014-04-04 00:00:00',6,'RD/PO/B-0284','2014-03-16 00:00:00','BY ROAD','-',13875,0,16369.5,'003','-','-','0',0,'2014-04-04 12:00:00','2014-04-04 13:40:00',13875,12,2,1,5,'005','',''),(55,'2014-04-06 00:00:00',12,'TMPL/R/1202/L(`R)/336','2014-03-11 00:00:00','BY ROAD','-',37000,0,43651.9,'-','-','-','0',0,'2014-04-06 15:31:00','2014-04-06 15:31:00',37000,12,2,1,5,'006','',''),(56,'2014-04-06 00:00:00',13,'4100071070','2014-03-21 00:00:00','BY ROAD','-',125800,0,144176,'-','-','-','0',0,'2014-04-06 14:50:00','2014-04-06 14:50:00',125800,12,2,1,2,'007','',''),(57,'2014-04-07 00:00:00',4,'1011302264','2014-03-24 00:00:00','BY ROAD','-',116937,0,137960,'004, 005','-','-','0',0,'2014-04-07 15:42:00','2014-04-07 15:42:00',116937,12,2,1,5,'008','',''),(58,'2014-04-08 00:00:00',10,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240,0,1185310,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240,12,2,1,2,'009','',''),(59,'2014-04-08 00:00:00',13,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240,0,1185310,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240,12,2,1,2,'009','','');
/*!40000 ALTER TABLE `inv_hdr_bkp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_hdr_bkp1`
--

DROP TABLE IF EXISTS `inv_hdr_bkp1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_hdr_bkp1` (
  `inv_id` int(10) unsigned NOT NULL DEFAULT '0',
  `inv_date` datetime NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `purchase_order_no` text,
  `purchase_order_date` datetime NOT NULL,
  `mode_of_transport` varchar(45) DEFAULT NULL,
  `vehicle_no` varchar(45) DEFAULT NULL,
  `net_total_amount` float NOT NULL,
  `freight_insurance` float NOT NULL,
  `grand_total` float NOT NULL,
  `tcNo` varchar(45) DEFAULT NULL,
  `irNo` varchar(45) DEFAULT NULL,
  `lrNo` varchar(45) DEFAULT NULL,
  `paymentTerms` varchar(45) DEFAULT NULL,
  `pkg_frwd_chg` float NOT NULL,
  `inv_issue_date` datetime DEFAULT NULL,
  `removal_date` datetime DEFAULT NULL,
  `li_amount_total` float NOT NULL,
  `bed_rate` float NOT NULL,
  `ed_cess_rate` float NOT NULL,
  `shs_cess` float NOT NULL,
  `vat_or_cst` float NOT NULL,
  `inv_no` varchar(45) DEFAULT NULL,
  `delivery_to` varchar(200) DEFAULT NULL,
  `delivery_address` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_hdr_bkp1`
--

LOCK TABLES `inv_hdr_bkp1` WRITE;
/*!40000 ALTER TABLE `inv_hdr_bkp1` DISABLE KEYS */;
INSERT INTO `inv_hdr_bkp1` VALUES (1,'2014-03-26 00:00:00',1,'1','2014-03-26 00:00:00','Truck','MH 12 AB 1234',1500,50,1550,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(2,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(3,'2014-03-03 00:00:00',0,'150','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(4,'2014-03-03 00:00:00',0,'123','2014-03-03 00:00:00','Test',NULL,0,0,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(35,'2014-03-18 00:00:00',0,'150','2014-03-24 00:00:00','By Road','123456A',0,0,0,'123','Test321','123','New',0,NULL,NULL,0,0,0,0,0,NULL,NULL,NULL),(36,'2014-03-11 00:00:00',0,'0','2014-03-26 00:00:00','New','abcd',101000,500,101500,'Test','Test','123','New',1000,'2014-03-31 00:00:00','2014-03-31 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(37,'2014-03-17 00:00:00',0,'0','2014-03-17 00:00:00','Test','abcd',200,20,30,'Test','Test','123','New',120,'2014-03-09 00:00:00','2014-03-16 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(38,'2014-03-17 00:00:00',1,'0','2014-03-18 00:00:00','By Road','abcd',50,50,50,'Test','Test321','123','New',50,'2014-03-17 00:00:00','2014-03-18 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(39,'2014-03-16 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',50,50,50,'2134','3234','123','321',50,'2014-03-16 00:00:00','2014-03-17 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(40,'2014-03-17 00:00:00',1,'0','2014-03-24 00:00:00','By Road','Test 123',4550,50,50,'2134','3234','123','321',50,'2014-03-24 00:00:00','2014-03-25 00:00:00',4500,0,0,0,0,NULL,NULL,NULL),(41,'2014-03-10 00:00:00',1,'1234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',0,0,0,'1001','1002','123','321',0,'2014-03-10 00:00:00','2014-03-10 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(42,'2014-03-10 00:00:00',1,'1250','2014-03-10 00:00:00','By Road','MH 70 AB 1234',2450,100,2844,'1001','1002','123','New',50,'2014-03-10 00:00:00','2014-03-10 00:00:00',2400,0,0,0,0,NULL,NULL,NULL),(43,'2014-03-11 00:00:00',1,'550','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1550,200,1936,'2134','3234','123','321',50,'2014-03-10 00:00:00','2014-03-11 00:00:00',1500,0,0,0,0,NULL,NULL,NULL),(44,'2014-03-18 00:00:00',1,'234','2014-03-10 00:00:00','By Road','MH 70 AB 1234',1750,1000,2960,'2134','3234','123','321',500,'2014-03-10 00:00:00','2014-03-10 00:00:00',1250,0,0,0,0,NULL,NULL,NULL),(45,'2014-03-17 00:00:00',1,'0','2014-03-26 00:00:00','By Road','MH 70 AB 1234',300,100,436,'2134','3234','123','321',100,'2014-03-17 00:00:00','2014-03-18 00:00:00',200,0,0,0,0,NULL,NULL,NULL),(46,'2014-03-10 00:00:00',1,'1200','2014-03-17 00:00:00','By Road','MH 70 AB 1234',0,150,0,'2134','3234','123','321',250,'2014-03-11 00:00:00','2014-03-18 00:00:00',0,0,0,0,0,NULL,NULL,NULL),(47,'2014-03-18 00:00:00',1,'0','2014-03-10 00:00:00','By Road','MH 70 AB 1234',6700,200,7704,'2134','3234','123','321',200,'2014-03-17 00:00:00','2014-03-17 00:00:00',6500,0,0,0,0,NULL,NULL,NULL),(48,'2014-03-17 00:00:00',1,'1500','2014-03-17 00:00:00','By Road','MH 70 AB 1234',2124,500,2878.88,'2134','123','123','123',100,'2014-03-17 00:00:00','2014-03-11 00:00:00',2024,0,0,0,0,NULL,NULL,NULL),(49,'2014-03-18 00:00:00',1,'0','2014-03-18 00:00:00','By Road','MH 70 AB 1234',1500,1000,2859.56,'2134','123','123','123',500,'2014-03-18 00:00:00','2014-03-18 00:00:00',1000,12,2,1,5,NULL,NULL,NULL),(50,'2014-04-04 00:00:00',3,'3800054427','2014-03-08 00:00:00','BY ROAD','-',39774,0,46924.6,'001','-','-','30 DAYS',0,'2014-04-04 14:16:00','2014-04-04 14:16:00',39774,12,2,1,5,'002','',''),(51,'2014-04-01 00:00:00',4,'1011302279','2014-03-25 00:00:00','BY ROAD','-',245220,0,289306,'','-','-','0',0,'2014-04-01 15:52:00','2014-04-01 15:52:00',245220,12,2,1,5,'001',NULL,NULL),(52,'2014-04-04 00:00:00',5,'PO/2013/2590','2014-03-12 00:00:00','BY ROAD','-',23400,0,27606.8,'-','-','-','0',0,'2014-04-04 13:38:00','2014-04-04 13:38:00',23400,12,2,1,5,'003','',''),(53,'2014-04-04 00:00:00',6,'RD/PO/B-0283','2014-03-10 00:00:00','BY ROAD','-',159375,0,188027,'002','-','-','0',0,'2014-04-04 12:58:00','2014-04-04 12:58:00',159375,12,2,1,5,'004','',''),(54,'2014-04-04 00:00:00',6,'RD/PO/B-0284','2014-03-16 00:00:00','BY ROAD','-',13875,0,16369.5,'003','-','-','0',0,'2014-04-04 12:00:00','2014-04-04 13:40:00',13875,12,2,1,5,'005','',''),(55,'2014-04-06 00:00:00',12,'TMPL/R/1202/L(`R)/336','2014-03-11 00:00:00','BY ROAD','-',37000,0,43651.9,'-','-','-','0',0,'2014-04-06 15:31:00','2014-04-06 15:31:00',37000,12,2,1,5,'006','',''),(56,'2014-04-06 00:00:00',13,'4100071070','2014-03-21 00:00:00','BY ROAD','-',125800,0,144176,'-','-','-','0',0,'2014-04-06 14:50:00','2014-04-06 14:50:00',125800,12,2,1,2,'007','',''),(57,'2014-04-07 00:00:00',4,'1011302264','2014-03-24 00:00:00','BY ROAD','-',116937,0,137960,'004, 005','-','-','0',0,'2014-04-07 15:42:00','2014-04-07 15:42:00',116937,12,2,1,5,'008','',''),(58,'2014-04-08 00:00:00',10,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240,0,1185310,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240,12,2,1,2,'009','',''),(59,'2014-04-08 00:00:00',13,'4100525638/ 0','2013-12-24 00:00:00','BY ROAD','-',1034240,0,1185310,'-','-','0','60 DAYS',0,'2014-04-08 14:45:00','2014-04-08 14:45:00',1034240,12,2,1,2,'009','',''),(60,'2014-04-09 00:00:00',7,'3050265','2014-03-11 00:00:00','BY ROAD','-',72690,0,85758.2,'-','-','-','0',0,'2014-04-09 10:16:00','2014-04-09 10:16:00',72690,12,2,1,5,'60','',''),(61,'2014-04-09 00:00:00',7,'3047184','2014-01-31 00:00:00','BY ROAD','-',54654,0,64479.7,'-','-','-','0',0,'2014-04-09 10:23:00','2014-04-09 10:23:00',54654,12,2,1,5,'61','',''),(62,'2014-04-09 00:00:00',31,'3130003604','2014-02-15 00:00:00','BY ROAD','-',709994,0,813704,'-','-','-','AGAINST DELIVERY',3994,'2014-04-09 10:36:00','2014-04-09 10:36:00',706000,12,2,1,2,'62','',''),(63,'2014-04-09 00:00:00',32,'4505015012','2014-02-13 00:00:00','BY ROAD','-',198800,0,234540,'-','-','-','0',0,'2014-04-09 10:46:00','2014-04-09 10:46:00',198800,12,2,1,5,'63','','');
/*!40000 ALTER TABLE `inv_hdr_bkp1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_line_item`
--

DROP TABLE IF EXISTS `inv_line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_line_item` (
  `inv_line_item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `part_id` int(10) unsigned NOT NULL,
  `package_desc` text,
  `quantity_no` int(10) unsigned NOT NULL,
  `quantity_kgs` float NOT NULL,
  `unit` varchar(45) NOT NULL,
  `rate` float NOT NULL,
  `amount` float NOT NULL,
  `inv_id` int(10) unsigned NOT NULL,
  `no_of_pkgs` varchar(45) DEFAULT NULL,
  `serial_no` int(11) DEFAULT NULL,
  `grade_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`inv_line_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_line_item`
--

LOCK TABLES `inv_line_item` WRITE;
/*!40000 ALTER TABLE `inv_line_item` DISABLE KEYS */;
INSERT INTO `inv_line_item` VALUES (1,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(2,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(3,2,'Test',10,0,'',0,0,0,NULL,NULL,NULL),(4,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(5,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(6,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(7,0,'A',0,0,'0.0',0,0,0,NULL,NULL,NULL),(8,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(9,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(10,2,'TestNew',0,0,'',0,0,0,NULL,NULL,NULL),(11,5,'NewDesc',200,200,'kg',200,200,0,NULL,NULL,NULL),(12,5,'NewDesc1',0,0,'kg',0,0,0,NULL,NULL,NULL),(13,5,'New Part',0,0,'kg',0,0,33,NULL,NULL,NULL),(14,5,'NewPart5',1,2,'kg',3,4,33,NULL,NULL,NULL),(15,2,'TestPart',200,10,'kg',50,1200,33,NULL,NULL,NULL),(16,2,'New Part123',5,500,'kg',100,50000,36,NULL,NULL,NULL),(17,5,'Testing',10,20,'kg',200,500,38,NULL,NULL,NULL),(18,5,'Testing',10,20,'kg',50,1000,37,NULL,NULL,NULL),(19,5,'Test',20,10,'kg',10,100,40,NULL,NULL,NULL),(20,2,'New',30,20,'no',30,900,40,NULL,NULL,NULL),(21,2,'Testing',10,20,'kg',20,400,40,NULL,NULL,NULL),(22,2,'Testing',10,20,'kg',100,2000,40,NULL,NULL,NULL),(23,2,'Testing',10,20,'kg',50,1000,40,NULL,NULL,NULL),(24,2,'Testing',10,20,'kg',50,1000,40,NULL,NULL,NULL),(25,2,'Testing',30,20,'no',30,900,40,NULL,NULL,NULL),(26,2,'Temp',10,20,'kg',50,1000,40,NULL,NULL,NULL),(27,2,'Temp',30,40,'no',30,900,40,NULL,NULL,NULL),(28,5,'Testing',10,20,'no',10,100,40,NULL,NULL,NULL),(29,2,'Testing',10,20,'kg',20,400,40,NULL,NULL,NULL),(30,2,'Testing',10,20,'kg',10,200,40,NULL,NULL,NULL),(31,2,'Testing',10,20,'kg',10,200,42,NULL,NULL,NULL),(32,5,'Testing',20,30,'no',10,200,42,NULL,NULL,NULL),(33,5,'Testing',20,200,'kg',10,2000,42,NULL,NULL,NULL),(34,5,'Testing',10,150,'kg',10,1500,43,NULL,NULL,NULL),(35,2,'Testing',12,100,'kg',10,1000,44,NULL,NULL,NULL),(36,5,'Testing',5,50,'no',50,250,44,NULL,NULL,NULL),(37,2,'Testing',10,20,'kg',10,200,45,NULL,NULL,NULL),(38,2,'Testing',10,20,'kg',250,5000,47,NULL,NULL,NULL),(39,5,'Temp',10,0,'no',150,1500,47,NULL,NULL,NULL),(40,2,'Testing',100,20,'kg',100,2000,48,NULL,NULL,NULL),(41,0,'This is a very large string. This streing has to be broken down into multiple lines. PLease do it as',12,12,'kg',2,24,48,NULL,NULL,NULL),(42,2,'Package desc',10,10,'kg',100,1000,49,NULL,NULL,NULL),(43,6,'ITEM NO 10 PART NO X24018000109  80  OD  X  500  LG  MATL PB2',1,0,'NO',19887,19887,50,'-',832,3),(46,6,'ITEM NO 10 PART NO X24018000109 80 OD X 500 LG (200 + 300) MATL PB2',1,0,'NO',19887,19887,50,'-',832,3),(47,7,'CODE PLANT 20016082 545 OD X 485 ID X 350 LG MATL PB2',0,152,'KG',915,139080,51,'LOOSE',10,10),(48,8,'CODE PLANT 20016084 HFD 455 OD X 400 ID  X 350 LG MATL PB2',0,116,'KG',915,106140,51,'LOOSE',11,10),(50,10,'MODEL NO RD-100 (305 X 36) X (295 X 339) X 245 ID X 375 LG MATL CUSN7PB15',0,72,'KG',750,54000,53,'LOOSE',820,NULL),(52,11,'MODEL NO RD-100 (415 X 15) X (406 X 295) X 355 ID X 310 LG MATL CUSN7PB15',0,85.8,'KG',750,64350,53,'LOOSE',821,NULL),(54,12,'MODEL NO RD-100 252 OD X 40  ID X 42 LG MATL CUSN7PB15 ',0,18.5,'KG',750,13875,53,'LOOSE',822,NULL),(55,13,'MODEL NO RD-100 252 OD X 42 LG MATL CUSN7PB15',0,19,'KG',750,14250,53,'LOOSE',823,NULL),(57,14,'MODEL NO RD-100 485 OD X 380 ID X 27 LG MATL CUSN7PB15',0,17.2,'KG',750,12900,53,'LOOSE',824,NULL),(60,15,'MODE NO RD-100 422 OD X 335 ID X 40 THK MATL CUSN7PB15',1,18.5,'KG',750,13875,54,'LOOSE',819,NULL),(61,24,'DRG NO 6046 MD T 18 202/106 (85 OD X 55 ID X 60 LG MATL LTB3',16,0,'NO',1377,22032,55,'W BOX',828,NULL),(62,24,'DRG NO 6046 MD T 18 205/117 65 OD X 35 ID X 28 LG MATL LTB3',4,0,'NO',494,1976,55,'W BOX',827,NULL),(63,24,'DRG NO 6046 MD T 18 205/123 54 OD X 26 ID X 24 LG MATL LTB3',8,0,'NO',427,3416,55,'W BOX',826,NULL),(64,24,'DRG NO 6046 MD T 18 205/127 (115 X 8) X (105 X 68) X 85 ID X 76 LG MATL LTB3',6,0,'NO',1596,9576,55,'W BOX',825,NULL),(65,24,'ITEM NO 00010 MATL CODE 200014185  1050 OD X 1011 ID X 220 LG MATL LB2',1,0,'NO',125800,125800,56,'W BOX',833,NULL),(66,24,'CODE PLANT 20005705 HFD 105 OD X 65 ID X 225 LG MATL SAE62',3,0,'NO',10065,30195,57,'LOOSE',13,NULL),(67,16,'CODE PLANT 20003235 HFD 100 OD X 75 ID X 160 LG MATL PB2',2,9.7,'KG',915,8875.5,57,'LOOSE',14,NULL),(70,24,'CODE PLANT 20005705 HFD 105 OD X 65 ID X 225 LG MATL SAE62',3,0,'NO',10065,30195,57,'LOOSE',15,NULL),(73,16,'CODE PLANT 20003232 HFD 135 OD X 95 ID X 205 LG MATL PB2',4,52.1,'KG',915,47671.5,57,'LOOSE',16,NULL),(78,32,'MATL NO H-010200MSQ11-215FTP-01 ITEM NO 20 MATL. ASTM B 427 UNS C 90800 HB 560, 563, 567, 570',4,0,'NO',258560,1034240,59,'W BOX',640,NULL),(80,22,'ITEM NO 97-43673 DWG NO 212093 REV NO 7 MATL AL.111.5475.03',5,0,'NO',9960,49800,60,'LOOSE',829,7),(81,21,'ITEM CODE 97-30282 DWG NO 526204 REV 4 MATL EN1982-CC491 K-GZ',35,0,'NO',654,22890,60,'LOOSE',830,8),(84,20,'ITEM CODE 518404-01 DWG NO 518404 REV 7 MATL EN 1982-CC491 K-GZ',25,0,'NO',2010,50250,61,'LOOSE',755,8),(85,19,'ITEM CODE 519390-01 DWG NO 519390 REV 3 MATL EN 1982-CC491K -GS',1,0,'NO',4404,4404,61,'LOOSE',756,9),(88,36,'PART NO 3015932 607 OD X 553 ID X 831 LG MATL PB2',1,0,'NO',358000,358000,62,'W BOX',791,NULL),(89,37,'PART NO 3015933 607 OD X 553 ID X 806 LG MATL PB2',1,0,'NO',348000,348000,62,'W BOX',790,NULL),(90,17,'ITEM 00010, 00020 NO 102314375 DWG NO 15A22-M3063-PM SIZE 31 X 66 X 66 X 656 MATL CUSN12NI',4,0,'NO',9350,37400,63,'LOOSE',777,NULL),(91,17,'ITEM 00030, 00040 NO 102314374 DWG NO 15A22-M3062-PM SIZE 31 X 66 X 616 MATL CUSN12NI',4,0,'NO',8800,35200,63,'LOOSE',779,NULL),(92,0,'ITEM 00050, 00070 NO 105890144 SIZE 26 X 66 X 316 MATL C 91700',8,0,'NO',3500,28000,63,'LOOSE',782,NULL),(93,0,'ITEM 00060, 00080 NO 105890075 SIZE 26 X 66 X 416 MATL C 91700',8,0,'NO',4900,39200,63,'LOOSE',781,NULL),(94,0,'ITEM 00090, 00100 NO 105966428 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,63,'LOOSE',785,NULL),(95,0,'ITEM 00110, 00120 NO 1055966424 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,63,'LOOSE',787,NULL),(96,32,'MATL NO H-010200MSQ11-215FTP-01 ITEM NO 20 MATL. ASTM B 427 UNS C 90800 HB 560, 563, 567, 570',4,0,'NO',258560,1034240,58,'W BOX',640,NULL),(98,40,'AS PER DWG NO NHE-S.ABN.100.4.18.RO (171 X 65) X (148 X 530) X 107 ID X 595 LG MATL PB2',1,0,'NO',60000,60000,65,'LOOSE',17,NULL),(99,24,'355  OD  X  295  ID  X  205  LG  MATL PB2',1,0,'NO',51062.5,51062.5,66,'LOOSE',46,NULL),(103,7,'DRG NO p45-06 SIZE 342 OD x 305 ID x 310 LGMATL PH BRONZE (ONE SET=ONE NO)',1,0,'NO',45787.5,45787.5,67,'W BOX',24,NULL),(106,7,'DRG NO P-39-09/1 (322 X 16) X (302 X 214) X 270 ID X 246 LG MATL PH BRONZE(TWO SET= TWO NOS)',2,0,'NO',30293.8,60587.5,67,'W BOX',25,NULL),(107,7,'DRG NO P-21-12 SIZE (255 X 12.5) X (235 X 115) X 205 ID X 140 LG MATL PH BRONZE (ONE SET = ONE NO )',1,0,'NO',13412.5,13412.5,67,'W BOX',26,NULL),(109,9,'ITEM CODE JPHASPHOBB DRG NO 039-04-025 816 OD X 755 ID X 605 LG MATL PB2',1,0,'NO',375250,375250,68,'W BOX',798,NULL),(110,9,'ITEM CODE MPHASPB617 DRG NO 039-04-026 816 OD X 755 ID X 617 LG MATL PB2',1,0,'NO',382850,382850,68,'W BOX',799,NULL),(111,24,'FOR CLAMPPING CYL 16.471 FOR 750T TAGGING PRESS AJAX LINE MATL CODE X12503020885 114  OD X 77 ID X 103 LG MATL CUSN12PB',2,0,'NO',4800,9600,69,'LOOSE',49,NULL),(113,9,'ITEM CODE 04260002053 205 OD X 175 ID X 200 LG. MATL. PB2',1,0,'NO',19000,19000,71,'W BOX',55,NULL),(114,41,'MATL CODE X12503020857 DRG NO 8.0000.60.16.476   1120 OD X 1045 ID X 585 LG MATL CU31 1 NO = 1 SET',1,0,'NO',1070000,1070000,70,'LOOSE',759,NULL),(116,15,'MATL CODE X12503020078 409.9 OD X 370.06 ID X 10 THK MATL RG7',1,0,'NO',5990,5990,72,'LOOSE',585,NULL),(118,16,'MATL CODE S06101000097 638 OD X 575 ID X 475 LG MATL PB2',1,0,'NO',277500,277500,73,'LOOSE',56,NULL),(120,44,'MATL CODE O11301000084 990 X 150 X 20 THK MATL PB2',1,0,'NO',29160,29160,74,'LOOSE',57,NULL),(122,16,'MATL CODE S06101000097 638 OD X 575 ID X 475 LG MATL PB2',1,0,'NO',277500,277500,75,'LOOSE',56,NULL),(123,17,'ITEM 00010, 00020 NO 102314375 DWG NO 15A22-M3063-PM SIZE 31 X 66 X 66 X 656 MATL CUSN12NI',4,0,'NO',9350,37400,76,'LOOSE',777,NULL),(124,17,'ITEM 00030, 00040 NO 102314374 DWG NO 15A22-M3062-PM SIZE 31 X 66 X 616 MATL CUSN12NI',4,0,'N',8800,35200,76,'LOOSE',779,NULL),(125,38,'ITEM 00050, 00070 NO 105890144 SIZE 26 X 66 X 316 MATL C 91700',8,0,'NO',3500,28000,76,'LOOSE',782,NULL),(126,38,'ITEM 00060, 00080 NO 105890075 SIZE 26 X 66 X 416 MATL C 91700',8,0,'NO',4900,39200,76,'LOOSE',781,NULL),(127,39,'ITEM 00090, 00100 NO 105966428 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,76,'LOOSE',785,NULL),(128,39,'ITEM 00110, 00120 NO 1055966424 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,76,'LOOSE',787,NULL),(130,9,'CODE NO 14013056  145 OD X 70 ID X 35 THK MATL PB',6,23.4,'KG',1000,23400,52,'LOOSE',834,NULL),(131,45,'test',10,10,'kg',100,1000,80,'loose',1,3),(132,45,'LOOSE',10,20,'kg',20,400,80,'Loose',2,12),(133,18,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,81,'Loose',22,10),(134,26,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,51,'Loose',22,10),(135,18,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,51,'Loose',22,10),(136,46,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,82,'Loose',22,10),(137,46,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,83,'Loose',22,10),(138,47,'ITEM CODE P1M400000010 280 OD X 210 ID X 50 LG MATL BS 1400 PB2',2,0,'no',10500,21000,84,'Loose',836,3),(145,25,'ITEM 00050 NO 102468716 DWG NO 10339624C-W-MPM SIZE 14 X 113 X 472 MATL C90700 ',6,0,'no',5600,33600,87,'Loose',38,0),(146,25,'ITEM 00090 NO 102468691 DWG NO 10339625C-W-MPM SIZE 14 X 44 X 472 MATL C90700 ',6,0,'no',2200,13200,87,'Loose',39,0),(147,25,'ITEM 00100 NO 102468705 DWG NO 10339625G-W-MPM SIZE 14 X 44 X 592 MATL C90700 ',6,0,'no',2600,15600,87,'Loose',40,0),(148,16,'ITEM 00130 NO 102638978  DWG NO 10320626-W-MPM SIZE 201 OD X 100 ID X 60 THK MATL C86300 ',4,0,'no',6900,27600,88,'Loose',41,0),(149,16,'ITEM 00140 NO 102638980  DWG NO 10320627-W-MPM SIZE 201 OD X 100 ID X 60 THK MATL C86300 ',4,0,'no',6900,27600,88,'Loose',42,0),(150,16,'ITEM 00130 NO 102638978  DWG NO 10320626-W-MPM SIZE 200 OD X 106 ID X 55 THK MATL C86300 ',4,0,'no',6900,27600,87,'Loose',41,0),(151,16,'ITEM 00140 NO 102638980  DWG NO 10320627-W-MPM SIZE 200 OD X 106 ID X 55 THK MATL C86300 ',4,0,'no',6900,27600,87,'Loose',42,0),(153,50,'M3003 ITEM 00010 NO 106016424  SIZE : 485 OD X 415 X 490 LG  MATL SAE 430 Gr B',1,0,'no',206500,206500,86,'Loose',20,0),(157,49,'ITEM 00010 NO 106015404 DWG  24421002-M1194-DLZ-MPM SIZE 46 X 212 X 858  MATL CuZn25Al5Mn4Fe3',2,0,'no',52000,104000,89,'Loose',8,0),(158,49,'ITEM 00020 NO 106015417 DWG  24421002-M1192-DLZ-MPM SIZE 46 X 212 X 536  MATL CuZn25Al5Mn4Fe3',2,0,'no',32750,65500,89,'Loose',9,0),(159,50,'M3003 ITEM 00010 NO 106016424  SIZE : 485 OD X 415 X 490 LG  MATL SAE 430 Gr B',1,0,'no',206500,206500,90,'Loose',20,0),(160,25,'ITEM 00050 NO 102468716 DWG NO 10339624C-W-MPM SIZE 14 X 113 X 472 MATL C90700 ',6,0,'no',5600,33600,91,'Loose',38,0),(161,25,'ITEM 00090 NO 102468691 DWG NO 10339625C-W-MPM SIZE 14 X 44 X 472 MATL C90700 ',6,0,'no',2200,13200,91,'Loose',39,0),(162,25,'ITEM 00100 NO 102468705 DWG NO 10339625G-W-MPM SIZE 14 X 44 X 592 MATL C90700 ',6,0,'no',2600,15600,91,'Loose',40,0),(163,16,'ITEM 00130 NO 102638978  DWG NO 10320626-W-MPM SIZE 200 OD X 106 ID X 55 THK MATL C86300 ',4,0,'no',6900,27600,91,'Loose',41,0),(164,16,'ITEM 00140 NO 102638980  DWG NO 10320627-W-MPM SIZE 200 OD X 106 ID X 55 THK MATL C86300 ',4,0,'no',6900,27600,91,'Loose',42,0),(165,16,'CODE PLANT 20003241 MFD 85 OD X 63 ID X 375 LG MATL PB2',8,64,'kg',915,58560,92,'Loose',12,0),(166,16,'CODE PLANT 20003241 MFD 85 OD X 63 ID X 375 LG MATL PB2',6,50,'kg',915,45750,93,'Loose',12,0),(167,46,'ITEM NO 547764-01 DWG NO 547764 REV NO 4 MATL EN 1982-CC333G-GZ',10,0,'no',13040,130400,94,'Loose',22,10),(168,21,'ITEM CODE 97-30282 DWG NO 526204 REV NO 4 MATL EN 1982-CC491K-GZ',35,0,'no',654,22890,95,'Loose',71,8),(169,9,'ITEM CODE 04260002053 205 OD X 175 ID X 200 LG MATL PB2',1,0,'no',19000,19000,96,'GUNNY BUNDLE',55,0),(170,22,'ITEM  CODE 97-43673 DWG NO 212093 REV NO 7  MATL AL.111.5475.03',5,0,'no',9960,49800,97,'Loose',73,7),(171,51,'ITEM ITEM NO 00010 MATL CODE 200014185  SIZE 1050 OD X 1011 ID X 220 LG MATL LB2',1,0,'no',181450,181450,98,'W BOX',72,34);
/*!40000 ALTER TABLE `inv_line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_line_item_22_04`
--

DROP TABLE IF EXISTS `inv_line_item_22_04`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv_line_item_22_04` (
  `inv_line_item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `part_id` int(10) unsigned NOT NULL,
  `package_desc` text,
  `quantity_no` int(10) unsigned NOT NULL,
  `quantity_kgs` float NOT NULL,
  `unit` varchar(45) NOT NULL,
  `rate` float NOT NULL,
  `amount` float NOT NULL,
  `inv_id` int(10) unsigned NOT NULL,
  `no_of_pkgs` varchar(45) DEFAULT NULL,
  `serial_no` int(11) DEFAULT NULL,
  `grade_id` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_line_item_22_04`
--

LOCK TABLES `inv_line_item_22_04` WRITE;
/*!40000 ALTER TABLE `inv_line_item_22_04` DISABLE KEYS */;
INSERT INTO `inv_line_item_22_04` VALUES (1,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(2,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(3,2,'Test',10,0,'',0,0,0,NULL,NULL,NULL),(4,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(5,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(6,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(7,0,'A',0,0,'0.0',0,0,0,NULL,NULL,NULL),(8,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(9,0,'',0,0,'',0,0,0,NULL,NULL,NULL),(10,2,'TestNew',0,0,'',0,0,0,NULL,NULL,NULL),(11,5,'NewDesc',200,200,'kg',200,200,0,NULL,NULL,NULL),(12,5,'NewDesc1',0,0,'kg',0,0,0,NULL,NULL,NULL),(13,5,'New Part',0,0,'kg',0,0,33,NULL,NULL,NULL),(14,5,'NewPart5',1,2,'kg',3,4,33,NULL,NULL,NULL),(15,2,'TestPart',200,10,'kg',50,1200,33,NULL,NULL,NULL),(16,2,'New Part123',5,500,'kg',100,50000,36,NULL,NULL,NULL),(17,5,'Testing',10,20,'kg',200,500,38,NULL,NULL,NULL),(18,5,'Testing',10,20,'kg',50,1000,37,NULL,NULL,NULL),(19,5,'Test',20,10,'kg',10,100,40,NULL,NULL,NULL),(20,2,'New',30,20,'no',30,900,40,NULL,NULL,NULL),(21,2,'Testing',10,20,'kg',20,400,40,NULL,NULL,NULL),(22,2,'Testing',10,20,'kg',100,2000,40,NULL,NULL,NULL),(23,2,'Testing',10,20,'kg',50,1000,40,NULL,NULL,NULL),(24,2,'Testing',10,20,'kg',50,1000,40,NULL,NULL,NULL),(25,2,'Testing',30,20,'no',30,900,40,NULL,NULL,NULL),(26,2,'Temp',10,20,'kg',50,1000,40,NULL,NULL,NULL),(27,2,'Temp',30,40,'no',30,900,40,NULL,NULL,NULL),(28,5,'Testing',10,20,'no',10,100,40,NULL,NULL,NULL),(29,2,'Testing',10,20,'kg',20,400,40,NULL,NULL,NULL),(30,2,'Testing',10,20,'kg',10,200,40,NULL,NULL,NULL),(31,2,'Testing',10,20,'kg',10,200,42,NULL,NULL,NULL),(32,5,'Testing',20,30,'no',10,200,42,NULL,NULL,NULL),(33,5,'Testing',20,200,'kg',10,2000,42,NULL,NULL,NULL),(34,5,'Testing',10,150,'kg',10,1500,43,NULL,NULL,NULL),(35,2,'Testing',12,100,'kg',10,1000,44,NULL,NULL,NULL),(36,5,'Testing',5,50,'no',50,250,44,NULL,NULL,NULL),(37,2,'Testing',10,20,'kg',10,200,45,NULL,NULL,NULL),(38,2,'Testing',10,20,'kg',250,5000,47,NULL,NULL,NULL),(39,5,'Temp',10,0,'no',150,1500,47,NULL,NULL,NULL),(40,2,'Testing',100,20,'kg',100,2000,48,NULL,NULL,NULL),(41,0,'This is a very large string. This streing has to be broken down into multiple lines. PLease do it as',12,12,'kg',2,24,48,NULL,NULL,NULL),(42,2,'Package desc',10,10,'kg',100,1000,49,NULL,NULL,NULL),(43,6,'ITEM NO 10 PART NO X24018000109  80  OD  X  500  LG  MATL PB2',1,0,'NO',19887,19887,50,'-',832,NULL),(46,6,'ITEM NO 10 PART NO X24018000109 80 OD X 500 LG (200 + 300) MATL PB2',1,0,'NO',19887,19887,50,'-',832,NULL),(47,7,'CODE PLANT 20016082 545 OD X 485 ID X 350 LG MATL PB2',0,152,'KG',915,139080,51,'LOOSE',10,10),(48,8,'CODE PLANT 20016084 HFD 455 OD X 400 ID  X 350 LG MATL PB2',0,116,'KG',915,106140,51,'LOOSE',11,10),(50,10,'MODEL NO RD-100 (305 X 36) X (295 X 339) X 245 ID X 375 LG MATL CUSN7PB15',0,72,'KG',750,54000,53,'LOOSE',820,NULL),(52,11,'MODEL NO RD-100 (415 X 15) X (406 X 295) X 355 ID X 310 LG MATL CUSN7PB15',0,85.8,'KG',750,64350,53,'LOOSE',821,NULL),(54,12,'MODEL NO RD-100 252 OD X 40  ID X 42 LG MATL CUSN7PB15 ',0,18.5,'KG',750,13875,53,'LOOSE',822,NULL),(55,13,'MODEL NO RD-100 252 OD X 42 LG MATL CUSN7PB15',0,19,'KG',750,14250,53,'LOOSE',823,NULL),(57,14,'MODEL NO RD-100 485 OD X 380 ID X 27 LG MATL CUSN7PB15',0,17.2,'KG',750,12900,53,'LOOSE',824,NULL),(60,15,'MODE NO RD-100 422 OD X 335 ID X 40 THK MATL CUSN7PB15',1,18.5,'KG',750,13875,54,'LOOSE',819,NULL),(61,24,'DRG NO 6046 MD T 18 202/106 (85 OD X 55 ID X 60 LG MATL LTB3',16,0,'NO',1377,22032,55,'W BOX',828,NULL),(62,24,'DRG NO 6046 MD T 18 205/117 65 OD X 35 ID X 28 LG MATL LTB3',4,0,'NO',494,1976,55,'W BOX',827,NULL),(63,24,'DRG NO 6046 MD T 18 205/123 54 OD X 26 ID X 24 LG MATL LTB3',8,0,'NO',427,3416,55,'W BOX',826,NULL),(64,24,'DRG NO 6046 MD T 18 205/127 (115 X 8) X (105 X 68) X 85 ID X 76 LG MATL LTB3',6,0,'NO',1596,9576,55,'W BOX',825,NULL),(65,24,'ITEM NO 00010 MATL CODE 200014185  1050 OD X 1011 ID X 220 LG MATL LB2',1,0,'NO',125800,125800,56,'W BOX',833,NULL),(66,24,'CODE PLANT 20005705 HFD 105 OD X 65 ID X 225 LG MATL SAE62',3,0,'NO',10065,30195,57,'LOOSE',13,NULL),(67,16,'CODE PLANT 20003235 HFD 100 OD X 75 ID X 160 LG MATL PB2',2,9.7,'KG',915,8875.5,57,'LOOSE',14,NULL),(70,24,'CODE PLANT 20005705 HFD 105 OD X 65 ID X 225 LG MATL SAE62',3,0,'NO',10065,30195,57,'LOOSE',15,NULL),(73,16,'CODE PLANT 20003232 HFD 135 OD X 95 ID X 205 LG MATL PB2',4,52.1,'KG',915,47671.5,57,'LOOSE',16,NULL),(78,32,'MATL NO H-010200MSQ11-215FTP-01 ITEM NO 20 MATL. ASTM B 427 UNS C 90800 HB 560, 563, 567, 570',4,0,'NO',258560,1034240,59,'W BOX',640,NULL),(80,22,'ITEM NO 97-43673 DWG NO 212093 REV NO 7 MATL AL.111.5475.03',5,0,'NO',9960,49800,60,'LOOSE',829,NULL),(81,21,'ITEM CODE 97-30282 DWG NO 526204 REV 4 MATL EN1982-CC491 K-GZ',35,0,'NO',654,22890,60,'LOOSE',830,NULL),(84,20,'ITEM CODE 518404-01 DWG NO 518404 REV 7 MATL EN 1982-CC491 K-GZ',25,0,'NO',2010,50250,61,'LOOSE',755,NULL),(85,19,'ITEM CODE 519390-01 DWG NO 519390 REV 3 MATL EN 1982-CC491K -GS',1,0,'NO',4404,4404,61,'LOOSE',756,NULL),(88,36,'PART NO 3015932 607 OD X 553 ID X 831 LG MATL PB2',1,0,'NO',358000,358000,62,'W BOX',791,NULL),(89,37,'PART NO 3015933 607 OD X 553 ID X 806 LG MATL PB2',1,0,'NO',348000,348000,62,'W BOX',790,NULL),(90,17,'ITEM 00010, 00020 NO 102314375 DWG NO 15A22-M3063-PM SIZE 31 X 66 X 66 X 656 MATL CUSN12NI',4,0,'NO',9350,37400,63,'LOOSE',777,NULL),(91,17,'ITEM 00030, 00040 NO 102314374 DWG NO 15A22-M3062-PM SIZE 31 X 66 X 616 MATL CUSN12NI',4,0,'NO',8800,35200,63,'LOOSE',779,NULL),(92,0,'ITEM 00050, 00070 NO 105890144 SIZE 26 X 66 X 316 MATL C 91700',8,0,'NO',3500,28000,63,'LOOSE',782,NULL),(93,0,'ITEM 00060, 00080 NO 105890075 SIZE 26 X 66 X 416 MATL C 91700',8,0,'NO',4900,39200,63,'LOOSE',781,NULL),(94,0,'ITEM 00090, 00100 NO 105966428 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,63,'LOOSE',785,NULL),(95,0,'ITEM 00110, 00120 NO 1055966424 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,63,'LOOSE',787,NULL),(96,32,'MATL NO H-010200MSQ11-215FTP-01 ITEM NO 20 MATL. ASTM B 427 UNS C 90800 HB 560, 563, 567, 570',4,0,'NO',258560,1034240,58,'W BOX',640,NULL),(98,40,'AS PER DWG NO NHE-S.ABN.100.4.18.RO (171 X 65) X (148 X 530) X 107 ID X 595 LG MATL PB2',1,0,'NO',60000,60000,65,'LOOSE',17,NULL),(99,24,'355  OD  X  295  ID  X  205  LG  MATL PB2',1,0,'NO',51062.5,51062.5,66,'LOOSE',46,NULL),(103,7,'DRG NO p45-06 SIZE 342 OD x 305 ID x 310 LGMATL PH BRONZE (ONE SET=ONE NO)',1,0,'NO',45787.5,45787.5,67,'W BOX',24,NULL),(106,7,'DRG NO P-39-09/1 (322 X 16) X (302 X 214) X 270 ID X 246 LG MATL PH BRONZE(TWO SET= TWO NOS)',2,0,'NO',30293.8,60587.5,67,'W BOX',25,NULL),(107,7,'DRG NO P-21-12 SIZE (255 X 12.5) X (235 X 115) X 205 ID X 140 LG MATL PH BRONZE (ONE SET = ONE NO )',1,0,'NO',13412.5,13412.5,67,'W BOX',26,NULL),(109,9,'ITEM CODE JPHASPHOBB DRG NO 039-04-025 816 OD X 755 ID X 605 LG MATL PB2',1,0,'NO',375250,375250,68,'W BOX',798,NULL),(110,9,'ITEM CODE MPHASPB617 DRG NO 039-04-026 816 OD X 755 ID X 617 LG MATL PB2',1,0,'NO',382850,382850,68,'W BOX',799,NULL),(111,24,'FOR CLAMPPING CYL 16.471 FOR 750T TAGGING PRESS AJAX LINE MATL CODE X12503020885 114  OD X 77 ID X 103 LG MATL CUSN12PB',2,0,'NO',4800,9600,69,'LOOSE',49,NULL),(113,9,'ITEM CODE 04260002053 205 OD X 175 ID X 200 LG. MATL. PB2',1,0,'NO',19000,19000,71,'W BOX',55,NULL),(114,41,'MATL CODE X12503020857 DRG NO 8.0000.60.16.476   1120 OD X 1045 ID X 585 LG MATL CU31 1 NO = 1 SET',1,0,'NO',1070000,1070000,70,'LOOSE',759,NULL),(116,15,'MATL CODE X12503020078 409.9 OD X 370.06 ID X 10 THK MATL RG7',1,0,'NO',5990,5990,72,'LOOSE',585,NULL),(118,16,'MATL CODE S06101000097 638 OD X 575 ID X 475 LG MATL PB2',1,0,'NO',277500,277500,73,'LOOSE',56,NULL),(120,44,'MATL CODE O11301000084 990 X 150 X 20 THK MATL PB2',1,0,'NO',29160,29160,74,'LOOSE',57,NULL),(122,16,'MATL CODE S06101000097 638 OD X 575 ID X 475 LG MATL PB2',1,0,'NO',277500,277500,75,'LOOSE',56,NULL),(123,17,'ITEM 00010, 00020 NO 102314375 DWG NO 15A22-M3063-PM SIZE 31 X 66 X 66 X 656 MATL CUSN12NI',4,0,'NO',9350,37400,76,'LOOSE',777,NULL),(124,17,'ITEM 00030, 00040 NO 102314374 DWG NO 15A22-M3062-PM SIZE 31 X 66 X 616 MATL CUSN12NI',4,0,'N',8800,35200,76,'LOOSE',779,NULL),(125,38,'ITEM 00050, 00070 NO 105890144 SIZE 26 X 66 X 316 MATL C 91700',8,0,'NO',3500,28000,76,'LOOSE',782,NULL),(126,38,'ITEM 00060, 00080 NO 105890075 SIZE 26 X 66 X 416 MATL C 91700',8,0,'NO',4900,39200,76,'LOOSE',781,NULL),(127,39,'ITEM 00090, 00100 NO 105966428 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,76,'LOOSE',785,NULL),(128,39,'ITEM 00110, 00120 NO 1055966424 SIZE 46 X 86 X 541 MATL CUSN12NI',2,0,'NO',14750,29500,76,'LOOSE',787,NULL),(130,9,'CODE NO 14013056  145 OD X 70 ID X 35 THK MATL PB',6,23.4,'KG',1000,23400,52,'LOOSE',834,NULL),(131,45,'test',10,10,'kg',100,1000,80,'loose',1,3),(132,45,'LOOSE',10,20,'kg',20,400,80,'Loose',2,12),(133,18,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,81,'Loose',22,10),(134,26,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,51,'Loose',22,10),(135,18,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,51,'Loose',22,10),(136,46,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,82,'Loose',22,10),(137,46,'ITEM CODE 547764-01 DWG NO 547764 REV NO 4 MATL EN1982-CC333G-GZ',10,0,'no',13040,130400,83,'Loose',22,10),(138,47,'ITEM CODE P1M400000010 280 OD X 210 ID X 50 LG MATL BS 1400 PB2',2,0,'no',10500,21000,84,'Loose',836,3);
/*!40000 ALTER TABLE `inv_line_item_22_04` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_item`
--

DROP TABLE IF EXISTS `line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_item` (
  `line_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_order_id` int(11) NOT NULL,
  `description` text,
  `quantity` float DEFAULT NULL,
  `unit` text,
  `rate` float DEFAULT NULL,
  `status` text,
  `part_no` int(10) unsigned NOT NULL,
  `pending_quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`line_item_id`),
  KEY `purchase_order_id` (`purchase_order_id`),
  CONSTRAINT `line_item_ibfk_1` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_item`
--

LOCK TABLES `line_item` WRITE;
/*!40000 ALTER TABLE `line_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mech_prop_master`
--

DROP TABLE IF EXISTS `mech_prop_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mech_prop_master` (
  `mech_prop_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `property_name` varchar(45) NOT NULL,
  PRIMARY KEY (`mech_prop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mech_prop_master`
--

LOCK TABLES `mech_prop_master` WRITE;
/*!40000 ALTER TABLE `mech_prop_master` DISABLE KEYS */;
INSERT INTO `mech_prop_master` VALUES (1,'Hardness'),(2,'Elongation %');
/*!40000 ALTER TABLE `mech_prop_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mechanical_properties_master`
--

DROP TABLE IF EXISTS `mechanical_properties_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mechanical_properties_master` (
  `mpm_id` int(11) NOT NULL AUTO_INCREMENT,
  `mpm_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`mpm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mechanical_properties_master`
--

LOCK TABLES `mechanical_properties_master` WRITE;
/*!40000 ALTER TABLE `mechanical_properties_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `mechanical_properties_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_grade_mapping`
--

DROP TABLE IF EXISTS `part_grade_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_grade_mapping` (
  `part_id` int(10) unsigned NOT NULL,
  `grade_id` int(10) unsigned NOT NULL,
  `part_grade_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`part_grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_grade_mapping`
--

LOCK TABLES `part_grade_mapping` WRITE;
/*!40000 ALTER TABLE `part_grade_mapping` DISABLE KEYS */;
INSERT INTO `part_grade_mapping` VALUES (45,12,12),(45,3,13),(18,10,14),(46,10,15),(47,3,16),(48,3,17),(48,13,18),(6,3,19),(19,9,20),(20,8,21),(21,8,22),(22,7,23),(23,6,24),(51,34,25);
/*!40000 ALTER TABLE `part_grade_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_grade_mapping_22_04`
--

DROP TABLE IF EXISTS `part_grade_mapping_22_04`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_grade_mapping_22_04` (
  `part_id` int(10) unsigned NOT NULL,
  `grade_id` int(10) unsigned NOT NULL,
  `part_grade_id` int(10) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_grade_mapping_22_04`
--

LOCK TABLES `part_grade_mapping_22_04` WRITE;
/*!40000 ALTER TABLE `part_grade_mapping_22_04` DISABLE KEYS */;
INSERT INTO `part_grade_mapping_22_04` VALUES (45,12,12),(45,3,13),(18,10,14),(46,10,15),(47,3,16),(48,3,17),(48,13,18);
/*!40000 ALTER TABLE `part_grade_mapping_22_04` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_master`
--

DROP TABLE IF EXISTS `part_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_master` (
  `part_id` int(11) NOT NULL AUTO_INCREMENT,
  `part_name` text,
  `part_drg_no` text,
  `part_rate` float DEFAULT NULL,
  `pm_size` float DEFAULT NULL,
  `part_uom` text,
  `grade` text,
  `cast_weight` float DEFAULT NULL,
  `proof_machine_weight` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  PRIMARY KEY (`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_master`
--

LOCK TABLES `part_master` WRITE;
/*!40000 ALTER TABLE `part_master` DISABLE KEYS */;
INSERT INTO `part_master` VALUES (6,'G M BAR','-',0,0,'NOS','BS 1400 PB2',0,0,0),(7,'P/M SPLIT BUSH','-',0,0,'NO',NULL,0,0,0),(8,'P/M SPLIT THRUST BUSH','-',0,0,'NO',NULL,0,0,0),(9,'P. B. BUSH','-',0,0,'NO',NULL,0,0,0),(10,'ECC BUSH RAW','-',0,0,'KG',NULL,0,0,0),(11,'FRAME BUSH RAW','-',0,0,'KG',NULL,0,0,0),(12,'BOTTOM THRUST PLATE','-',0,0,'KG','CUSN7PB15',0,0,0),(13,'TOP THRUST PLATE','-',0,0,'KG','CUSN7PB15',0,0,0),(14,'FLANGE','-',0,0,'KG','CUSN7PB15',0,0,0),(15,'GUIDE RING','-',0,0,'NOS',NULL,0,0,0),(16,'P/M BUSH','-',0,0,'NO',NULL,0,0,0),(17,'P/M LINER','-',0,0,'NO',NULL,0,0,0),(18,'OPERATING SLIDE FOR PAPX307','547764',0,0,'NO',NULL,0,0,0),(19,'LOCK RING FOR MOPX207','519390',0,0,'NO','EN 1982 CC491K-GS',0,0,0),(20,'LOCK RING FOR MAB206','518404',0,0,'NO','EN 1982 CC491K-GZ',0,0,0),(21,'LOCK RING FOR MAB103','526204',0,0,'NO','EN 1982 CC491K-GZ',0,0,0),(22,'BOTTOM BEARING BUSH FOR 3191M','212093',0,0,'NO','AL.111.5475.03',0,0,0),(23,'BUSHING','785585',0,0,'NO','EN 1982 CC492K',0,0,0),(24,'BUSH','-',0,0,'KG',NULL,0,0,0),(25,'LINER','-',0,0,'KG',NULL,0,0,0),(26,'','',0,0,'','',0,0,0),(27,'','',0,0,'','',0,0,0),(28,'BEARING BUSH','-',0,0,'NO',NULL,0,0,0),(29,'OUTER BEARING STRIP','-',0,0,'NO','',0,0,0),(30,'INNER BEARING STRIP','-',0,0,'NO','',0,0,0),(31,'DUST SEAL','-',0,0,'NO','',0,0,0),(32,'GREASED BRONZE BEARING','-',0,0,'NO','',0,0,0),(33,'P/M RING','-',0,0,'NO','',0,0,0),(34,'P/M BUSH FOR HOUSING','-',0,0,'NO','',0,0,0),(35,'P/M WORM WHEEL','-',0,0,'NO','',0,0,0),(36,'JOURNAL CLUTCH','-',0,0,'NO','',0,0,0),(37,'JOURNAL BRAKE','-',0,0,'NO','',0,0,0),(38,'P/M SLIDE LEDGE','-',0,0,'NO','',0,0,0),(39,'P/M SLIDING LINER','-',0,0,'NO','',0,0,0),(40,'P/M GUIDE BUSH','-',0,0,'NO','',0,0,0),(41,'P/M BUSH PITMAN BIG END','DRG NO8.0000.60.16.476',0,0,'NO','-',0,0,0),(42,'GUIDE TING','-',0,0,'NO','',0,0,0),(43,'GUIDE RING','-',0,0,'NO','',0,0,0),(44,'RECTANGLE PLATE','-',0,0,'NO','',0,0,0),(45,'TestPartTemp','123',0,10,'KG',NULL,10,10,10),(46,'OPERATING SLIDE FOR PAPX 307','547764',0,0,'NOS',NULL,0,0,0),(47,'DUST SEAL BUSH ','-',0,0,'NOS',NULL,0,0,0),(48,'TRAVAGRATE BEARING BUSH ','-',0,0,'NOS',NULL,0,0,0),(49,'FRONT FACE WEAT PLATE','-',0,0,'NOS',NULL,0,0,0),(50,'BEARING RING','-',0,0,'NOS',NULL,0,0,0),(51,'VERSON BUSH','-',0,0,'NO',NULL,0,0,0);
/*!40000 ALTER TABLE `part_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `gross_price` float DEFAULT NULL,
  `net_value` float DEFAULT NULL,
  `purchaseOrderNo` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
INSERT INTO `purchase_order` VALUES (4,'Test1237',NULL,NULL,''),(5,'Abcd',NULL,NULL,''),(6,'Test12345',0,0,''),(7,'Test12375',NULL,NULL,'');
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_certificates`
--

DROP TABLE IF EXISTS `test_certificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_certificates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_certificates`
--

LOCK TABLES `test_certificates` WRITE;
/*!40000 ALTER TABLE `test_certificates` DISABLE KEYS */;
INSERT INTO `test_certificates` VALUES (1,'','',0,0),(2,'','',0,0);
/*!40000 ALTER TABLE `test_certificates` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-27 16:22:24
