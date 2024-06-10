package com.course.jpa.repositories;

import com.course.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person,Long> {
    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name) from Person p)")
    public List<Object[]>getShorterName();
    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();
    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();
    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();
    @Query("select count(p) from Person p")
    Long getTotalPerson();
    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    //Between with QueryMethod
    List<Person>findByIdBetweenOrderByIdAsc(Long id1, Long id2);
    List<Person>findByIdBetween(Long id1, Long id2);
    List<Person>findByNameBetween(String id1, String id2);
    @Query("SELECT p form Person where p.id between ?1 and ?2 order by p.name")
    List<Person>findAllBetweenId(Long id1, Long id2);

    @Query("SELECT lower(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatLower();

    @Query("SELECT upper(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    //@Query("SELECT concat(p.name, ' ', p.lastname )as fullName FROM Person p")
    @Query("SELECT p.name || ' ' || p.lastname FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT count(p.name) FROM Person p")
    Long allNameCount();

    @Query("SELECT count(distinct(p.programmingLanguage)) FROM Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("SELECT distinct(p.programmingLanguage) FROM Person p")
    List<String>findAllProgrammingLanguageDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String>findAllNames();

    @Query("SELECT distinct(p.name) FROM Person p")
    List<String>findAllNamesDistinct();

    @Query("SELECT p.name FROM Person p where p.id=?1")
    String getNameById(Long id);

    @Query("SELECT concat(p.name, ' ', p.lastname )as fullName FROM Person p where p.id=?1")
    String getFullNameById(Long id);

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

