CREATE DATABASE  IF NOT EXISTS `rdp_cloud` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rdp_cloud`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `u_ID` int(11) NOT NULL AUTO_INCREMENT,
  `u_NAME` varchar(32) NOT NULL,
  `u_PASSWORD` varchar(32) NOT NULL,
  PRIMARY KEY (`u_ID`),
  UNIQUE KEY `user` (`u_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Table structure for table `containers`
--

DROP TABLE IF EXISTS `containers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `containers` (
  `c_ID` int(11) NOT NULL AUTO_INCREMENT,
  `c_NAME` varchar(32) NOT NULL,
  `c_DOCKERID` varchar(32) NOT NULL,
  `c_DESCRIPTION` varchar(200) DEFAULT NULL,
  `u_NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`c_ID`),
  KEY `u_NAME` (`u_NAME`),
  CONSTRAINT `containers_ibfk_1` FOREIGN KEY (`u_NAME`) REFERENCES `users` (`u_NAME`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `i_ID` int(11) NOT NULL AUTO_INCREMENT,
  `i_NAME` varchar(32) NOT NULL,
  `i_VERSION` varchar(32) NOT NULL,
  `i_CPU` decimal(6,2) NOT NULL,
  `i_RAM` decimal(6,2) NOT NULL,
  `i_STORAGE` int(11) NOT NULL,
  PRIMARY KEY (`i_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Table structure for table `containerimages`
--

DROP TABLE IF EXISTS `containerimages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `containerimages` (
  `ci_ID` int(11) NOT NULL AUTO_INCREMENT,
  `c_ID` int(11) NOT NULL,
  `i_ID` int(11) NOT NULL,
  PRIMARY KEY (`ci_ID`),
  KEY `c_ID` (`c_ID`),
  KEY `i_ID` (`i_ID`),
  CONSTRAINT `containerimages_ibfk_1` FOREIGN KEY (`c_ID`) REFERENCES `containers` (`c_ID`),
  CONSTRAINT `containerimages_ibfk_2` FOREIGN KEY (`i_ID`) REFERENCES `images` (`i_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

