# Acesso ao sistema


### Rodar Sistema

    docker-compose up --build

### Encerrar Sistema

    docker-compose down -v

### Swagger
    http://localhost:8080/swagger

### Instruções
    - Após rodar o sistema, o sistema automaticamente criará um usuario com cpf e senha 123. Sendo assim possivel acessar o sistema, criar novos usuarios, atendimentos, pets, raças, endereços e contatos.

    - O front-end está usando a porta 4200, enquanto o back-end está usando a porta 8080. 
    - http://localhost:4200
    - http://localhost:8080

    - O Swagger está rodando no http://localhost:8080/swagger, precisando fazer a autentificação.