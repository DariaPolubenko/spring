package ru.daria.demo.withOutDataBase.service;

import org.springframework.stereotype.Service;
import ru.daria.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTest {
    List<Person> personList = new ArrayList<>();

    public Person createPerson(Person person) {
        if (person == null) {
            return null;
        }
        personList.add(person);
        return person;
    }

    public List<Person> getAllPersons() {
        return personList;
    }

    public Person getPersonById(Long userId) {
        return personList.get(Integer.valueOf(String.valueOf(userId)));
    }
}
