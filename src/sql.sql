-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2018 at 10:19 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smarter_pos_db`
--
CREATE DATABASE IF NOT EXISTS smarter_pos_db;

-- --------------------------------------------------------

--
-- Table structure for table `incomes`
--

CREATE TABLE if NOT EXISTS `incomes` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double DEFAULT '0',
  `Notes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `incomes_date` date DEFAULT NULL,
  `id_in` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `input_box`
--

CREATE TABLE if NOT EXISTS `input_box` (
  `id_in_box` int(11) NOT NULL,
  `id_mat` int(11) NOT NULL,
  `sub_discount` double DEFAULT '0',
  `mat_price` double NOT NULL,
  `mat_count` int(11) NOT NULL,
  `input_box_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `input_box_hdr`
--

CREATE TABLE if NOT EXISTS `input_box_hdr` (
  `id_in_box` int(11) NOT NULL,
  `customer_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `in_box_date` date NOT NULL,
  `discount` double DEFAULT '0',
  `total` double NOT NULL,
  `re_purchases` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `inside_storage`
--

CREATE TABLE if NOT EXISTS `inside_storage` (
  `in_strg_id` int(11) NOT NULL,
  `id_mat` int(11) NOT NULL,
  `in_strg_date` date NOT NULL,
  `in_strg_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE if NOT EXISTS `material` (
  `id_mat` int(11) NOT NULL,
  `mat_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `unit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `notes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `barcode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `output_box`
--

CREATE TABLE if NOT EXISTS `output_box` (
  `id_out_box` int(11) NOT NULL,
  `id_mat` int(11) NOT NULL,
  `sub_discount` double DEFAULT '0',
  `mat_price` double NOT NULL,
  `mat_count` int(11) NOT NULL,
  `output_box_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `output_box_hdr`
--

CREATE TABLE if NOT EXISTS `output_box_hdr` (
  `id_out_box` int(11) NOT NULL,
  `customer_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `out_box_date` date NOT NULL,
  `discount` double DEFAULT '0',
  `total` double NOT NULL,
  `re_sales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `outside_storage`
--

CREATE TABLE if NOT EXISTS `outside_storage` (
  `out_strg_id` int(11) NOT NULL,
  `id_mat` int(11) NOT NULL,
  `out_strg_date` date NOT NULL,
  `out_strg_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `spending`
--

CREATE TABLE if NOT EXISTS `spending` (
  `id_sp` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double DEFAULT '0',
  `Notes` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `sp_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE if NOT EXISTS `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pass_word` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `pass_word`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `incomes`
--
ALTER TABLE `incomes`
  ADD PRIMARY KEY (`id_in`);

--
-- Indexes for table `input_box`
--
ALTER TABLE `input_box`
  ADD PRIMARY KEY (`input_box_id`),
  ADD KEY `id_in_box` (`id_in_box`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `input_box_hdr`
--
ALTER TABLE `input_box_hdr`
  ADD PRIMARY KEY (`id_in_box`);

--
-- Indexes for table `inside_storage`
--
ALTER TABLE `inside_storage`
  ADD PRIMARY KEY (`in_strg_id`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indexes for table `output_box`
--
ALTER TABLE `output_box`
  ADD PRIMARY KEY (`output_box_id`),
  ADD KEY `id_out_box` (`id_out_box`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `output_box_hdr`
--
ALTER TABLE `output_box_hdr`
  ADD PRIMARY KEY (`id_out_box`);

--
-- Indexes for table `outside_storage`
--
ALTER TABLE `outside_storage`
  ADD PRIMARY KEY (`out_strg_id`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `spending`
--
ALTER TABLE `spending`
  ADD PRIMARY KEY (`id_sp`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `incomes`
--
ALTER TABLE `incomes`
  MODIFY `id_in` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `input_box`
--
ALTER TABLE `input_box`
  MODIFY `input_box_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `input_box_hdr`
--
ALTER TABLE `input_box_hdr`
  MODIFY `id_in_box` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `inside_storage`
--
ALTER TABLE `inside_storage`
  MODIFY `in_strg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id_mat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `output_box`
--
ALTER TABLE `output_box`
  MODIFY `output_box_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `output_box_hdr`
--
ALTER TABLE `output_box_hdr`
  MODIFY `id_out_box` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `outside_storage`
--
ALTER TABLE `outside_storage`
  MODIFY `out_strg_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `spending`
--
ALTER TABLE `spending`
  MODIFY `id_sp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `input_box`
--
ALTER TABLE `input_box`
  ADD CONSTRAINT `input_box_ibfk_1` FOREIGN KEY (`id_in_box`) REFERENCES `input_box_hdr` (`id_in_box`),
  ADD CONSTRAINT `input_box_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `material` (`id_mat`);

--
-- Constraints for table `inside_storage`
--
ALTER TABLE `inside_storage`
  ADD CONSTRAINT `inside_storage_ibfk_1` FOREIGN KEY (`id_mat`) REFERENCES `material` (`id_mat`);

--
-- Constraints for table `output_box`
--
ALTER TABLE `output_box`
  ADD CONSTRAINT `output_box_ibfk_1` FOREIGN KEY (`id_out_box`) REFERENCES `output_box_hdr` (`id_out_box`),
  ADD CONSTRAINT `output_box_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `material` (`id_mat`);

--
-- Constraints for table `outside_storage`
--
ALTER TABLE `outside_storage`
  ADD CONSTRAINT `outside_storage_ibfk_1` FOREIGN KEY (`id_mat`) REFERENCES `material` (`id_mat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;