package com.kol92v.dins.exceptionhandling.exception;

/**
 * Исключение выбрасываемое при попытке получить сущность
 * которой нет в БД
 * */
public class GetEntityException extends EntityRequestException {

    public GetEntityException(int id) {
        super("There is no entity with ID = " +
                id + " in Database");
    }

    public GetEntityException(String substring) {
        super("There is no entity with substring = " +
                substring + " in Database");
    }

}
