package ru.daria.demo.withOutDataBase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daria.demo.withDataBase.dto.PersonDTO;
import ru.daria.demo.entity.Person;
import ru.daria.demo.withOutDataBase.service.ServiceTest;

import java.util.ArrayList;
import java.util.List;

@RestController //показывает спрингу и спринг сам его инициализирует(создает экземпляр класса, единичный на весь проект)
@RequestMapping("/person")
public class TestController {
    @Autowired //1 способ
    ServiceTest serviceTest; //сам инициализирует этот объект
    List<Person> personList = new ArrayList<>();

    public TestController() {
    }

    //MVC - паттерн (model-view-control)
    // model - модель данных (Person)
    // view - отображение данных
    // control - работа с моделью

    @PostMapping("/createPerson")
    public Person createPerson(@RequestBody Person person) {
        return serviceTest.createPerson(person);
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons() {
        return serviceTest.getAllPersons();
    }

    @GetMapping("/getPerson/{userId}")
    public PersonDTO getPersonById(@PathVariable Long userId) {
        Person person = serviceTest.getPersonById(userId);
        PersonDTO personDTO = new PersonDTO(person.getUsername(), person.getEmail());
        return personDTO;
    }

    public TestController(ServiceTest serviceTest) { //2 способ под замену Autowired, удобнее потому что больше полей можно подключить для взаимодействия
        this.serviceTest = serviceTest;
    }

//    @GetMapping //можем ловить запросы в браузере (GET- для получения данных, POST - для создания, PUT - обновить, DELETE - удалить)
//    public int getUserName() {
//        return serviceTest.some(20, 50);
//    }

    @PostMapping
    public String createUserName(@RequestParam String userName) {
        //RequestParam - принимает параментр из постман, типа значение переменной из запроса
        return userName;
    }
}
