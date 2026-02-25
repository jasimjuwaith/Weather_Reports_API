# Weather Report API

A comprehensive Spring Boot application designed to process and analyze 20 years of massive weather CSV data. Built with an optimized MySQL schema, Spring Data JPA mapping, and a fully interactive Swagger/OpenAPI dashboard.

## Technology Stack
- **Java 21**: Leveraging the newest LTS release.
- **Spring Boot 4 / Spring Web / Spring Data JPA**: The robust application scaffolding and transactional persistence layer.
- **MySQL 8.0**: Advanced relational datastore for querying chronological datasets.
- **OpenCSV**: A blazing-fast streaming CSV processor.
- **Lombok**: Boilerplate stripping. 

## REST API

| Method | Route | Description | Parameters |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/v1/weather/upload` | Stream massive files into the database. | `MultipartFile` body |
| **GET** | `/api/v1/weather/by-date` | Extrapolate weather context by date. | `date` (YYYY-MM-DD) |
| **GET** | `/api/v1/weather/by-month` | Look up precise monthly metrics. | `year`, `month` |
| **GET** | `/api/v1/weather/stats` | Execute aggregate statistical math. | `year` |
