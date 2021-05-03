package com.kol92v.dins.exceptionhandling.exception;

/**
 * Абстрактный класс для исключений которые выбрасываются при некорректных параметрах запроса к БД.
 * Наследуется от {@link RuntimeException}
 * */
public abstract class EntityRequestException extends RuntimeException {
    public EntityRequestException(String message) {
        super(message);
    }
}
