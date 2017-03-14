Criar o banco zup no postgresql com usuário e senha root
Na pasta do projeto utilizar o comando no console mvn compile flyway:migrate para geração do banco com os dados
Para rodar o projeto adicionar no debug configurations o seguinte comando clean spring-boot:run
No arquivo application.properties adicionar para o usuário e senha root
Para acessar a aplicação digite localhost:8080