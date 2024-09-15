# sb-crud

# Creating a maven project
First open the website [spring initializr](https://start.spring.io)

Select following items:

- Projet : *Maven Project*
- Language: *Java*
- Spring Boot: 3.3.3 (Version you require)
- Enter meta data: Name of project, etc.

Add dependencies :

- *Spring Web*
- *PostgreSQL Driver*
- *Spring Data JPA*
- *Lombok*

Click on *GENERATE*, and a ZIP-file will be downloaded to the download folder of your browser

#Setting up the project 
1. Unzip the downloaded ZIP-file of the maven project into the folder of your GIT-Repository
2. Add application.properties to .gitignore
3. Edit application properties and add your data base credentials and driver to /src/main/resources/application.properties
as follows:

spring.application.name=*name of application*

spring.datasource.url=jdbc:postgresql: //hostname or IP::port number/name of database (Like //212.217.152.54:5432/sb-crud

spring.datasource.username=*username*

spring.datasource.password=*Password*

spring.datasource.driver-class-name=org.postgresql.Driver

# Model

1. I created a package named model.
2. I created a class named Book, which will will hold data for a book.

## Jakarta persistence annotations

The annotations Entity and Table allow me to skip a lot of boilerplate code for the model.
They signal to the JPA that this class is an entity and allows me to specify the name of the table that will be created in the database.

## Lombok persistence annotations

*@NoArgsConstrutor* will generate a constructor with now parameters
*@Data* will will generate getters, setters, equualsTo, toString and a constructor that has all the required fields
 
# JPA Repository

A Repository provides methods for manipulating data in a database.
So, instead of writing all the code myself, it is easier to use all the CRUD functionality provided by a JPA Repository.

1. I create a new package named *repo* 
2. I create an interface named *BookRepository*
3. The Repository has to inherit from (extend) JpaRepository
4. Annotate the interface with *@Repository* tag

.. and I'm done!
