package com.careerpath.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DbDebugRunner {

    @Bean
    CommandLineRunner dbCheck(DataSource dataSource) {
        return args -> {
            try (Connection c = dataSource.getConnection()) {
                System.out.println("=== DB DEBUG ===");
                System.out.println("URL  : " + c.getMetaData().getURL());
                System.out.println("USER : " + c.getMetaData().getUserName());
                System.out.println("DB   : " + c.getCatalog());
                System.out.println("================");
            }
        };
    }
}
