package ru.daria.demo.withDataBase.service;

import ru.daria.demo.entity.Person;

import java.util.Optional;

public interface IPersonDataBase { //сервис создается через интерфейс, чтобы потом не сломать логику
    public Optional<Person> getPerson(Long id);

    public Person create(Person person);

    public Person updatePerson(Long id, Person person);

    public void deletePerson(Long id);


}




