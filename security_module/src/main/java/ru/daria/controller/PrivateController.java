package ru.daria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class PrivateController {

    @GetMapping("/private")
    public String sayHello() {
        return "Hello!";
    }
}
