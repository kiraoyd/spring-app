# spring-app
Playing around with building a Java Spring Boot Web Application (MVC, classic CRUD Resful API)

Database: postgresql
Data Abstraction Layer: Spring Boot JPA
View: Thymeleaf

Following these tutorials: 

https://docs.spring.io/spring-boot/tutorial/first-application/index.html

https://zetcode.com/all/#springboot

Build managed with Maven.

Detailed log of progress can be found in log.md file.

Latest Update: 8/8/2024

## To Build and Run: Linux (WSL Ubuntu)

When running locally....

Check that postgresql is running: ```sudo service postgresql status```

If down, start it: ```sudo service postgresql start```

Restart: ```sudo service postgresql restart```

 ```mvn clean install -U```

```mvn spring-boot:run```

Navigate to ```localhost:8080``` to view current page View.

Note: When editing in intellij, direct changes to the pom.xml file seem to cause 
a 30 second freeze to the program. Still troubleshooting this issue.


