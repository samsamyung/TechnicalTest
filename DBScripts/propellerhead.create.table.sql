
use propellerhead;

DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `note`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `creationDateTime` bigint(20) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `address3` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `creationDateTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
