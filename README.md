[Versión en inglés](README_EN.md)

# NASA APOD API

## Descripción

Una API RESTful desarrollada con Java y Spring Boot que consume la API "Astronomy Picture of the Day" (APOD) de la NASA.

Este proyecto tiene una doble función:
1.  **Proxy y Enriquecimiento:** Un endpoint principal obtiene la imagen del día directamente de la NASA.
2.  **Persistencia y Archivo:** Opcionalmente, guarda cada imagen consultada en una base de datos local, añadiendo una marca de tiempo. Un segundo endpoint permite consultar este archivo histórico por mes y año.

Este proyecto demuestra la capacidad de integrarse con APIs de terceros, gestionar claves de API de forma segura, mapear respuestas JSON y persistir datos.

## Stack Tecnológico

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3
* **Cliente HTTP:** RestTemplate
* **Acceso a Datos:** Spring Data JPA
* **Base de Datos:** H2 (en memoria)
* **Build Tool:** Maven

## Cómo Empezar

### Prerrequisitos

* JDK 17 o superior.
* Maven.
* Una **API Key de la NASA**: puedes obtenerla registrándote en [api.nasa.gov](https://api.nasa.gov/).

### Instalación y Configuración

1.  **Clona el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/nasa-apod-api.git](https://github.com/tu-usuario/nasa-apod-api.git)
    ```
2.  **Navega al directorio del proyecto:**
    ```bash
    cd nasa-apod-api
    ```
3.  **Configura tu API Key:**
    En el archivo `src/main/resources/application.properties`, añade la siguiente línea reemplazando `TU_API_KEY` con tu clave real:
    ```properties
    nasa.api.key=TU_API_KEY
    ```
4.  **Ejecuta la aplicación:**
    ```bash
    ./mvnw spring-boot:run
    ```
5.  La API estará disponible en `http://localhost:8080`.

## Endpoints de la API

* `GET /api/nasa/apod`: Obtiene la imagen astronómica del día actual.
* `GET /api/nasa/apod/archive?year=2025&month=7`: Obtiene un listado de las imágenes guardadas en la base de datos para un año y mes específicos.