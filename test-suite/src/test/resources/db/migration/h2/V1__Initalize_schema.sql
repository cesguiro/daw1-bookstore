CREATE TABLE publishers (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            slug VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE books (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       isbn VARCHAR(13) UNIQUE NOT NULL,
                       title_es VARCHAR(255) NOT NULL,
                       title_en VARCHAR(255) NOT NULL,
                       synopsis_es TEXT,
                       synopsis_en TEXT,
                       base_price DECIMAL(10, 2) NOT NULL,
                       discount_percentage DECIMAL(4, 2) DEFAULT 0,
                       cover VARCHAR(255),
                       publication_date DATE,
                       publisher_id INT,
                       FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);

CREATE INDEX idx_books_publisher_id ON books (publisher_id);


CREATE TABLE authors (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         nationality VARCHAR(255),
                         biography_en TEXT,
                         biography_es TEXT,
                         birth_year INT,
                         death_year INT,
                         slug VARCHAR(255) UNIQUE
);

CREATE TABLE books_authors (
                               book_id INT,
                               author_id INT,
                               PRIMARY KEY (book_id, author_id),
                               FOREIGN KEY (book_id) REFERENCES books(id),
                               FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE INDEX idx_books_authors_book_id ON books_authors (book_id);
CREATE INDEX idx_books_authors_author_id ON books_authors (author_id);