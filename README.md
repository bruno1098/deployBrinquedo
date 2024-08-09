![image](https://github.com/user-attachments/assets/656ac969-4bd4-491e-a339-3a353aaebbfc)![image](https://github.com/user-attachments/assets/53074169-718d-4922-b64b-7c7424f7e6aa)
# API de Brinquedos

Este projeto é uma API RESTful para gerenciar uma coleção de brinquedos, permitindo operações completas de CRUD (Create, Read, Update, Delete). A API é desenvolvida utilizando Spring Boot e segue princípios RESTful, utilizando HATEOAS para facilitar a navegação entre recursos.

## Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
- ![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
- ![Lombok](https://img.shields.io/badge/Lombok-45b8d8?style=for-the-badge&logo=lombok&logoColor=white)
- ![HATEOAS](https://img.shields.io/badge/HATEOAS-007ec6?style=for-the-badge&logo=spring&logoColor=white)
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)

## Visão Geral

A API de Brinquedos oferece serviços para manipulação de dados de brinquedos. Os clientes podem criar, ler, atualizar, deletar e verificar a existência de registros de brinquedos. Esta API é ideal para ser usada como backend para aplicações de lojas de brinquedos ou qualquer sistema que necessite gerenciar um inventário de brinquedos.

## Configuração do Spring Initializr

Esta é a configuração utilizada no [Spring Initializr](https://github.com/bruno1098/deployBrinquedo/blob/main/images/Imagem%2009-08-2024%20a%CC%80s%2018.33.jpeg?raw=true) 

- Projeto: Maven
- Linguagem: Java
- Versão do Spring Boot: 3.1.2
- Grupo: `br.com.brinquedos`
- Artefato: `brinquedos-api`
- Nome: `brinquedos-api`
- Descrição: API para gerenciar brinquedos
- Pacote: `br.com.brinquedos`
- Dependências:
  - Spring Web
  - Spring Data JPA
  - Lombok
  - Spring HATEOAS
  - Oracle Driver

![Configuração do Spring Initializr](images/spring-initializr-config.png)

## CRUD de Brinquedos

### Criar Novo Brinquedo

- **URL:** `/brinquedos`
- **Método:** `POST`
- **Descrição:** Cria um novo registro de brinquedo.
- **Exemplo de Requisição:**
  ```http
  POST /brinquedos
  Content-Type: application/json

  {
    "nome": "Trenzinho de Madeira",
    "tipo": "Madeira",
    "classificacao": "Crianças até 8 anos",
    "tamanho": "Médio",
    "preco": 75.8
  }
  ```
- **Exemplo de Resposta:**
  ```json
  {
    "id": 7,
    "nome": "Trenzinho de Madeira",
    "tipo": "Madeira",
    "classificacao": "Crianças até 8 anos",
    "tamanho": "Médio",
    "preco": 75.8,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/brinquedos/7",
        "title": "POST method"
      },
      {
        "rel": "brinquedos",
        "href": "http://localhost:8080/brinquedos",
        "title": "GET method"
      }
    ]
  }
  ```

- **Imagem da Requisição:**

  ![Exemplo de POST](images/post-request.png)

### Listar Todos os Brinquedos

- **URL:** `/brinquedos`
- **Método:** `GET`
- **Descrição:** Retorna uma lista de todos os brinquedos disponíveis.
- **Exemplo de Requisição:**
  ```http
  GET /brinquedos
  ```
- **Exemplo de Resposta:**
  ```json
  [
    {
      "id": 1,
      "nome": "Carrinho de Controle Remoto",
      "tipo": "Eletrônico",
      "classificacao": "Crianças até 10 anos",
      "tamanho": "Médio",
      "preco": 150.99,
      "links": [
        {
          "rel": "self",
          "href": "http://localhost:8080/brinquedos/1",
          "title": "GET method"
        },
        {
          "rel": "brinquedos",
          "href": "http://localhost:8080/brinquedos",
          "title": "GET method"
        }
      ]
    }
    // outros brinquedos...
  ]
  ```

- **Imagem da Requisição:**

  ![Exemplo de GET Todos](images/get-all-request.png)

### Consultar Brinquedo por ID

- **URL:** `/brinquedos/{id}`
- **Método:** `GET`
- **Descrição:** Retorna os detalhes de um brinquedo específico pelo seu ID.
- **Exemplo de Requisição:**
  ```http
  GET /brinquedos/1
  ```
- **Exemplo de Resposta:**
  ```json
  {
    "id": 1,
    "nome": "Carrinho de Controle Remoto",
    "tipo": "Eletrônico",
    "classificacao": "Crianças até 10 anos",
    "tamanho": "Médio",
    "preco": 150.99,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/brinquedos/1",
        "title": "GET method"
      },
      {
        "rel": "brinquedos",
        "href": "http://localhost:8080/brinquedos",
        "title": "GET method"
      }
    ]
  }
  ```

- **Imagem da Requisição:**

  ![Exemplo de GET por ID](images/get-by-id-request.png)

### Atualizar Brinquedo

- **URL:** `/brinquedos/{id}`
- **Método:** `PUT`
- **Descrição:** Atualiza todos os dados de um brinquedo existente.
- **Exemplo de Requisição:**
  ```http
  PUT /brinquedos/1
  Content-Type: application/json

  {
    "nome": "Carrinho de Controle Remoto Avançado",
    "tipo": "Eletrônico",
    "classificacao": "Crianças até 12 anos",
    "tamanho": "Grande",
    "preco": 200.99
  }
  ```
- **Exemplo de Resposta:**
  ```json
  {
    "id": 1,
    "nome": "Carrinho de Controle Remoto Avançado",
    "tipo": "Eletrônico",
    "classificacao": "Crianças até 12 anos",
    "tamanho": "Grande",
    "preco": 200.99,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/brinquedos/1",
        "title": "PUT method"
      },
      {
        "rel": "consultar",
        "href": "http://localhost:8080/brinquedos/1",
        "title": "GET method"
      },
      {
        "rel": "brinquedos",
        "href": "http://localhost:8080/brinquedos",
        "title": "GET method"
      }
    ]
  }
  ```

- **Imagem da Requisição:**

  ![Exemplo de PUT](images/put-request.png)

### Atualizar Parcialmente um Brinquedo

- **URL:** `/brinquedos/{id}`
- **Método:** `PATCH`
- **Descrição:** Atualiza parcialmente os dados de um brinquedo.
- **Exemplo de Requisição:**
  ```http
  PATCH /brinquedos/1
  Content-Type: application/json

  {
    "preco": 180.99
  }
  ```
- **Exemplo de Resposta:**
  ```json
  {
    "id": 1,
    "nome": "Carrinho de Controle Remoto",
    "tipo": "Eletrônico",
    "classificacao": "Crianças até 10 anos",
    "tamanho": "Médio",
    "preco": 180.99,
    "links": [
      {
        "rel": "consultar",
        "href": "http://localhost:8080/brinquedos/1",
        "title": "GET method"
      },
      {
        "rel": "brinquedos",
        "href": "http://localhost:8080/brinquedos",
        "title": "GET method"
      },
      {
        "rel": "self",
        "href": "http://localhost:8080/brinquedos/1",
        "title

": "PATCH method"
      }
    ]
  }
  ```

- **Imagem da Requisição:**

  ![Exemplo de PATCH](images/patch-request.png)

### Deletar Brinquedo

- **URL:** `/brinquedos/{id}`
- **Método:** `DELETE`
- **Descrição:** Remove um brinquedo do sistema.
- **Exemplo de Requisição:**
  ```http
  DELETE /brinquedos/1
  ```
- **Exemplo de Resposta:**
  ```http
  HTTP/1.1 200 OK
  ```

- **Imagem da Requisição:**

  ![Exemplo de DELETE](images/delete-request.png)

### Verificar Existência de Brinquedo (HEAD)

- **URL:** `/brinquedos/{id}`
- **Método:** `HEAD`
- **Descrição:** Verifica se um brinquedo existe no sistema.
- **Exemplo de Requisição:**
  ```http
  HEAD /brinquedos/1
  ```
- **Exemplo de Resposta:**
  ```http
  HTTP/1.1 200 OK
  ```

- **Imagem da Requisição:**

  ![Exemplo de HEAD](images/head-request.png)

### Opções do Brinquedo (OPTIONS)

- **URL:** `/brinquedos/{id}`
- **Método:** `OPTIONS`
- **Descrição:** Retorna os métodos HTTP suportados para o recurso.
- **Exemplo de Requisição:**
  ```http
  OPTIONS /brinquedos/1
  ```
- **Exemplo de Resposta:**
  ```http
  HTTP/1.1 200 OK
  Allow: GET, POST, PATCH, PUT, DELETE, HEAD
  ```

- **Imagem da Requisição:**

  ![Exemplo de OPTIONS](images/options-request.png)

## Ambiente de Desenvolvimento

O projeto foi desenvolvido utilizando a IDE **IntelliJ IDEA**. Esta IDE foi escolhida por suas robustas funcionalidades de suporte a projetos Java e integração com ferramentas de construção como Maven e Gradle.

## Estrutura do Projeto

A estrutura do projeto está organizada de forma a seguir as melhores práticas de desenvolvimento em Java com Spring Boot:

```
brinquedos-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── brinquedos/
│   │   │               ├── controller/
│   │   │               │   └── BrinquedoController.java
│   │   │               ├── entity/
│   │   │               │   └── Brinquedo.java
│   │   │               ├── service/
│   │   │               │   └── BrinquedoService.java
│   │   │               └── repository/
│   │   │                   └── BrinquedoRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── br/
│               └── com/
│                   └── brinquedos/
│                       └── BrinquedoControllerTests.java
└── pom.xml
```

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/bruno1098/deployBrinquedo
   cd brinquedos
   ```

2. Configure o banco de dados Oracle com as informações necessárias no arquivo `application.properties`.

3. Compile o projeto usando Maven:
   ```bash
   mvn clean install
   ```

4. Execute o aplicativo:
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

