# Desafio Técnico Itaú – API de Transações com Redis Cache

## 📋 Descrição

Este projeto foi desenvolvido como parte do desafio técnico proposto pelo Itaú Unibanco. A missão consistia em criar uma API REST capaz de receber transações financeiras e retornar estatísticas sobre essas transações.

Optou-se por utilizar Java com Spring Boot para a construção da API, incorporando o Redis como mecanismo de cache para otimizar o desempenho e a escalabilidade da aplicação.

## 🚀 Tecnologias e Ferramentas Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Redis** – utilizado como cache para armazenar transações e estatísticas
- **Spring Data Redis** – integração com o Redis
- **Spring Web** – criação de endpoints RESTful
- **Spring Validation** – validação de dados de entrada
- **Docker Compose** – orquestração de containers para a aplicação e o Redis
- **Maven** – gerenciamento de dependências e build

## 🧠 Decisões de Arquitetura

### 🗹 Uso do Redis como Cache

Para garantir alta performance e baixa latência nas operações de leitura e escrita, foi implementado o Redis como camada de cache. Essa abordagem permite armazenar em memória os resultados de consultas recorrentes ou pesadas, reduzindo significativamente a carga sobre o sistema e melhorando o tempo de resposta.

### 🏱 Estrutura de Camadas

O projeto segue uma arquitetura em camadas, promovendo a separação de responsabilidades e facilitando a manutenção e escalabilidade:

- **Controller**: recebe e processa as requisições HTTP
- **Service**: contém a lógica de negócios
- **Repository**: interage com o Redis para persistência e recuperação de dados

### 🔄 Middlewares e Validações

Foram implementados middlewares para tratamento de exceções e validações de entrada, garantindo que apenas dados válidos sejam processados pela aplicação.

## 📬 Endpoints da API

### POST `/transacao`

Recebe uma nova transação.

**Requisição:**
```json
{
  "valor": 100.50,
  "dataHora": "2025-05-09T12:34:56.789-03:00"
}
```

**Respostas:**
- `201 Created` – Transação registrada com sucesso
- `422 Unprocessable Entity` – Dados inválidos (ex: valor negativo, data futura)
- `400 Bad Request` – Erro de formatação na requisição

### GET `/estatistica`

Retorna estatísticas das transações ocorridas nos últimos 60 segundos.

**Resposta:**
```json
{
  "count": 10,
  "sum": 1000.0,
  "avg": 100.0,
  "min": 50.0,
  "max": 150.0
}
```

### DELETE `/transacao`

Remove todas as transações registradas.

**Resposta:**
- `200 OK` – Todas as transações foram removidas com sucesso


## 🐳 Executando com Docker Compose

Para facilitar a execução da aplicação juntamente com o Redis, foi criado um arquivo `docker-compose.yml`.

**Passos:**
1. Clone o repositório:
```bash
git clone https://github.com/devalvesg/desafio-itau.git
cd desafio-itau
```

2. Execute o Docker Compose:
```bash
docker-compose up --build
```

3. Acesse a aplicação em `http://localhost:8080`

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.