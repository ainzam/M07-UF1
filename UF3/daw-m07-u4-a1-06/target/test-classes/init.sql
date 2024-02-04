CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(10) NOT NULL,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL, -- Cambia la longitud según tus necesidades
    rank INT DEFAULT 0,
    active BOOLEAN DEFAULT true,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Insertar datos con contraseñas
INSERT INTO users (username, name, email, password) VALUES ('user1', 'John Test', 'john@email.com', 'password123');
INSERT INTO users (username, name, email, password) VALUES ('user2', 'Paul Test', 'paul@email.com', 'securepass');
