# Notes and progress log

## 8/5/2024

### General Project Setup

Install Java ```sudo apt install openjdk-17-jdk```


### GRADLE

Gradle configuration file structure reminds me of Docker or Terraform JSON style setup.

Install Gradle ```sudo apt install gradle```

Create the build.gradle file, drop in template script.

Test ```gradle classes```.

Add the first dependency: spring-boot-starter-web to allow for web application development.
Add ```implementation 'org.springframework.boot:spring-boot-starter-web'``` to the build.gradle file, dependencies section.
Test: ```gradle dependencies```

Some issues on home machine with Gradle version updates, putting Gradle on the backburner for now.
Deleting build.gradle and .gradle directory.
Will come back to this at a later date.

### MAVEN

Maven configuration file structure reminds me of html parent-child structuring.

Installed Maven: ``sudo apt install maven``
Added a pom.xl file to test out using Maven to build as well.

Test: ```mvn package```

Added in the ```spring-boot-starter-web``` dependency to the tree.
Spring boot starters are a set of convinient dependency descriptiors that simplify configuration.
This one is for building web, RESTful applications using Spring MVC, using Tomcat as the default embedded container.


Added immediately below the ```<parent>``` section:
```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
</dependencies>
```

Test: ```mvn dependency:tree```

Setup the file structure:

1. create ```src/main/java```
2. Create application file ```MyApp.java```
3. Copy in the template code for the Application class ```MyApp```, and the main class method to run the application.
4. Test starter example: ```mvn spring-boot:run```, open browser to ```localhost:8080``` 

Note: had issues with the mvn build, might be port 8080 is in use.
Ran sudo netstat -tuln | grep :8080 to check, port 8080 is not in use.
missing resources directory, created this in ```src```, added in application.properties file. 
Cleaned up my pom.xml to remove optional dependencies to see if that fixed the issue.
Added in missing spring boot plugin section to pom.xml.
Ran ```mvn clean install -U```, build success, but mvnspring-boot:run still failing.

Trying to run the application JAR directly: ```mvn clean package```, then ```java -jar target/myproject-0.0.1-SNAPSHOT.jar```
Nope, still failing on the run.

Ok finally found the issue! Looks like my project file structure wasn't set up correctly.
The Application class in MyApp.java can't be in the default package at the root of src/main/java.
So I added the com/example/myproject path and nexted MyApp.java there (thank you chatGPT).
I included the package declaration at the top of MyApp.java to ensure it was not part of the default package.
This also helps Spring Boot's component scanning pick up my application class, as the @ApringBootApplication annotation is typically used in a class that resides in a top-level package.

## 8/8/2024

The tiny hello world app is running, so I feel confident about the state of my setup. 
Note: the ```@SpringBootApplication``` annotation marks the entry point to the appication for Spring, it must live 
in the MyApp.java file.

### SETUP ALL DIRECTORIES

Time to start setting up the file structure for the application:

1. Controller - handle incoming HTTP requests and outgoing responses to the client. Interacts with the Service layer.
2. Service - performs needed operations and provides the apps services, interacts with the Repository to access data Models. Calls repository methods like ```findAll()``` and ```update()```
3. Repository - handles data access logic (Create, Retreive, Update, Delete) with the Model. Handles queries to the Model.
4. Model - data representation, will likely be setting things up to use a postgres AWS DB, set up as entity data classes in Java
5. Resources - configuration file for Spring Boot
6. Templates - HTML templates for rendering views (index.html, etc)

Controller, model, repository and service should exist on the same level as the MyApp.java file. 
Resources lives at the same level as the ```java``` directory, with templates nested one level below resources.
https://zetcode.com/all/#springboot


Order of build:
1. Set up Spring Data JPA, and establish the postgresql database.
2. 

### DEFINE THE MODEL

Will start by setting up a postgresql database.

Download postgres: ```sudo apt-get install postgresql```

Check that postgresql is running: ```sudo service postgresql status```

If down, start it: ```sudo service postgresql start```

Enable start on boot (for convinience): ```sudo systemctl enable postgresql```

Install postgreSQL server and related packages: ```/etc/init.d/postgresql status```

check status: ```sudo -u postgres psql postgres```

# NOTE: running into a little hiccup with a previous postreqsql setup on my machine, return here next work period.

Add ```        
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<version>42.6.0</version>
</dependency>``` 
dependency into the Maven tree in pom.xml.

Update the application.properties file:

```terraform
spring.main.banner-mode=off
logging.level.org.springframework=ERROR

spring.jpa.hibernate.ddl-auto=none

spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=postgres
spring.datasource.password=s$cret

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

banner-mode property turns off Spring banner.
hibernate.ddl-auto=none turns off automatic schema creation.

To keep it simple, lets make this App a sidekick finder.
Users can input descriptors for their character, and the App will run analysis to choose the best Sidekick character from the database.
So the Model will be represented by the following tables:

characters (id, name, power_level, elemental_type, magic_type, personality)
magic_type (id, name)
elemental_type( id, name)
personality(id, name)

CRUD operations will work on the characters table, magic_type, personality, and elemental_type will hold a predefined set of data.

Create the Character.java file in the model directory, to build out the Class mapping to that table.
Declare package.
Import statements.
Apply ```@Entity``` attribute and ```@Table(name = "characters)``` attribute for table mapping.

### BUILD REPOSITORY INTERFACES

Add ```spring-boot-starter-data-jpa``` to the pom.xml to allow use for the Spring Data JPA.

What is the Spring Data JPA?
An abstraction over the data access later, allowing the definition of a repository interface.
Repositiory interfaces are defined by extending the ```JpaRepository``` class.
Repository interfaces are injected into the Service class as a private data member.
Allows me to define query methods directly in the repositiory interface using method naming conventions, and avoiding the use of raw SQl.
Spring Data JPA will generate CRUD implementations at runtime.
JPA entity classes map to a database table.

### DEVELOP SERVICE CLASS

### BUILD THE CONTROLLER

https://zetcode.com/springboot/model/

Try using Model, ModelMap, ModelAndView to pass data from Controller tp View.

CREATE VIEW 

Adding in the ```spring-boot-starter-thymeleaf``` starter to pom.xml to enable using Thymeleaf views.