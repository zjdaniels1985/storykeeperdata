package edu.ctu.storykeeperdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Lombok annotation for logger
@Slf4j
// Spring annotation
@SpringBootApplication
public class StorykeeperDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorykeeperDataApplication.class, args);
        log.info("Springboot and mongodb sequence id generator started successfully.");
    }

}
