package ru.daria.controller;

import org.springframework.web.bind.annotation.*;
import ru.daria.dto.response.authentication.ResponseAuthentication;
import ru.daria.entity.Person;
import ru.daria.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person") //проверить контроллер, и проверить пароль
public class UserController {
    private PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping("{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/authentication")
    public ResponseAuthentication authentication(@RequestBody Person person) {
        return personService.authentication(person);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return personService.validateToken(token);
    }
}
