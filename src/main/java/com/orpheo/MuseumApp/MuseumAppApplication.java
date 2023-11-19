package com.orpheo.MuseumApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MuseumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuseumAppApplication.class, args);
	}

}
