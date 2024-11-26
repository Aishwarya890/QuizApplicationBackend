package com.quizapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}

//package com.quizapplication;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.Collections;
//
//@SpringBootApplication
//public class QuizApplication {
//
//    public static void main(String[] args) {
//        // Create the Spring application and set the port from the environment variable 'PORT'https://rewards.bing.com/
//        SpringApplication app = new SpringApplication(QuizApplication.class);
//        
//        // Set the default port from the environment variable PORT
//        app.setDefaultProperties(Collections.singletonMap("server.port", System.getenv("PORT")));
//        
//        app.run(args);
//    }
//}

