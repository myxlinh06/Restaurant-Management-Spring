package org.example.restaurant_management_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.restaurant_management_project.model")
public class RestaurantManagementProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantManagementProjectApplication.class, args);
    }

}
