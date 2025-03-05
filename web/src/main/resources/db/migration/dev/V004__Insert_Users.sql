-- Las contraseñas originales son: admin, user1, user2, user3, user4, user5, user6, user7, user8, user9
-- Todas han sido cifradas con BCrypt antes de insertarlas en la base de datos


INSERT INTO users (email, password, name, address, language, admin) VALUES
    ('admin@example.com', '$2b$12$uHew8tLdvwJ7wtVpBVi0PuF341w4vEemA/IFIJw2fkgGRMqg7Cp0q', 'Admin User', '999 Admin Street', 'en', 1),
    ('user1@example.com', '$2b$12$lYGDbb/dw0FIQKIRqiiUsO59bNwyzUKOQ1zC.l6IBNW51HA8KIPEu', 'John Doe', '123 Maple Street', 'es', 0),
    ('user2@example.com', '$2b$12$RGhRa1oiMVo0hjYN5PXDbeUYw0EQ.Ohd/GskPvu3kD2t7G2pYm6v2', 'Jane Smith', '456 Oak Avenue', 'en', 0),
    ('user3@example.com', '$2b$12$1OEgIg1z8P0qPrAHVIDpI.tu4j8GLA9Be4.v.0q6Rum6TLTNDn34W', 'Alice Johnson', '789 Pine Road', 'es', 0),
    ('user4@example.com', '$2b$12$x1zlwzpHreUxUW5GpEHPI.kD7GBayvpJ4vDz3CS4DCY70mucpkGf2', 'Bob Brown', '321 Cedar Lane', 'en', 0),
    ('user5@example.com', '$2b$12$FaVIRo9JAMAOLoebZMXOHe7U.CKGhmh5Fw80P3w4cBcSQ2BHxjoFa', 'Charlie Davis', '654 Elm Street', 'es', 0),
    ('user6@example.com', '$2b$12$ssGYgsU0E0SQgpxFvUcvROyMykN90Cihr0oOYqndmwE11M3krMLoq', 'Emily Clark', '987 Birch Boulevard', 'en', 0),
    ('user7@example.com', '$2b$12$38JQssritpSjLnPxPnHn5OBcH7TXWNddEHdWpYZz.8xT3FXg0.w3e', 'Daniel Miller', '123 Spruce Drive', 'es', 0),
    ('user8@example.com', '$2b$12$BHYx/xnv2j9Vbvt/nmvQhuufkMHer31VHgFSSYN.poI6DjzD6hJSq', 'Sophia Wilson', '456 Redwood Circle', 'en', 0),
    ('user9@example.com', '$2b$12$PZIFsvWnIAJuBm8vZN4gZeo.3BOovXhw7cHopSsnDobEcYob48jy.', 'James Moore', '789 Ash Terrace', 'es', 0);
