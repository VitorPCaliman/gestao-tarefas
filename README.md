# 📌 API de Gestão de Tarefas

Esta é uma API para gerenciamento de tarefas, permitindo a criação, listagem, atualização e exclusão de tarefas. A API utiliza **Spring Boot**, **H2 Database** e segue boas práticas de desenvolvimento, incluindo validações e tratamento de erros.

## 🚀 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.2-green) ![H2 Database](https://img.shields.io/badge/H2%20Database-inMemory-yellow) ![JUnit](https://img.shields.io/badge/JUnit-5-orange)

## 📌 Funcionalidades

A API permite as seguintes operações:

- 📌 **Criar uma tarefa** (`POST /api/tarefas`)
- 📌 **Listar todas as tarefas** (`GET /api/tarefas`)
- 📌 **Buscar uma tarefa por ID** (`GET /api/tarefas/{id}`)
- 📌 **Atualizar o status de uma tarefa** (`PUT /api/tarefas/{id}`)
- 📌 **Excluir uma tarefa** (`DELETE /api/tarefas/{id}`)

## 📂 Arquitetura e Estrutura de Pacotes

```
/src/main/java/com/api/gestao_tarefas

  │── controller  # Controllers da API
  │── dto         # Objetos de transferência de dados
  │── model       # Entidades
  │── repository  # Acesso ao banco de dados
  │── service     # Regras de negócio

```

## 🗂️ Modelo de Dados

A API trabalha com a seguinte entidade:

```java
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    private String descricao;

    private String status;
}
```

## ⚙️ Configuração do Banco de Dados H2

A API utiliza um banco de dados em memória H2. A configuração está no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:tarefas
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesse o console do H2 após rodar a aplicação:
- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:tarefas`
- **User:** `sa`
- **Password:** *(vazio)*

## 🛠️ Como Instalar e Rodar a API

### 📌 Pré-requisitos
- Java 17+
- Maven

### 📌 Passos
1. **Clone o repositório**
   ```sh
   git clone https://github.com/VitorPCaliman/gestao-tarefas.git
   ```

2. **Entre no diretório do projeto**
   ```sh
   cd .\gestao-tarefas\
   ```

3. **Baixe as Dependências**
   ```sh
   mvn clean install
   ```
4. **Executar os Testes**
   ```sh
   mvn test
   ```

5. **Execute o projeto com Maven**
   ```sh
   mvn spring-boot:run
   ```
6. **A API estará disponível em** `http://localhost:8080`

## 📄 Documentação e Testes via Postman

### 1️⃣ Criar uma Tarefa
- **Endpoint:** `POST /api/tarefas`
- **Body:**
  ```json
  {
    "titulo": "Comprar pão",
    "descricao": "Comprar pão e leite no mercado",
    "status": "PENDENTE"
  }
  ```
- **Resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Comprar pão",
    "descricao": "Comprar pão e leite no mercado",
    "status": "PENDENTE"
  }
  ```
- **Erro (400 Bad Request)**
  ```json
  {
    "message":  "O título é obrigatório."
  }
  ```

### 2️⃣ Listar Tarefas
- **Endpoint:** `GET /api/tarefas`
- **Resposta:**
  ```json
  [
    {
      "id": 1,
      "titulo": "Comprar pão",
      "descricao": "Comprar pão e leite no mercado",
      "status": "PENDENTE"
    }
  ]
  ```
- **Erro (400 Not Found)**
  ```json
  {
    "message": "Nenhuma Tarefa encontrada."
  }
  ```

### 3️⃣ Buscar Tarefa por ID
- **Endpoint:** `GET /api/tarefas/{id}`
- **Exemplo:** `/api/tarefas/1`
- **Resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Comprar pão",
    "descricao": "Comprar pão e leite no mercado",
    "status": "PENDENTE"
  }
  ```
- **Erro (400 Not Found)**
  ```json
  {
    "message": "Tarefa não encontrada."
  }
  ```

### 4️⃣ Atualizar Status da Tarefa
- **Endpoint:** `PUT /api/tarefas/{id}`
- **Body:**
  ```json
  {
    "status": "CONCLUIDA"
  }
  ```
- **Resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Comprar pão",
    "descricao": "Comprar pão e leite no mercado",
    "status": "CONCLUIDA"
  }
  ```
- **Erro (404 Not Found)**
  ```json
  {
    "message": "Tarefa não encontrada."
  }
  ```
- **Erro (400 Bad Request)**
  ```json
  {
    "message": "O status não pode ser vazio."
  }
  ```

### 5️⃣ Excluir uma Tarefa
- **Endpoint:** `DELETE /api/tarefas/{id}`
- **Exemplo:** `/api/tarefas/1`
- **Resposta:** `204 No Content`
- **Erro (404 Not Found)**
  ```json
  {
    "message": "Tarefa não encontrada."
  }
  ```

## 📌 Melhorias Planejadas

✅ Implementar paginação na listagem de tarefas

✅ Adicionar autenticação e autorização com Spring Security

✅ Criar logs detalhados para cada operação

✅ Melhorar a documentação com Swagger

✅ Adicionar testes automatizados com cobertura de código

---

🚀 API simples desenvolvida como parte de um desafio técnico e para facilitar o gerenciamento de tarefas com boas práticas de desenvolvimento.

Caso tenha sugestões ou melhorias, contribua no repositório! 😊
