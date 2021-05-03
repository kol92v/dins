package com.kol92v.dins.exceptionhandling.exception;

import com.kol92v.dins.entity.EntityDB;

/**
 * Исключение выбрасываемое при попытке обновить
 * сущность которой нет в БД
 * */
public class UpdateEntityException extends EntityRequestException {

    public UpdateEntityException(EntityDB entity) {
        super("There is no entity in Database: " +
                entity);
    }

}
