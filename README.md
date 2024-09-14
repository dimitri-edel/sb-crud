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

#