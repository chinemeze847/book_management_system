# Book_Management_System

## Description
This API manages the creation, fetching, updating, deletion and searching of books to and from the database

## Technologies 
* **JPA** It is used as the ORM (object relational mapping) in this project
* **Junit 5** It is used as the testing framework 
* **Java 17** The version of java used for the project
* **Maven** It is the build tool used for dependency management
* **MySQL DB** It is used as the database system to store the books
* **Mockito** It is used to fake or mock the repository methods for testing
* **Docker** Containerizes the application to run on multiple platforms 

## Dependencies
* **starter-web** Allows for RESTful applications using Spring MVC
* **starter-data-jpa** uses JPA to store data in a relational database
* **dev-tools** Improves the development experience by providing the live reload feature
* **mysql-connector-j** Allows the api to connect to a mysql database
* **starter-validation** provides validation feature to our models
* **lombok** It reduces the unused code from the java applications.
  It is automatically generating the getter and setter method for the class object by using Lombok annotation
* **starter-test** It allows us to test our application

## Project Structure
The project in arranged in packages and java classes for proper separation of concerns

### Packages
* **Controller** This package contains the controller class that handles the http request from the user
* **dto(data transfer object)**  It contains the request objects to be use to make the api calls
* **exception** It Contains all the possible exceptions that could occur and and adviser class that handles these exceptions
* **model** It contains the Book model class
* **repository** It contains the book repository that extends the JPARepository that provides methods to interact with the database
* **service** It contains the service interface and it's implementation that handles the business logic
* **test** It contains the test class for the service layer

## API Documentation
Follow the link below to view the API documentation generated with postman
https://documenter.getpostman.com/view/16028426/2s9YJW4kC3
