
CREATE TABLE clientes (
     id BIGINT auto_increment NOT NULL,
     nome varchar(100) NOT NULL,
     idade bigint NOT NULL,
     cpf varchar(100) NOT NULL UNIQUE,
     data_nascimento DATE NOT NULL,
     CONSTRAINT clientes_PK PRIMARY KEY (id)
);
