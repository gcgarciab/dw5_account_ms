package com.misiontic.account_ms;

import com.misiontic.account_ms.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class AccountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMsApplication.class, args);
	}

	@GetMapping
	public List<Person> hello() {
		return List.of(
				new Person("1", "Camilo Garcia", "gcgarciab@unal.edu.co", 28)
		);
	}
}
