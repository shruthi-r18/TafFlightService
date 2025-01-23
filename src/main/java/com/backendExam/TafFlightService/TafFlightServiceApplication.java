package com.backendExam.TafFlightService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TafFlightServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TafFlightServiceApplication.class, args);
    }

}
