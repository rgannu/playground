package com.utopian.camel.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//CHECKSTYLE:OFF

/**
 * A sample Spring Boot application that starts the Camel routes.
 */
@SpringBootApplication
public class SampleApplication {
  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {
    SpringApplication.run(SampleApplication.class, args);
  }
}
//CHECKSTYLE:ON