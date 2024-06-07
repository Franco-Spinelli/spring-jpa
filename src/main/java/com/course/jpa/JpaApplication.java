package com.course.jpa;

import com.course.jpa.entities.Person;
import com.course.jpa.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
	@Autowired
	private IPersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		findOne();

	}

	public void findOne(){
		Person person = null;
		Optional<Person>optionalPerson = personRepository.findOneName("Andres");
		if(optionalPerson.isPresent()){
			person = optionalPerson.get();
			System.out.println(person.getName());
		}
	}
	@Transactional
	public void create(){
		Person person = new Person(null,"Myke","Street","C");
		personRepository.save(person);
	}
	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please write the id to delete");
		long id = scanner.nextLong();
		personRepository.deleteById(id);
		/////
		Optional<Person>optionalPerson = personRepository.findById(id);
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			personRepository.delete(person);
		}
		////
		optionalPerson.ifPresentOrElse( personRepository::delete,()-> System.out.println("The person doesn't exits"));
	}
	@Transactional
	public void update(){
		Person person = null;
		Optional<Person>optionalPerson = personRepository.findById(1L);
		if(optionalPerson.isPresent()){
			person = optionalPerson.get();
			person.setProgrammingLanguage("C");
			Person personDb = personRepository.save(person);
			System.out.println(personDb);
		}
	}
	@Transactional(readOnly = true)
	public void list(){
		//List<Person>persons = (List<Person>) personRepository.byProgrammingLanguage("Java");
		List<Person>persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java","Andres");
		persons.stream().forEach(person -> {
			System.out.println(person.getName());
		});

		List<Object[]>data = personRepository.getPersonData("Andres");
		data.stream().forEach(person -> {
			System.out.println(person[0] + " is expert in " + person[1]);
		});
	}
}
