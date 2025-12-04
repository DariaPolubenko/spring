package ru.daria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daria.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUserName(String userName);

}
