# ecom-ms-app

E-commerce application built with a microservices architecture using Spring Boot and Spring Cloud. This is an ENSET practical activity (Architectures Microservices).

Goal: manage invoices that contain products and belong to a customer, with each business domain in its own independent service.

## Architecture

```
Client
  |
  v
Gateway Service (8888)  --->  Discovery Service / Eureka (8761)
  |                                   ^
  |-- Customer Service (8081)  -------|  register
  |-- Inventory Service (8082) -------|  register
```

The client only talks to the gateway. The gateway asks Eureka where each service is, then forwards the request to the right one.

## Services

| Service | Port | Description |
|---|---|---|
| customer-service | 8081 | Manages customers. H2 database `customers-db`. |
| inventory-service | 8082 | Manages products. H2 database `products-db`. |
| gateway-service | 8888 | Spring Cloud Gateway (reactive). Routing entry point. |
| discovery-service | 8761 | Eureka Server. Service registry. |

Each service has its own database (database per service).

## Tech stack

- Java 21
- Spring Boot 4.1.1
- Spring Web, Spring Data JPA, Spring Data REST
- H2 (in-memory)
- Lombok
- Spring Cloud Gateway (reactive)
- Spring Cloud Netflix Eureka (server and client)
- Spring Boot Actuator
- Maven (multi-module project)

## Project structure

```
ecom-ms-app/
  customer-service/
  inventory-service/
  gateway-service/
  discovery-service/
  pom.xml
```

## Prerequisites

- JDK 21
- Maven (or the Maven wrapper in each module)
- IntelliJ IDEA (recommended)

## Running the project

Start the services in this order:

1. `discovery-service` (wait until http://localhost:8761 loads)
2. `customer-service`
3. `inventory-service`
4. `gateway-service`

From the command line, inside each module folder:

```bash
./mvnw spring-boot:run
```

