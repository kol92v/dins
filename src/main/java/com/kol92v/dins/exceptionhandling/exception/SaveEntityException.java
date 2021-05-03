package com.kol92v.dins.exceptionhandling.exception;


import com.kol92v.dins.entity.EntityDB;


/**
 * Исключение выбрасываемое при попытке повторно сохранить
 * уже существующую сущность в БД
 * */
public class SaveEntityException extends EntityRequestException {

    public SaveEntityException(EntityDB entity) {
        super("This entity is present in " +
                "Database already: " + entity);
    }

}
