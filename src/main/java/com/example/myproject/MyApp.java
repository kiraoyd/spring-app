package com.example.myproject; //specify which package this class is part of

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//stereotype annocation - specify class role to Spring (web REST app)
@RestController
//meta-annotation - sets Spring to guess how to configure itself based on the jar dependencies
// bootstraps application as an entry point
@SpringBootApplication
public class MyApp {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    //main class method
    //SpringApplication class bootstraps our application, starting the Tomcat webserver
    public static void main(String[] args) {
        //pass myApp.class to specify primary Spring component, along with any commanline-line args
        SpringApplication.run(MyApp.class, args);
    }

}