# Aviation API (Spring Boot)

Backend API for an aviation-themed arrivals/departures application.  
Supports CRUD operations for Airports, Airlines, Gates, and Flights, including search endpoints for arrivals and departures.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web + Spring Data JPA
- PostgreSQL
- Maven
- JUnit / Mockito (tests)
- GitHub Actions CI (Maven tests)

---

## Getting Started (Local)

### Prerequisites
- Java 17 installed
- Maven installed (or use Maven wrapper if your project has it)
- PostgreSQL running locally

### Configure Database
Update `src/main/resources/application.properties` with your DB credentials, for example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DB_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```
Run the API

From the project root:

mvn clean spring-boot:run

API runs at:

    http://localhost:8080

Running Tests

mvn -U clean test

API Endpoints
Airports

    POST /api/airports

    GET /api/airports

    GET /api/airports/{id}

    PUT /api/airports/{id}

    DELETE /api/airports/{id}

Airlines

    POST /api/airlines

    GET /api/airlines

    GET /api/airlines/{id}

    PUT /api/airlines/{id}

    DELETE /api/airlines/{id}

Gates

    POST /api/gates
    Example body:

    { "gateCode": "A12", "terminal": "T1", "active": true, "airportId": 1 }

    GET /api/gates

    GET /api/gates/{id}

    PUT /api/gates/{id}

    DELETE /api/gates/{id}

Flights

    POST /api/flights
    Example body:

    {
      "flightNumber": "AC123",
      "arrivalAirport": "YYT",
      "departureAirport": "YYZ",
      "scheduledArrival": "2025-12-17T12:30:00",
      "scheduledDeparture": "2025-12-17T10:00:00",
      "status": "ON_TIME",
      "airlineId": 1,
      "gateId": 1
    }

    GET /api/flights

    GET /api/flights/{id}

    PUT /api/flights/{id}

    DELETE /api/flights/{id}

Flight Search

    GET /api/flights/search/arrivals?airport=YYT

    GET /api/flights/search/departures?airport=YYZ