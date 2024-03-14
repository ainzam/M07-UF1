-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Temps de generació: 10-03-2024 a les 00:46:03
-- Versió del servidor: 10.4.27-MariaDB
-- Versió de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de dades: `orders`
--

-- --------------------------------------------------------

--
-- Estructura de la taula `addresses`
--

CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL,
  `recipientName` varchar(255) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `state` varchar(150) DEFAULT NULL,
  `country` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `items`
--

CREATE TABLE `items` (
  `reference` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` double NOT NULL,
  `image` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `orders`
--

CREATE TABLE `orders` (
  `reference` bigint(20) NOT NULL,
  `client_username` varchar(255) NOT NULL,
  `deliveryAddress_id` bigint(20) NOT NULL,
  `startDate` datetime(6) DEFAULT NULL,
  `deliveryDate` datetime(6) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `totalQuantity` int(11) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `orders_items`
--

CREATE TABLE `orders_items` (
  `order_reference` bigint(20) NOT NULL,
  `item_reference` bigint(20) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índexs per a les taules bolcades
--

--
-- Índexs per a la taula `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- Índexs per a la taula `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`reference`);

--
-- Índexs per a la taula `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`reference`),
  ADD UNIQUE KEY `UK_fnnbgwan86a7sbyyk5sf75mkd` (`deliveryAddress_id`),
  ADD KEY `FKk5gjboftdpqsvqx0mrrv3a4f` (`client_username`);

--
-- Índexs per a la taula `orders_items`
--
ALTER TABLE `orders_items`
  ADD PRIMARY KEY (`item_reference`,`order_reference`),
  ADD KEY `FK40vpoftpboamey6j7qiht2hml` (`order_reference`);

--
-- Índexs per a la taula `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT per les taules bolcades
--

--
-- AUTO_INCREMENT per la taula `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la taula `items`
--
ALTER TABLE `items`
  MODIFY `reference` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la taula `orders`
--
ALTER TABLE `orders`
  MODIFY `reference` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restriccions per a les taules bolcades
--

--
-- Restriccions per a la taula `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKk5gjboftdpqsvqx0mrrv3a4f` FOREIGN KEY (`client_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `FKtcj2xbl89q1ydb3y1sf0hnqmi` FOREIGN KEY (`deliveryAddress_id`) REFERENCES `addresses` (`id`);

--
-- Restriccions per a la taula `orders_items`
--
ALTER TABLE `orders_items`
  ADD CONSTRAINT `FK40vpoftpboamey6j7qiht2hml` FOREIGN KEY (`order_reference`) REFERENCES `orders` (`reference`),
  ADD CONSTRAINT `FK45vkh3kt8x13ehgrlskket0k7` FOREIGN KEY (`item_reference`) REFERENCES `items` (`reference`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
