
# 📚 Challenge Literalura - Spring Boot Console App

[![Java](https://img.shields.io/badge/Java-17-red?logo=java)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql)](https://www.postgresql.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 📖 Descripción

Aplicación de consola desarrollada en **Java 17** con **Spring Boot**, que consume la API pública de [Gutendex](https://gutendex.com/) para gestionar libros de dominio público.  
Permite consultar, filtrar y almacenar información de libros y autores en una base de datos relacional mediante **JPA y PostgreSQL**.

---

## 🚀 Funcionalidades

- 🔍 Buscar libros por título desde la API
- 📘 Listar libros guardados en la base de datos
- ✍️ Consultar todos los autores registrados
- 🌐 Filtrar libros por idioma
- 📅 Ver autores vivos en un año específico

---

## 🛠️ Tecnologías Usadas

| Herramienta        | Descripción                                |
|--------------------|--------------------------------------------|
| Java 17            | Lenguaje de programación base              |
| Spring Boot        | Framework principal para la aplicación     |
| Spring Data JPA    | Persistencia de datos                      |
| PostgreSQL         | Base de datos relacional                   |
| pgAdmin            | Herramienta para gestión de PostgreSQL     |
| Jackson            | Conversión JSON ↔️ Objetos Java            |
| HttpClient         | Cliente HTTP para consumir la API externa  |
| Maven              | Gestión de dependencias y build            |

---

## 🧱 Estructura del Proyecto

src/
├── main/
│ ├── java/
│ │ └── com.ejemplo.gutendex/
│ │ ├── model/ # Entidades: Libro, Autor
│ │ ├── repository/ # Interfaces JPA
│ │ ├── service/ # Lógica de negocio
│ │ ├── api/ # Cliente API Gutendex
│ │ └── principal/ # Clase Main con menú interactivo
│ └── resources/
│ └── application.properties # Configuración de la BD

yaml
Copiar
Editar

---

## ▶️ Cómo Ejecutar

1. Clona el repositorio:

```bash
git clone https://github.com/AndresMena1979/Challenge-Literalura
cd Challenge-Literalura
Ejecuta desde IDE o con Maven:

bash
Copiar
Editar
./mvnw spring-boot:run
Usa el menú interactivo en consola para navegar por las opciones.

📌 Notas
La API de Gutendex es pública y no requiere autenticación.

Se pueden aplicar filtros como idioma, autor, título, etc.

Este proyecto es ideal para practicar consumo de APIs REST, persistencia con JPA, y buenas prácticas de arquitectura.

🧠 Aprendizajes Clave
Consumo de servicios REST en Java

Mapeo de JSON a clases Java con Jackson

Diseño por capas: Modelo → Repositorio → Servicio → Presentación

Integración de base de datos con Spring Data JPA

🪪 Licencia
Distribuido bajo la licencia MIT.
Uso libre para fines educativos y de aprendizaje.

👤 Autor
Francisco Javier Mena
Desarrollador Backend Java en formación
📘 Programa: Oracle Next Education (ONE)

