package edu.ctu.storykeeperdata.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    // Creating an object for faker
    @Bean
    public Faker faker() {
        return new Faker();
    }

}
