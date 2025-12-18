CREATE DATABASE db_estudo;
USE db_estudo;

CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
    email VARCHAR(100)
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tarefa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo TEXT,
    prioridade VARCHAR(20),
    status VARCHAR(20),
    data_criacao DATETIME,
    data_conclusao DATETIME,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO alunos (nome, idade, email)
VALUES ('Derick', 20, 'derick@email.com');

INSERT INTO usuario (nome, email, senha)
VALUES ('Derick', 'derick@email.com', '123');

SELECT * FROM usuario;
SELECT * FROM tarefa
