DROP DATABASE IF EXISTS library;
CREATE DATABASE library;
use library;

CREATE TABLE publishers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    title_es VARCHAR(255) NOT NULL,
    title_en VARCHAR(255) NOT NULL,    
    synopsis_es TEXT,
    synopsis_en TEXT,
    price DECIMAL(10, 2) NOT NULL,
    cover VARCHAR(255),
    publisher_id INT,
    FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);


CREATE TABLE authors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(255),
    birth_year INT,
    death_year INT
);

CREATE TABLE book_authors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    author_id INT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    order_date DATE,
    delivery_date DATE,
    status INT NOT NULL DEFAULT 0, -- 0: carrito de la compra, 1: Pedido, 2: in process, 3: sent, 4: received
    total DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    book_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

