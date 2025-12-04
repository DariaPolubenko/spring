package ru.daria.exeptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Не найден");
    }
}
