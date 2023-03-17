-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 17, 2023 at 02:05 PM
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
-- Table structure for table `admin_users`
--

CREATE TABLE `admin_users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  `avatar` text NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(512) NOT NULL,
  `last_login` datetime NOT NULL,
  `is_locked` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `user_id` int(11) NOT NULL,
  `product_inventory_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `coupons`
--

CREATE TABLE `coupons` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(512) NOT NULL,
  `discount_percent` tinyint(4) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `is_actived` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `subtotal` decimal(10,0) NOT NULL,
  `total` decimal(10,0) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `note` varchar(512) NOT NULL,
  `apartment_number` varchar(256) NOT NULL,
  `street` varchar(256) NOT NULL,
  `ward` varchar(256) NOT NULL,
  `district` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `status` smallint(6) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `product_inventory_id` int(11) NOT NULL,
  `order_detail_id` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `discount_percent` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  `evaluation` tinyint(4) NOT NULL,
  `comment` varchar(512) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(512) NOT NULL,
  `description` text NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `weight` varchar(32) NOT NULL,
  `dimensions` varchar(128) NOT NULL,
  `materials` varchar(512) NOT NULL,
  `other_Info` varchar(512) NOT NULL,
  `import_price` decimal(10,0) NOT NULL,
  `sell_price` decimal(10,0) NOT NULL,
  `discount_percent` tinyint(4) NOT NULL,
  `ship_price` decimal(10,0) NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `is_actived` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `is_actived` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_inventories`
--

CREATE TABLE `product_inventories` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size` varchar(32) NOT NULL,
  `color` varchar(32) NOT NULL,
  `quantity` int(11) NOT NULL,
  `is_actived` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(512) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role_admin_users`
--

CREATE TABLE `role_admin_users` (
  `admin_user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  `avatar` text NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(512) NOT NULL,
  `birthdate` date NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `apartment_number` varchar(256) NOT NULL,
  `street` varchar(256) NOT NULL,
  `ward` varchar(256) NOT NULL,
  `district` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `receive_newsletter` tinyint(1) NOT NULL,
  `receive_offers` tinyint(1) NOT NULL,
  `last_login` datetime NOT NULL,
  `is_locked` tinyint(1) NOT NULL,
  `created_at` date NOT NULL,
  `modified_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `avatar`, `email`, `password`, `birthdate`, `gender`, `telephone`, `apartment_number`, `street`, `ward`, `district`, `city`, `receive_newsletter`, `receive_offers`, `last_login`, `is_locked`, `created_at`, `modified_at`) VALUES
(1, 'Pham Van', 'Hung', '', 'phamhunggl721@gmail.com', '$2y$10$2vY4VCXWawZJjZd/iOqam.9SyzL1rjsD9qkMDrc/Ijaubo5ly8vsK', '2001-03-25', 1, '0898759325', 'KTX DHQG', 'To Vinh Dien', 'Dong Hoa', 'Di An', 'Binh Duong', 1, 1, '2023-03-16 07:36:03', 0, '2023-03-16', '2023-03-16');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_users`
--
ALTER TABLE `admin_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD KEY `fk_cart_user` (`user_id`),
  ADD KEY `fk_cart_product_inventory` (`product_inventory_id`);

--
-- Indexes for table `coupons`
--
ALTER TABLE `coupons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_coupon` (`coupon_id`),
  ADD KEY `fk_user` (`user_id`),
  ADD KEY `fk_payment` (`payment_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD KEY `fk_product_inventory` (`product_inventory_id`),
  ADD KEY `fk_order_detail` (`order_detail_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_product_category` (`product_category_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `product_inventories`
--
ALTER TABLE `product_inventories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `product_id` (`product_id`,`size`,`color`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role_admin_users`
--
ALTER TABLE `role_admin_users`
  ADD KEY `fk_admin_user` (`admin_user_id`),
  ADD KEY `fk_role` (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `email_2` (`email`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD KEY `fk_wishlist_product` (`product_id`),
  ADD KEY `fk_wishlist_user` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_users`
--
ALTER TABLE `admin_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `coupons`
--
ALTER TABLE `coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_inventories`
--
ALTER TABLE `product_inventories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `fk_cart_product_inventory` FOREIGN KEY (`product_inventory_id`) REFERENCES `product_inventories` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_coupon` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_payment` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `fk_order_detail` FOREIGN KEY (`order_detail_id`) REFERENCES `order_details` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_product_inventory` FOREIGN KEY (`product_inventory_id`) REFERENCES `product_inventories` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `product_inventories`
--
ALTER TABLE `product_inventories`
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `role_admin_users`
--
ALTER TABLE `role_admin_users`
  ADD CONSTRAINT `fk_admin_user` FOREIGN KEY (`admin_user_id`) REFERENCES `admin_users` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `fk_wishlist_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
