package com.course.jpa;

import com.course.jpa.entities.Person;
import com.course.jpa.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Autowired
	private IPersonRepository personRepository;
	@Override
	public void run(String... args) throws Exception {
		//List<Person>persons = (List<Person>) personRepository.byProgrammingLanguage("Java");
		List<Person>persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java","Andres");
		persons.stream().forEach(person -> {
			System.out.println(person.getName());
		});
	}
}
