CREATE DATABASE studentdb
    DEFAULT CHARACTER SET = 'utf8mb4';
use studentdb;
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) ,
                       age INTEGER
);
