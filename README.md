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
- **Erro (404 Not Found)**
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
- **Erro (404 Not Found)**
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

## 🙋‍♂️ Como Contribuir com este Projeto?

Procure entender as necessidades do sistema e analisár a lista de melhorias planejadas. Abra issues, discussions e forks. Mas siga as boas práticas.

**Guia de Contribuição - Git Flow**

## 📌 1. O que é Git Flow?
O **Git Flow** é um modelo de branching para controle de versionamento que define uma estrutura organizada para o desenvolvimento de software. Ele se baseia em dois ramos principais e diversos ramos auxiliares.

## 🌱 2. Estrutura de Branches
O projeto segue a seguinte estrutura de branches:

- **`main`** → Contém a versão estável do projeto. Apenas merges de releases chegam aqui.
- **`develop`** → Branch principal de desenvolvimento. Contém o código mais recente aprovado para testes.
- **Branches auxiliares**:
  - **`feature/*`** → Usada para desenvolver novas funcionalidades.
  - **`bugfix/*`** → Corrigir bugs encontrados na branch `develop`.
  - **`release/*`** → Preparação de uma nova versão, a partir da `develop`.
  - **`hotfix/*`** → Correção de bugs críticos diretamente na `main`.

### 🎯 Exemplo de criação de branches:

```sh
# Criando uma nova feature
git checkout develop
git pull origin develop
git checkout -b feature/nome-da-feature

# Criando uma correção de bug
git checkout develop
git pull origin develop
git checkout -b bugfix/nome-do-bug
```

---

## ✅ 3. Padrões de Commits

Os commits devem ser **claros, descritivos e seguir um padrão**, preferencialmente o **Conventional Commits**:

```
<tipo>(<escopo>): <descrição breve>

[corpo opcional: detalhes adicionais]
[rodapé opcional: informações extras, como issue relacionada]
```

### 📌 Exemplos:
- `feat(login): adicionar autenticação JWT`
- `fix(api): corrigir erro de resposta HTTP 500`
- `docs(readme): atualizar instruções de instalação`
- `chore(deps): atualizar dependências`

📌 **Tipos de commit mais comuns:**
- `feat` → Nova funcionalidade
- `fix` → Correção de bug
- `docs` → Alterações na documentação
- `style` → Formatação (espaços, indentação, etc.)
- `refactor` → Refatoração de código sem mudar comportamento
- `test` → Adição ou modificação de testes
- `chore` → Atualizações que não alteram código fonte (build, CI/CD, deps)

---

## 🔄 4. Fluxo de Trabalho

### 👨‍💻 Desenvolvimento de uma Feature:
1. **Criar uma branch a partir da `develop`**  
   ```sh
   git checkout develop
   git pull origin develop
   git checkout -b feature/nova-funcionalidade
   ```
2. **Desenvolver e commitar seguindo o padrão**  
   ```sh
   git add .
   git commit -m "feat(core): adicionar suporte a múltiplos idiomas"
   ```
3. **Enviar para o repositório remoto**  
   ```sh
   git push origin feature/nova-funcionalidade
   ```
4. **Abrir um Pull Request (PR) para a `develop`**  
   - Descreva o que foi feito, testes e impactos esperados.
   - Espere a revisão e aprovação antes do merge.

---
## 🚀 5. Boas Práticas
✔ Sempre trabalhe em **branches separadas**  
✔ Use commits **pequenos e significativos**  
✔ Faça **revisões de código (PRs)** antes do merge  
✔ **Teste suas alterações** antes de abrir um PR  
✔ **Mantenha a `develop` sempre atualizada**  

---

🚀 API simples desenvolvida como parte de um desafio técnico e para facilitar o gerenciamento de tarefas com boas práticas de desenvolvimento.

Caso tenha sugestões ou melhorias, contribua no repositório! 😊
