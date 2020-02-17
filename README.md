# TODO ESIG

Projeto desafio para a vaga de Desenvolvedor Java na ESIG

Este projeto está dividido em 3 pastas: 
- `original`, contendo a primeira versão com JSF,
- `spring`, contendo a segunda versão com Spring (e Thymeleaf),
- `frontreact`, contendo o front-end implementado com React, sendo alimentada pela segunda versão

## Como rodar
### Banco de dados
Criar um banco de dados PostgreSQL em `localhost:5432` com o nome `todo`. Caso o Hibernate não crie a tabela necessária, utilizar o script `database.sql` no diretório raiz do repositório

### Projeto com JSF
Rodar o arquivo `index.xhtml` em `/original/src/main/java/webapp`, e acessar em `http://localhost:8080/jsftutorial/faces/index.xhtml`

### Projeto com Spring + Thymeleaf
Rodar o arquivo Java `TodoApplication.java`, em `/spring/src/main/java/com/project/todo`, e acessar em `localhost:8080`

### projeto com React.js + Spring
Rodar o arquivo Java `TodoApplication.java`, em `src/main/java/com/project/todo`, que servirá como API. Em seguida, acessar o projeto em /frontreact e inicializá-lo com `npm start`, e acessar em `localhost:3000`

## Itens implementados
#### A: Aplicação web utilizando JavaServer Faces (JSF)
Para o primeiro item, foi implementado um WebApp em Java, utilizando como front-end JavaServer Faces. Essa primeira versão implementa uma lista da tarefas contendo as 4 funcionalidades do CRUD.

#### B: Persistência em banco de dados
Foi utilizado o PostgreSQL como banco de dados

#### C: Hibernate e JPA
Foi implementado Hibernate e JPA para se comunicar com o PostgreSQL

#### D: Spring Boot
A segunda versão foi implementada com o Spring Boot, assim como com o Spring MVC. Além das funcionalidades da primeira versão com JSF (e um design melhor), a segunda versão possui também a funcionalidade de marcar as tarefas já realizadas.

#### E: Spring Boot
A segunda versão foi implementada com o Spring MVC, assim como com o Spring Boot

#### F: Bootstrap 4
Foi utilizado o Bootstrap tanto na segunda versão (com Thymeleaf) quanto no React.js

#### I: React.js
Foi implementada uma terceira versão do projeto, utilizando React.js como front-end e sendo alimentado pelo projeto em Spring, onde foi implementado uma API se baseando no modelo REST (mas não seguindo todas as definições).
