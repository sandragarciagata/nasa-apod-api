[Spanish version](README.md)

# NASA APOD (Astronomy Picture of the Day) API

## Project Description

A RESTful API built with Java & Spring Boot that consumes NASA's "Astronomy Picture of the Day" (APOD) API.

This project serves a dual purpose:
1.  **Proxy & Enrichment:** A main endpoint fetches the current picture of the day directly from NASA.
2.  **Persistence & Archiving:** It optionally saves each retrieved picture into a local database, adding a timestamp. A second endpoint allows querying this historical archive by month and year.

This project showcases the ability to integrate with third-party APIs, manage API keys securely, map JSON responses, and persist data.

## Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3
* **HTTP Client:** RestTemplate
* **Data Access:** Spring Data JPA
* **Database:** H2 (In-Memory)
* **Build Tool:** Maven

## Getting Started

### Prerequisites

* JDK 17 or higher.
* Maven.
* A **NASA API Key**: you can get one by signing up at [api.nasa.gov](https://api.nasa.gov/).

### Installation & Configuration

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/nasa-apod-api.git](https://github.com/your-username/nasa-apod-api.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd nasa-apod-api
    ```
3.  **Set up your API Key:**
    In the `src/main/resources/application.properties` file, add the following line, replacing `YOUR_API_KEY` with your actual key:
    ```properties
    nasa.api.key=YOUR_API_KEY
    ```
4.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
5.  The API will be available at `http://localhost:8080`.

## API Endpoints

* `GET /api/nasa/apod`: Fetches the current Astronomy Picture of the Day.
* `GET /api/nasa/apod/archive?year=2025&month=7`: Retrieves a list of saved pictures from the database for a specific year and month.