package com.course.jpa.repositories;

import com.course.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person,Long> {
    @Query("select p from Person p where p.id=?1")
    Optional<Person>findOne(Long id);
    @Query("select p from Person p where p.name=?1")
    Optional<Person>findOneName(String name);
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person>findOneLikeName(String name);
    Optional<Person>findByNameContaining(String name);
    //Find by programming language using @QueryMethod
    List<Person>findByProgrammingLanguage(String programmingLanguage);

    //Find by programming language and name using @QueryMethod
    List<Person>findByProgrammingLanguageAndName(String programmingLanguage,String name);

    //Find by programming language using @Query
    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person>byProgrammingLanguage(String programmingLanguage);
    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> getPersonData();
    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> getPersonData(String name);
    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1 and p.programmingLanguage=?2")
    List<Object[]> getPersonData(String name, String programmingLanguage);
}

