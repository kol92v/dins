package com.kol92v.dins.exceptionhandling.exception;

/**
 * Исключение выбрасываемое если параметр запроса типа String
 * пустой или содержит одни лишь пробелы
 * */
public class EmptyStringException extends EntityRequestException{

    private EmptyStringException(String message) {
        super(message);
    }

    public EmptyStringException() {
        this("Please enter the correct request in the query parameter");
    }

}
