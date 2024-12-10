-- Create table if it doesn't exist
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    info_contato VARCHAR(255),
    cpf VARCHAR(11),
    endereco_uf VARCHAR(2)
);

-- Insert initial data into the table
INSERT INTO cliente (endereco, info_contato, nome, cpf, endereco_uf) VALUES
('Sobradinho', '61993253661', 'Gustavo Alves', '12345678901', 'DF'),
('Rio de Janeiro', '11111111', 'Pedro Santos', '23456789012', 'RJ'),
('Fortaleza', '2222222', 'Thiago Sobral', '34567890123', 'CE');
