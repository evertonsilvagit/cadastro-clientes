# cadastro de clientes

Banco de dados:

A aplicação foi construída usando o banco em memória h2.

Execução:

$ mvn clean package

$ docker build -t cadastro-clientes .
$ docker run -d -p 8080:8080 cadastro-clientes

Swagger:

http://localhost:8080/cadastro/api/v1/swagger-ui.html
