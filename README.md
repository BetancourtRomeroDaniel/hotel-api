
# 🏨 Hotel API

API REST desarrollada con Java y Spring Boot para la gestión de un sistema hotelero. Permite administrar usuarios, hoteles, habitaciones y reservas mediante operaciones CRUD.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 4
- Spring Data JPA
- MySQL
- Maven
- Hibernate
- Lombok
- Jakarta Validation
- Swagger / OpenAPI
- Postman

## ✨ Funcionalidades

### Usuarios
- Registrar usuarios.
- Consultar todos los usuarios.
- Buscar usuarios por ID.
- Actualizar usuarios.
- Eliminar usuarios.

### Hoteles
- Registrar hoteles.
- Consultar hoteles.
- Buscar hoteles por ID.
- Actualizar hoteles.
- Eliminar hoteles.

### Habitaciones
- Registrar habitaciones asociadas a hoteles.
- Consultar habitaciones.
- Buscar habitaciones por ID.
- Actualizar habitaciones.
- Eliminar habitaciones.
- Validar la existencia del hotel asociado.

### Reservas
- Registrar reservas asociando usuarios y habitaciones.
- Consultar todas las reservas.
- Buscar reservas por ID.
- Actualizar reservas.
- Eliminar reservas.
- Validar fechas y relaciones entre entidades.

## 🏗️ Arquitectura del proyecto

El proyecto está organizado mediante una arquitectura por capas:

```text
src/main/java/com/example/miapp/
│
├── config/
├── controller/
├── dto/
├── exception/
├── mapper/
├── model/
├── repository/
└── service/
```

### Responsabilidades

- **Controller:** expone los endpoints REST.
- **Service:** contiene la lógica de negocio.
- **DTO:** define los datos de entrada y salida.
- **Mapper:** transforma entidades y DTO.
- **Model:** contiene las entidades del sistema.
- **Repository:** gestiona el acceso a la base de datos.
- **Exception:** centraliza el manejo de errores.
- **Config:** contiene la configuración de la aplicación.

## 🛠️ Configuración e instalación

### Requisitos previos

- Java 17.
- MySQL Server.
- Git.

### 1. Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

### 2. Crear la base de datos

En MySQL ejecuta:

```sql
CREATE DATABASE hotel_db;
```

### 3. Configurar las variables de entorno

Copia el archivo de ejemplo:

```bash
cp .env.example .env
```

Configura las credenciales de tu instalación local de MySQL en `.env`:

```properties
DB_URL=jdbc:mysql://localhost:3306/hotel_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
```

No publiques tu archivo `.env`.

### 4. Ejecutar la aplicación

En macOS o Linux:

```bash
set -a
source .env
set +a
./mvnw spring-boot:run
```

En Windows puedes utilizar:

```bash
mvnw.cmd spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

## 📖 Documentación de la API

La API cuenta con documentación interactiva mediante Swagger / OpenAPI.

Con la aplicación ejecutándose, visita:

```text
http://localhost:8080/swagger-ui/index.html
```

También puedes consultar la especificación OpenAPI en:

```text
http://localhost:8080/v3/api-docs
```

## 🧪 Pruebas

Para ejecutar las pruebas del proyecto:

```bash
./mvnw clean test
```

## 👨‍💻 Autor

Proyecto personal de desarrollo Backend realizado con Java y Spring Boot.
