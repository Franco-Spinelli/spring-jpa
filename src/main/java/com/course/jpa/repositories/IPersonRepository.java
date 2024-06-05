package com.course.jpa.repositories;

import com.course.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonRepository extends CrudRepository<Person,Long> {
    //Find by programming language using @QueryMethod
    List<Person>findByProgrammingLanguage(String programmingLanguage);

    //Find by programming language and name using @QueryMethod
    List<Person>findByProgrammingLanguageAndName(String programmingLanguage,String name);

    //Find by programming language using @Query
    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person>byProgrammingLanguage(String programmingLanguage);
}

