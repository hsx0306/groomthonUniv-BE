package com.sch.groomthon.hsx0306;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hsx0306Application {
	public static void main(String[] args) {
		SpringApplication.run(Hsx0306Application.class, args);
	}
}