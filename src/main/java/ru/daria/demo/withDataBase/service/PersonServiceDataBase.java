package ru.daria.demo.withDataBase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.daria.demo.entity.Person;
import ru.daria.demo.withDataBase.repository.PersonRepository;

import java.math.BigDecimal;
import java.util.Optional;

// Транзакции - последовательные операции, которые выполняются по принципу "все или ничего". Есть acid-свойства:
// 1. Atomicity / атомарность - все операции выполняются или ни одна
// 2. Consistency / согласованность - данные переходят из одного валидного состояния в другое
// 3. Isolation / изолированность - транзакции не влияют друг на друга
// 4. Durability / долговечность - результаты фиксируются после завершения
// если во время операции происходит ошибка, операция отменяется и все возвращается обратно

@Service
public class PersonServiceDataBase implements IPersonDataBase {
    private final PersonRepository repository;

    public PersonServiceDataBase(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Optional<Person> getPerson(Long id) { //repository.getReferenceById(id) пока что заменен
        return repository.findById(id);
/*
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()) {
            return personOptional.get();
        } else {
            throw new IllegalArgumentException("Персонажа с таким id " + id + " не существует");
        }
 */
    }

    public void deletePerson(Long id) {
        Person person = repository.getReferenceById(id);
        if (person == null) {
            throw new IllegalArgumentException("Персонажа с таким id " + id + " не существует");
        } else {
            repository.delete(person);
        }
    }

    public Person updatePerson(Long id, Person person) {
        Person person1 = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Персонажа с таким id " + id + " не существует"));
        person1.setEmail(person.getEmail());
        person1.setPassword(person.getPassword());
        person1.setUsername(person.getUsername());
        repository.save(person1);
        return person1;
    }

    public int amount = 15000;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    //@Transactional //метод если норм пройдет - то и операция успешно пройдет
    //под капотом эта аннотация использует AOP (аспектно-ориентированное программирование)
    public void transferMoney(BigDecimal amount) {
        BigDecimal currentBalance = new BigDecimal(1000);
        this.amount -= amount.intValue();
        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        System.out.println("Успешно проведена операция" + this.amount);
    }
}
