package com.kol92v.dins.exceptionhandling;



import com.kol92v.dins.exceptionhandling.exception.EntityRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Обработчик выброшенных исключений
 * */

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectRequest> handleException(EntityRequestException exception) {
        return getResponseEntity(exception);
    }

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectRequest> handleException(SQLIntegrityConstraintViolationException exception) {
        return getResponseEntity(exception);
    }

    /**
     * Создает и возвращает обертку типа {@link ResponseEntity} для объекта типа
     * {@link EntityIncorrectRequest} из переданного в параметре исключения
     * @param exception используется для получения сообщения чтобы создать объект типа {@link EntityIncorrectRequest}
     * */
    private ResponseEntity<EntityIncorrectRequest> getResponseEntity(Exception exception) {
        EntityIncorrectRequest entityIncorrectRequest = new EntityIncorrectRequest(exception.getMessage());
        return new ResponseEntity<>(entityIncorrectRequest, HttpStatus.BAD_REQUEST);
    }


}
