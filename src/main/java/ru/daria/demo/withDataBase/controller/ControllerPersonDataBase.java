package ru.daria.demo.withDataBase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import ru.daria.demo.exeption.TestException;
import ru.daria.demo.withDataBase.dto.PersonDTO;
import ru.daria.demo.entity.Person;
import ru.daria.demo.withDataBase.service.PersonServiceDataBase;

import java.math.BigDecimal;

@RestController

public class ControllerPersonDataBase {
    PersonServiceDataBase personServiceDataBase;

    public ControllerPersonDataBase(PersonServiceDataBase personServiceDataBase) {
        this.personServiceDataBase = personServiceDataBase;
    }

    @Operation(summary = "Создание пользователя", description = " ")
    @ApiResponse(responseCode = "200", description = "success added")
    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person) {
        return personServiceDataBase.create(person);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получение пользователя", description = "по id")
    @ApiResponse(responseCode = "200", description = "success received")
    public PersonDTO getPerson(@PathVariable Long id) {
        Person person = personServiceDataBase.getPerson(id).get();
        PersonDTO personDTO = new PersonDTO(person.getUsername(), person.getEmail());
        return personDTO;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление пользователя", description = "по id")
    @ApiResponse(responseCode = "200", description = "success deleted")
    public void deletePerson(@PathVariable Long id) {
        personServiceDataBase.deletePerson(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Обновление пользователя", description = "по id")
    @ApiResponse(responseCode = "200", description = "success updated")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personServiceDataBase.updatePerson(id, person);
    }

    @GetMapping("/test")
    @Operation(summary = "Тестирование ошибки \"IllegalArgumentException\"", description = " ")
    @ApiResponse(responseCode = "400", description = "bad request")
    public void test() {
        throw new IllegalArgumentException("Ошибочка");
    }


    @GetMapping("/test/exception")
    @Operation(summary = "Тестирование ошибки \"TestException\"", description = " ")
    @ApiResponse(responseCode = "409", description = "conflict")
    public void testException() throws TestException {
        throw new TestException("TestException");
    }

    @PutMapping("/transferMoney")
    public void transferMoney(@RequestParam BigDecimal amount) {
        personServiceDataBase.transferMoney(amount);
    }
}
