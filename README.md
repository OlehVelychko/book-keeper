# BookKeeper

## Overview
BookKeeper is a Spring-based CRUD web application designed to manage books and people. The application enables the creation, retrieval, updating, and deletion of records, as well as the assignment of books to people. The project implements modern Java web development practices, utilizing Thymeleaf for templating and PostgreSQL as the database.

## Features
- **CRUD Operations**: Manage books and people with create, read, update, and delete functionalities.
- **Book Assignment**: Assign and release books to/from people.
- **Validation**: Includes server-side input validation for user inputs.
- **Error Handling**: User-friendly error pages for invalid or incomplete actions.

## Technologies Used
1. **Backend**:
   - Spring Framework (Core, WebMVC, and JDBC)
   - Java 17
2. **Frontend**:
   - Thymeleaf (templating engine)
   - HTML5 and CSS
3. **Database**:
   - PostgreSQL
4. **Build Tool**:
   - Apache Maven
5. **Server**:
   - Apache Tomcat 11

## Key Features and Design
1. **Validation**:
   - `@NotEmpty`, `@Size`, `@Min`, and `@Max` annotations are used in models to enforce field validation.
   - Validation messages are displayed on forms via Thymeleaf templates.

2. **Separation of Concerns**:
   - Controllers handle user requests (`BooksController`, `PeopleController`).
   - DAO (Data Access Objects) layer manages database interactions (`BookDAO`, `PersonDAO`).
   - Mappers (`BookMapper`, `PersonMapper`) convert SQL result sets to Java objects.
   - Models (`Book`, `Person`) represent the core entities.

3. **Configuration**:
   - Java-based Spring configuration (`SpringConfig`) is used for setting up database connectivity and view resolvers.
   - UTF-8 encoding and hidden method filter are configured via the `MySpringMVCDispatcherServletInitializer`.

4. **Routing**:
   - RESTful URLs for accessing resources, such as:
     - `/books` - List all books.
     - `/books/new` - Create a new book.
     - `/books/{id}` - View book details.
     - `/books/{id}/edit` - Edit book details.
     - `/books/{id}/assign` - Assign a book to a person.
     - `/books/{id}/release` - Release a book from a person.

5. **Database Schema**:
   - A schema named `book_keeper_schema` is used with two tables:
     - `book`: Stores book information (`id`, `title`, `author`, `year`, `person_id`).
     - `person`: Stores person information (`id`, `name`, `birth_year`).

6. **Error Handling**:
   - Custom error messages are displayed in case of invalid or missing records.
   - Default error page for unhandled cases (`error.html`).

## Prerequisites
1. Java 17 or higher.
2. Apache Maven 3.8.1 or higher.
3. PostgreSQL database server.
