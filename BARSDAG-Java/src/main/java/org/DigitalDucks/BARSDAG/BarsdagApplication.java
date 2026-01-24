package org.DigitalDucks.BARSDAG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarsdagApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarsdagApplication.class, args);
	}

}
