-- Editoriales
INSERT INTO publishers (name)
VALUES
    ('Editorial Anagrama'),
    ('Penguin Random House Grupo Editorial España'),
    ('Editorial Planeta, S. A.'),
    ('Debolsillo'),
    ('Minotauro');

-- Autores
INSERT INTO authors (name, nationality, birth_year, death_year)
VALUES
    ('John Kennedy Toole', 'Estadounidense', 1937, 1969),
    ('Umberto Eco', 'Italiano', 1932, 2016),
    ('Milan Kundera', 'Checo', 1929, NULL),
    ('Terry Pratchett', 'Británico', 1948, 2015),
    ('Neil Gaiman', 'Británico', 1960, NULL);

-- Libros
INSERT INTO books (isbn, title_es, title_en, synopsis_es, synopsis_en, price, cover, publisher_id)
VALUES
    (
        '9788433920423',
        'La conjura de los necios',
        'A confederacy of dunces',
        'El protagonista de esta novela es uno de los personajes más memorables...',
        'A monument to sloth, rant and contempt, a behemoth of fat, flatulence and furious suspicion of anything modern - this is Ignatius J. Reilly...',
        13.20,
        'necios.jpeg',
        1
    ),
    (
        '9788426418197',
        'El nombre de la rosa',
        'The name of the rose',
        'Valiéndose de las características de la novela gótica, la crónica medieval y la novela policíaca, El nombre de la rosa narra las...',
        'The year is 1327. Franciscans in a wealthy Italian abbey are suspected of heresy, and Brother William of Baskerville arrives to investigate...',
        12.30,
        'nombreRosa.jpeg',
        2
    ),
    (
        '9786074213485',
        'La insoportable levedad del ser',
        'The unbearable lightness of being',
        'La insoportable levedad del ser es una novela del escritor checo Milan Kundera publicada en 1984. La novela es una exploración filosófica de la vida...',
        'In The Unbearable Lightness of Being, Milan Kundera tells the story of a young...',
        11.50,
        'levedad.jpeg',
        3
    ),
    (
        '9788466338141',
        'La isla del día de antes',
        'The island of the day before',
        'La isla del día de antes es una novela del escritor italiano Umberto Eco publicada en 1994. La novela es una exploración filosófica de la vida...',
        'It is the year 1643. Roberto, a young nobleman, survives war, the Bastille, exile, and shipwreck as he voyages to a Pacific island straddling the date meridian...',
        10.40,
        'islaDiaAntes.jpeg',
        4
    ),
    (
        '9788448022440',
        'Buenos presagios',
        'Good omens',
        'Buenos presagios es una novela de humor escrita por Terry Pratchett y Neil Gaiman. Fue publicada por primera vez en 1990. La novela es una narrativa de...',
        'According to The Nice and Accurate Prophecies of Agnes Nutter, Witch (the world''s only completely accurate book of prophecies, written in 1655, before she exploded),...',
        9.30,
        'buenosPresagios.jpeg',
        5
    );

-- INSERT en la tabla books_authors
INSERT INTO book_authors (book_id, author_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 2),
    (5, 4),
    (5, 5);

-- Usuarios
-- username = admin, password = admin
-- username = user1, password = user1
-- username = user2, password = user2
INSERT INTO users(username, password, email, name, surname, address, admin)
VALUES
    (
        'admin',
        '$2a$10$zAJMtKY7UbnmeXH2kXMfp.xpq99PWjafw0ZwDOzYuAtp2hT2bNJVG',
        'admin@bookstore.com',
        'Juan',
        'González López',
        'Calle Admin',
        true
    ),
    (
        'user1',
        '$2a$10$cKSdPQGs/yPIlrXSFDkIDuMLx6ylOnGSxmzSOo6nEOq7waE/unv5W',
        'user1@bookstore.com',
        'María',
        'García Pérez',
        'Calle User1',
        false
    ),
    (
        'user2',
        '$2a$10$S/PIuHAFYLpoPx8v7gmj/.HAZFnew8KKXLCwFZNGwx3yMK.gBu5YO',
        'user2@bookstore.com',
        'Ana',
        'Martínez Sánchez',
        'Calle User2',
        false
    );

-- Pedidos

INSERT INTO orders(user_id, order_date, delivery_date, total, status)
VALUES
    (2, NULL, NULL, NULL, 0),
    (3, NULL, NULL, NULL, 0),
    (2, '2023-11-30', '2023-12-05', 85.50, 4),
    (2, '2023-02-12', '2023-02-15', 13.20, 4),
    (3, '2024-03-20', '2024-03-25', 61.40, 4),
    (3, '2024-03-25', NULL, 49.20, 3),
    (2, '2024-03-29', NULL, 138.80, 2),
    (3, '2024-04-01', NULL, 129.50, 1);

INSERT INTO order_details(order_id, book_id, quantity, price)
VALUES
    (3, 2, 2, 12.30),
    (3, 4, 3, 20.30),
    (4, 1, 1, 13.20),
    (5, 3, 1, 11.50),
    (5, 4, 2, 20.30),
    (5, 5, 1, 9.30),
    (6, 2, 4, 12.30),
    (7, 1, 3, 11.20),
    (7, 3, 2, 52.60),
    (8, 1, 5, 13.20),
    (8, 3, 2, 11.50),
    (8, 4, 3, 10.40),
    (8, 5, 1, 9.30),
    (2, 1, 1, 13.20)
