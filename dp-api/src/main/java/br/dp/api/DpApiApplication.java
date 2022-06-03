package br.dp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.dp.*" })
public class DpApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DpApiApplication.class, args);
	}

}
