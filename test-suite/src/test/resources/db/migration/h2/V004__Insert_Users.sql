-- Las contraseñas originales son: admin, user1, user2, user3, user4, user5, user6, user7, user8, user9
-- Todas las contraseñas han sido cifradas con SHA-256 y un salt antes de insertarlas en la base de datos

INSERT INTO users (email, password, name, address, language, admin) VALUES
    ('admin@example.com', '6554b98d37556b4bc9986d02612aa5510ceb07f4a9d34571b1728b7637c9efc6:Q45kMzavpLFWuyTV+pWnSQ==', 'Admin User', '999 Admin Street', 'en', 1),
    ('user1@example.com', '9b1c1c64a00e6ed02d0cb3200a688d61bb1c7e1f9c0abfa9156eab5274fd6fcf:ZX4ENdVsl6lJwukaZUDhvg==', 'John Doe', '123 Maple Street', 'es', 0),
    ('user2@example.com', '6817a015aee13f17fce0b1b50f3bfe96598ce4661e308663d4d0f54edca7813c:y5zl7+IOjwXlI0mFuLISjw==', 'Jane Smith', '456 Oak Avenue', 'en', 0),
    ('user3@example.com', 'd167fec1ac72e31606397dfea3f47884366cfb4a45d3640af357454e5f4c30f0:Jtd8QuGt7od7y1VFjj/onA==', 'Alice Johnson', '789 Pine Road', 'es', 0),
    ('user4@example.com', 'ddd970238625a1a92ac680986330054d1085b621d523f35963f94fdecba0d63b:6KsSslZcLdv4RLQe5DAa+g==', 'Bob Brown', '321 Cedar Lane', 'en', 0),
    ('user5@example.com', 'df54b0b72605b5f78c26e1d25c208913ebb7d058a5680bbd2161d53e262a35a8:aW/qpTJn83zCY9QR9GKKQA==', 'Charlie Davis', '654 Elm Street', 'es', 0),
    ('user6@example.com', 'e9d72876695157fdd55daa0f3eb801c8d05b21bd98b1ced85dd8b4378af8e647:qA/eyCTB8X9msKk51E4bdw==', 'Emily Clark', '987 Birch Boulevard', 'en', 0),
    ('user7@example.com', '3e035d5016817a9bf29416fbafbe3f369586f9f773fc2237f411103e3e0d09b8:/CWcdSWXLJfo7T1R/HdPhw==', 'Daniel Miller', '123 Spruce Drive', 'es', 0),
    ('user8@example.com', 'a78ebdaa2c271f8e0ad1e5645501d716ca85a793460e3d7d19ea826fa6744fcd:ss1bzVSW9AoqLWwTdBjAJA==', 'Sophia Wilson', '456 Redwood Circle', 'en', 0),
    ('user9@example.com', 'f1b5199f32454072a7cbc696e9e55d6844c8d1352101943f45fbcbdf384dc997:U4tJZ5OVVSTOTe6LaeILDw==', 'James Moore', '789 Ash Terrace', 'es', 0);
