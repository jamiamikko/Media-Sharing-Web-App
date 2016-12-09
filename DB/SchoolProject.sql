-- phpMyAdmin SQL Dump
-- version 4.4.15.8
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 09, 2016 at 07:30 AM
-- Server version: 5.5.50-MariaDB
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SchoolProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `Feedback`
--

CREATE TABLE IF NOT EXISTS `Feedback` (
  `ID` int(7) NOT NULL,
  `Owner` int(7) DEFAULT NULL,
  `OnContent` int(7) DEFAULT NULL,
  `OnFeedback` int(7) DEFAULT NULL,
  `Content` varchar(200) NOT NULL,
  `UploadDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Img`
--

CREATE TABLE IF NOT EXISTS `Img` (
  `ID` int(7) NOT NULL,
  `Owner` int(7) DEFAULT NULL,
  `URL` varchar(200) NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `UploadDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Usr`
--

CREATE TABLE IF NOT EXISTS `Usr` (
  `ID` int(7) NOT NULL,
  `PRIVILEGE` tinyint(1) DEFAULT '0',
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(40) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Usr`
--

INSERT INTO `Usr` (`ID`, `PRIVILEGE`, `UserName`, `Password`) VALUES
(23, 0, 'encryption testing', '4u3OOebFQMvK9J4YDiWNBZFSL9qJ+DRk'),
(28, 1, 'jamiamikko', 'F7Sdlld3vwXuNZV2ITYwgg==');

-- --------------------------------------------------------

--
-- Table structure for table `_friend_of_`
--

CREATE TABLE IF NOT EXISTS `_friend_of_` (
  `User1` int(7) NOT NULL DEFAULT '0',
  `User2` int(7) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Owner` (`Owner`),
  ADD KEY `OnFeedback` (`OnFeedback`),
  ADD KEY `Feedback_ibfk_2` (`OnContent`);

--
-- Indexes for table `Img`
--
ALTER TABLE `Img`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Owner` (`Owner`),
  ADD KEY `URL` (`URL`) USING BTREE;

--
-- Indexes for table `Usr`
--
ALTER TABLE `Usr`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UserName` (`UserName`),
  ADD UNIQUE KEY `Password` (`Password`);

--
-- Indexes for table `_friend_of_`
--
ALTER TABLE `_friend_of_`
  ADD PRIMARY KEY (`User1`,`User2`),
  ADD KEY `User2` (`User2`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Feedback`
--
ALTER TABLE `Feedback`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `Img`
--
ALTER TABLE `Img`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=117;
--
-- AUTO_INCREMENT for table `Usr`
--
ALTER TABLE `Usr`
  MODIFY `ID` int(7) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD CONSTRAINT `Feedback_ibfk_2` FOREIGN KEY (`OnContent`) REFERENCES `Img` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Feedback_ibfk_1` FOREIGN KEY (`Owner`) REFERENCES `Usr` (`ID`),
  ADD CONSTRAINT `Feedback_ibfk_3` FOREIGN KEY (`OnFeedback`) REFERENCES `Feedback` (`ID`);

--
-- Constraints for table `Img`
--
ALTER TABLE `Img`
  ADD CONSTRAINT `Img_ibfk_1` FOREIGN KEY (`Owner`) REFERENCES `Usr` (`ID`);

--
-- Constraints for table `_friend_of_`
--
ALTER TABLE `_friend_of_`
  ADD CONSTRAINT `_friend_of__ibfk_2` FOREIGN KEY (`User2`) REFERENCES `Usr` (`ID`),
  ADD CONSTRAINT `_friend_of__ibfk_1` FOREIGN KEY (`User1`) REFERENCES `Usr` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
