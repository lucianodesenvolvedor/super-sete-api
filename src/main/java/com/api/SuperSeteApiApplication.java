package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.api.config.FileStorageConfig;

@EnableConfigurationProperties({FileStorageConfig.class})
@SpringBootApplication
public class SuperSeteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperSeteApiApplication.class, args);

	}

}
