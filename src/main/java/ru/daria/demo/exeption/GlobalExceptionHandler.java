package ru.daria.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//закоменчено пока что, потому что swagger с ним не работает
/*
@ControllerAdvice("ru.daria.demo.exeption") //обрабатывает ошибку, если она где-то появилась в процесе работы
//swagger не работает с исключениями, поэтому используем эту аннотацию для того, чтобы swagger его пока не видел
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String getText(RuntimeException ex) {
        return "Ошибка";
    }

    @ExceptionHandler(TestException.class)
    @ResponseBody
    public ResponseEntity<?> getText(TestException ex) {
        //return "Ошибка " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка");
    }
}
*/