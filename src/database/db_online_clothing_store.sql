-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 19, 2023 at 04:14 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_online_clothing_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `AdminUsers`
--

CREATE TABLE `AdminUsers` (
  `id` int(11) NOT NULL,
  `firstName` varchar(256) NOT NULL,
  `lastName` varchar(256) NOT NULL,
  `avatar` text DEFAULT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(512) NOT NULL,
  `lastLogin` datetime NOT NULL,
  `isLocked` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `AdminUsers`
--

INSERT INTO `AdminUsers` (`id`, `firstName`, `lastName`, `avatar`, `telephone`, `email`, `password`, `lastLogin`, `isLocked`, `createdAt`, `modifiedAt`) VALUES
(1, 'admin', 'admin', '', '0898759325', 'admin@gmail.com', '$2y$10$2vY4VCXWawZJjZd/iOqam.9SyzL1rjsD9qkMDrc/Ijaubo5ly8vsK', '2023-03-18 03:28:58', 0, '2023-03-18', '2023-03-18');

-- --------------------------------------------------------

--
-- Table structure for table `CartItems`
--

CREATE TABLE `CartItems` (
  `userId` int(11) NOT NULL,
  `productInventoryId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Coupons`
--

CREATE TABLE `Coupons` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(512) NOT NULL,
  `discountPercent` tinyint(4) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `isActived` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `OrderDetails`
--

CREATE TABLE `OrderDetails` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `subtotal` decimal(10,0) NOT NULL,
  `total` decimal(10,0) NOT NULL,
  `couponId` int(11) DEFAULT NULL,
  `paymentId` int(11) NOT NULL,
  `note` varchar(512) DEFAULT NULL,
  `apartmentNumber` varchar(256) NOT NULL,
  `street` varchar(256) NOT NULL,
  `ward` varchar(256) NOT NULL,
  `district` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `status` smallint(6) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `OrderItems`
--

CREATE TABLE `OrderItems` (
  `productInventoryId` int(11) NOT NULL,
  `orderDetailId` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `discountPercent` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  `evaluation` tinyint(4) DEFAULT NULL,
  `comment` varchar(512) DEFAULT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Payments`
--

CREATE TABLE `Payments` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ProductCategories`
--

CREATE TABLE `ProductCategories` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `isActived` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ProductInventories`
--

CREATE TABLE `ProductInventories` (
  `id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `size` varchar(32) NOT NULL,
  `color` varchar(32) NOT NULL,
  `quantity` int(11) NOT NULL,
  `isActived` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE `Products` (
  `id` int(11) NOT NULL,
  `name` varchar(512) NOT NULL,
  `image` text NOT NULL,
  `imageHover` text NOT NULL,
  `description` text NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `weight` varchar(32) NOT NULL,
  `dimensions` varchar(128) NOT NULL,
  `materials` varchar(512) NOT NULL,
  `otherInfo` varchar(512) NOT NULL,
  `importPrice` decimal(10,0) NOT NULL,
  `sellPrice` decimal(10,0) NOT NULL,
  `discountPercent` tinyint(4) NOT NULL,
  `shipPrice` decimal(10,0) NOT NULL,
  `productCategoryId` int(11) NOT NULL,
  `isActived` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `RoleAdminUsers`
--

CREATE TABLE `RoleAdminUsers` (
  `adminUserId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Roles`
--

CREATE TABLE `Roles` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `firstName` varchar(256) NOT NULL,
  `lastName` varchar(256) NOT NULL,
  `avatar` text DEFAULT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(512) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `telephone` varchar(10) NOT NULL,
  `apartmentNumber` varchar(256) NOT NULL,
  `street` varchar(256) NOT NULL,
  `ward` varchar(256) NOT NULL,
  `district` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `receiveNewsletter` tinyint(1) NOT NULL,
  `receiveOffers` tinyint(1) NOT NULL,
  `lastLogin` datetime NOT NULL,
  `isLocked` tinyint(1) NOT NULL,
  `createdAt` date NOT NULL,
  `modifiedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `firstName`, `lastName`, `avatar`, `email`, `password`, `birthdate`, `gender`, `telephone`, `apartmentNumber`, `street`, `ward`, `district`, `city`, `receiveNewsletter`, `receiveOffers`, `lastLogin`, `isLocked`, `createdAt`, `modifiedAt`) VALUES
(1, 'Pham Van', 'Hung', '', 'phamhunggl721@gmail.com', '$2y$10$2vY4VCXWawZJjZd/iOqam.9SyzL1rjsD9qkMDrc/Ijaubo5ly8vsK', '2001-03-25', 1, '0898759325', 'KTX DHQG', 'To Vinh Dien', 'Dong Hoa', 'Di An', 'Binh Duong', 1, 1, '2023-03-16 07:36:03', 0, '2023-03-16', '2023-03-16'),
(9, 'Pham Van', 'Hung', NULL, 'phamhunggl325@gmail.com', '$2a$10$4pvP0IKisEnAaIMVS5qC2ua4XLd4ToQrHFgwc60/qSex/WRtZmYnW', NULL, NULL, '0344406561', '123', 'To Vinh Dien', 'Dong Hoa', 'Di An', 'Binh Duong', 1, 0, '2023-03-19 00:06:01', 0, '2023-03-19', '2023-03-19');

-- --------------------------------------------------------

--
-- Table structure for table `Wishlist`
--

CREATE TABLE `Wishlist` (
  `productId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AdminUsers`
--
ALTER TABLE `AdminUsers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `telephone` (`telephone`);

--
-- Indexes for table `CartItems`
--
ALTER TABLE `CartItems`
  ADD KEY `fk_cart_user` (`userId`),
  ADD KEY `fk_cart_product_inventory` (`productInventoryId`);

--
-- Indexes for table `Coupons`
--
ALTER TABLE `Coupons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `OrderDetails`
--
ALTER TABLE `OrderDetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_coupon` (`couponId`),
  ADD KEY `fk_user` (`userId`),
  ADD KEY `fk_payment` (`paymentId`);

--
-- Indexes for table `OrderItems`
--
ALTER TABLE `OrderItems`
  ADD KEY `fk_product_inventory` (`productInventoryId`),
  ADD KEY `fk_order_detail` (`orderDetailId`);

--
-- Indexes for table `Payments`
--
ALTER TABLE `Payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ProductCategories`
--
ALTER TABLE `ProductCategories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `ProductInventories`
--
ALTER TABLE `ProductInventories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `product` (`productId`,`size`,`color`) USING BTREE;

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_product_category` (`productCategoryId`);

--
-- Indexes for table `RoleAdminUsers`
--
ALTER TABLE `RoleAdminUsers`
  ADD KEY `fk_admin_user` (`adminUserId`),
  ADD KEY `fk_role` (`roleId`);

--
-- Indexes for table `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `telephone` (`telephone`);

--
-- Indexes for table `Wishlist`
--
ALTER TABLE `Wishlist`
  ADD KEY `fk_wishlist_product` (`productId`),
  ADD KEY `fk_wishlist_user` (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AdminUsers`
--
ALTER TABLE `AdminUsers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Coupons`
--
ALTER TABLE `Coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `OrderDetails`
--
ALTER TABLE `OrderDetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Payments`
--
ALTER TABLE `Payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ProductCategories`
--
ALTER TABLE `ProductCategories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ProductInventories`
--
ALTER TABLE `ProductInventories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Roles`
--
ALTER TABLE `Roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `CartItems`
--
ALTER TABLE `CartItems`
  ADD CONSTRAINT `fk_cart_product_inventory` FOREIGN KEY (`productInventoryId`) REFERENCES `ProductInventories` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `OrderDetails`
--
ALTER TABLE `OrderDetails`
  ADD CONSTRAINT `fk_coupon` FOREIGN KEY (`couponId`) REFERENCES `Coupons` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_payment` FOREIGN KEY (`paymentId`) REFERENCES `Payments` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `OrderItems`
--
ALTER TABLE `OrderItems`
  ADD CONSTRAINT `fk_order_detail` FOREIGN KEY (`orderDetailId`) REFERENCES `OrderDetails` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_product_inventory` FOREIGN KEY (`productInventoryId`) REFERENCES `ProductInventories` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `ProductInventories`
--
ALTER TABLE `ProductInventories`
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `Products`
--
ALTER TABLE `Products`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`productCategoryId`) REFERENCES `ProductCategories` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `RoleAdminUsers`
--
ALTER TABLE `RoleAdminUsers`
  ADD CONSTRAINT `fk_admin_user` FOREIGN KEY (`adminUserId`) REFERENCES `AdminUsers` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`roleId`) REFERENCES `Roles` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `Wishlist`
--
ALTER TABLE `Wishlist`
  ADD CONSTRAINT `fk_wishlist_product` FOREIGN KEY (`productId`) REFERENCES `Products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
