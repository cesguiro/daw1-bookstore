CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       language VARCHAR(3) NOT NULL DEFAULT 'es',
                       admin BOOLEAN NOT NULL DEFAULT 0
);