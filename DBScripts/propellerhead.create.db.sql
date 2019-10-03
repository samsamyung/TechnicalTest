CREATE DATABASE IF NOT EXISTS `propellerhead`;

CREATE USER propellerhead@'localhost' identified by '123456';
GRANT ALL PRIVILEGES ON *.* to propellerhead@'localhost';
