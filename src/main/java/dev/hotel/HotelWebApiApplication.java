package dev.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("dev.repository")
@SpringBootApplication
public class HotelWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelWebApiApplication.class, args);
    }

}
