# CarService

## Description
**CarService** is a pet-project implemented in Java 17, with additional use of Redis for caching and ElasticSearch for logging. 
The project is aimed at demonstrating the capabilities of a modern backend stack. It uses basic web design with Bootstrap for a simple and functional user interface.

## Technologies
- Java 17
- Spring Boot (including Spring Data JPA, Spring Security, Spring Data Redis)
- HTML, CSS
- JavaScript
- Maven
- Docker
- PostgreSQL
- Redis
- ElasticSearch
- Thymeleaf

## Usage
After successfully launching **CarService**, users can log into their account or create a new one. Depending on the user's role, the following functionalities are available:
- Creating, modifying, and viewing offers.
- Managing information about car models and brands.

All functionalities are fully implemented in the web version of the project and are accessible according to the user's role.

Initial data for the project can be configured in the `CommandLineRunnerImpl` class, which allows for setting up necessary parameters for initial use of the application.
