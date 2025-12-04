package ru.daria.demo.withDataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daria.demo.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
