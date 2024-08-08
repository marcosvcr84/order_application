
# Order Application API

Este projeto é uma API de Serviço de Pedidos construída com Spring Boot. Ele lida com pedidos de clientes em formatos XML e JSON, realiza validações, aplica regras de negócios e armazena os pedidos em um banco de dados MySQL.

## Requisitos

- Java 17 ou superior
- Maven 3.6.3 ou superior
- MySQL 8.0 ou superior

## Iniciando

### Clone o Repositório

```sh
git clone https://github.com/yourusername/orderservice.git
cd orderservice
```

### Configure o Banco de Dados

1. Crie um banco de dados MySQL chamado `orders_db`.

```sql
CREATE DATABASE orders_db;
```

2. Atualize a configuração do banco de dados em `src/main/resources/application.properties` com seu nome de usuário e senha do MySQL.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/orders_db
spring.datasource.username=root
spring.datasource.password=rootpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

### Execute a Aplicação

Você pode executar a aplicação usando Maven ou sua IDE.

#### Usando Maven

```sh
./mvnw spring-boot:run
```

#### Usando IDE

Importe o projeto como um projeto Maven e execute a classe principal `OrderServiceApplication`.

## Endpoints da API

### Criar Pedido

- **URL:** `/api/orders`
- **Método:** `POST`
- **Content-Type:** `application/json` ou `application/xml`
- **Corpo da Requisição (JSON):**

```json
{
  "controlNumber": 123456,
  "registrationDate": "2023-08-07",
  "productName": "Example Product",
  "unitPrice": 100.0,
  "quantity": 2,
  "clientId": 1
}
```

- **Corpo da Requisição (XML):**

```xml
<OrderRequest>
  <controlNumber>123456</controlNumber>
  <registrationDate>2023-08-07</registrationDate>
  <productName>Example Product</productName>
  <unitPrice>100.0</unitPrice>
  <quantity>2</quantity>
  <clientId>1</clientId>
</OrderRequest>
```

### Obter Todos os Pedidos

- **URL:** `/api/orders`
- **Método:** `GET`
- **Corpo da Resposta:**

```json
[
  {
    "id": 1,
    "controlNumber": 123456,
    "registrationDate": "2023-08-07",
    "productName": "Example Product",
    "unitPrice": 100.0,
    "quantity": 2,
    "totalPrice": 190.0,
    "clientId": 1
  }
]
```

### Obter Pedido pelo Número de Controle

- **URL:** `/api/orders/control-number/{controlNumber}`
- **Método:** `GET`
- **Corpo da Resposta:**

```json
[
  {
    "id": 1,
    "controlNumber": 123456,
    "registrationDate": "2023-08-07",
    "productName": "Example Product",
    "unitPrice": 100.0,
    "quantity": 2,
    "totalPrice": 190.0,
    "clientId": 1
  }
]
```

## Executando Testes

Você pode executar os testes usando Maven:

```sh
./mvnw test
```

## Informações Adicionais

- A aplicação aplica um desconto de 5% para pedidos com quantidade maior que 5 e um desconto de 10% para pedidos com quantidade de 10 ou mais.
- Se a `registrationDate` não for fornecida, a data atual será usada.
- Se a `quantity` não for fornecida, o valor padrão é 1.

## Licença

Este projeto é licenciado sob a licença MIT - veja o arquivo LICENSE para mais detalhes.
