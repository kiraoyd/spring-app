# Notes and progress log

## 8/5/2024

### General Project Setup

Install Java ```sudo apt install openjdk-17-jdk```

Install Gradle ```sudo apt install gradle```

Create the build.gradle file, drop in template script.

Test ```gradle classes```.

Add the first dependency: spring-boot-starter-web to allow for web application development.
Add ```implementation 'org.springframework.boot:spring-boot-starter-web'``` to the build.gradle file, dependencies section.
Test: ```gradle dependencies```

Some issues on home machine with Gradle version updates, putting Gradle on the backburner for now.
Deleting build.gradle and .gradle directory.

Installed Maven: ``sudo apt install maven``
Added a pom.xl file to test out using Maven to build as well.

Test: ```mvn package```

Added in the ```spring-boot-starter-web``` dependency to the tree.
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

