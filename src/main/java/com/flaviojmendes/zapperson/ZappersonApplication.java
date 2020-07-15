package com.flaviojmendes.zapperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.flaviojmendes.zapperson.repository")
public class ZappersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZappersonApplication.class, args);
	}

}
