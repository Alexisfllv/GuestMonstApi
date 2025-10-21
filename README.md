# 🎃 ¿Quién es ese Monstruo? – Backend API

Juego web tipo *"¿Quién es ese Pokémon?"* adaptado a Halloween.  
El backend provee los endpoints para obtener trivias aleatorias, validar respuestas y cargar datos iniciales de monstruos.

---

## 🧠 Descripción General

Este proyecto implementa la **API backend** del juego "¿Quién es ese Monstruo?".  
El objetivo es ofrecer una experiencia divertida y rápida, donde el usuario adivina la identidad de un monstruo a partir de su silueta.

La aplicación sigue una arquitectura REST utilizando **Spring Boot 3.5** y **Java 21**.  
El sistema expone endpoints para:

- Obtener una trivia aleatoria con 4 opciones.
- Validar si la respuesta del usuario es correcta.
- Precargar la base de datos con monstruos mediante un *seed script*.

---

## 🧩 Tecnologías Utilizadas

| Tecnología | Descripción |
|-------------|-------------|
| **Java 21** | Lenguaje base del proyecto |
| **Spring Boot 3.5** | Framework principal para la API REST |
| **Spring Web** | Controladores REST |
| **Spring Data JPA** | Persistencia de datos con PostgreSQL |
| **PostgreSQL** | Base de datos relacional |
| **Lombok** | Reducción de código boilerplate (Getters, Setters, etc.) |
| **MapStruct** | Mapeo entre entidades y DTOs |
| **Swagger UI (Springdoc)** | Documentación interactiva de la API |
| **SLF4J + Logback** | Sistema de logs |

---

## 🧱 Arquitectura del Proyecto

```plaintext
src/
├── main/
│   ├── java/com/halloween/monstertrivia/
│   │   ├── controller/     → Endpoints REST
│   │   ├── service/        → Lógica de negocio
│   │   ├── repository/     → Acceso a datos
│   │   ├── domain/         → Entidades JPA
│   │   ├── dto/            → Objetos de transferencia
│   │   └── config/         → Configuración general
│   └── resources/
│       ├── application.yml → Configuración de entorno
│       └── data/seed.sql   → Datos iniciales (monstruos)
```

---

## 🧩 Diagrama Entidad–Relación

```mermaid
erDiagram
    tb_product_photo {
        int product_id
        string file_name
        string description
        string content_type
        int size
    }

    tb_products {
        int id
        boolean active
        string description
        string name
        float price
        int restaurant_id
    }

    tb_request_item {
        int id
        int amount
        string note
        float total_price
        int request_id
        int product_id
    }
```

---

## 🧛 Endpoints Principales

### 🎲 1. Obtener trivia aleatoria

**GET** `/api/trivia`

**Ejemplo de respuesta:**
```json
{
  "id": 3,
  "imagen": "https://cdn.monsters/halloween/vampire-silhouette.png",
  "opciones": ["Vampiro", "Momia", "Zombie", "Frankenstein"]
}
```

---

### ✅ 2. Validar respuesta

**POST** `/api/validate`

**Body Ejemplo:**
```json
{
  "id": 3,
  "respuesta": "Vampiro"
}
```

**Respuesta:**
```json
{
  "correcto": true,
  "monstruoRevelado": "https://cdn.monsters/halloween/vampire.png"
}
```

---

### 🧬 3. Cargar datos iniciales (Seed)

**Script:** `/resources/data/seed.sql`  
Debe cargar al menos **10 monstruos** con sus respectivas siluetas, nombres y respuestas correctas.

---

## ⚙️ Configuración del Proyecto

### 1️⃣ Clonar repositorio

```bash
git clone https://github.com/tuusuario/monster-trivia-backend.git
cd monster-trivia-backend
```

### 2️⃣ Configurar Base de Datos PostgreSQL

```yaml
spring:
  application:
    name: whos-that-monster

  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    defer-datasource-initialization: true
```

### 3️⃣ Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

### 4️⃣ Acceder a Swagger

```bash
http://localhost:8080/swagger-ui.html
```

---

## 📋 Historias de Usuario Implementadas

| ID       | Descripción              | Endpoint             | Estado |
|----------:|--------------------------|----------------------|--------|
| **US01**  | Obtener trivia aleatoria | `GET /api/trivia`    | ✅     |
| **US02**  | Validar respuesta        | `POST /api/validate` | ✅     |
| **US03**  | Cargar seed de monstruos | Script SQL           | ✅     |
