package ru.daria.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//мультимодульный
@SpringBootApplication //на компьютере запускает порт, по которому можно взаимодейтсвовать
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//SpringApplication.run - запуск приложения и создание на его основе точки входа в программу, запустит программу и не закроет

		//500 ошибка на стороне сервера в логике
	}
}
