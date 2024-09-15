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

The annotations **@Entity** and **@Table** allow me to skip a lot of boilerplate code for the model.
They signal to the JPA that this class is an entity and allows me to specify the name of the table that will be created in the database.
**@Id** and **@GenerateValue** will declare the field as a unique identifier and let the database engine
know that it must auto-generate those IDs

## Lombok persistence annotations

*@NoArgsConstrutor* will generate a constructor with now parameters
**@Data** will will generate getters, setters, equualsTo, toString and a constructor that has all the required fields
 
# JPA Repository

A Repository provides methods for manipulating data in a database.
So, instead of writing all the code myself, it is easier to use all the CRUD functionality provided by a JPA Repository.

1. I create a new package named **repo** 
2. I create an interface named **BookRepository**
3. The Repository has to inherit from (extend) JpaRepository
4. Annotate the interface with **@Repository** tag

.. and I'm done!

# Controller

Create a new package named controller
Create a new class named BookController inside the package
Annotate the class with **@RestController**

Declare a property of type BookRepository inside the class. 
Annotate the property with **@Autowired**, which will inject and instantiate the Repository.

Add the following methods to the controller:

public List<Book> **getAllBooks()**
public void **addBook(Book book)**		C
public Book **getBookById(long id)**	R
public void **updateBook(Book book)**	U
public void **deleteBook(long id)**		D

## Add code to getAllBooks method

First of I need to map the method to a URL, which thankfully can be done via
**@GetMapping** annotation.
I map it to **/books**

Change the return type to **ResponseEntity<List<Book>>**, which will essentially return
an **HTTP-Response** object with the list will be converted to **JSON**

Inside the method try and retrieve the List from the Repository and relay the results to 
the consumer, by incorporating status codes in the response entity

## Add code to getBookById method

First of I need to map the method to a URL, which thankfully can be done via
**@GetMapping** annotation.
I map it to **/books/{id}**

Change the return type to **ResponseEntity<Book>**, which will return
an **HTTP-Response** object with the book converted to **JSON**

Inside the method try and retrieve the Book from the Repository and relay the results to 
the consumer, by incorporating status codes in the response entity

## Add code to addBook method

First of I need to map the method to a URL via **@GetMapping** annotation.
I map it to **/books/add**

Change the return type to **ResponseEntity<Book>**, which will return
an **HTTP-Response** object with the book converted to **JSON** and the book object
will contain the auto-generated **Id**

Use the **@RequestBody** annotation for the book parameter of the method. It lets the 
JPA know that this object must be extracted from the request.

Inside the method I call the save method on the Repository

## Add code to updateBook method

First of I need to map the method to a URL via **@GetMapping** annotation.
I map it to **/books/update**

Use the **@RequestBody** annotation for the book parameter of the method. It lets the 
JPA know that this object must be extracted from the request.

Inside the method I commit the changes to the database. If the update has been successful
the method will return a **ResponseEntity** with the **Book** object and HTTP-Staus **OK**
Otherwise, the ResponseEntity will be empty and the HTTP-Status will be set to NOT_FOUND





 
