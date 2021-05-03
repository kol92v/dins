package com.kol92v.dins.entity;

/**
 * Общий интерфейс для всех Entity
 * */

public interface EntityDB {

    /**
     * Предоставляет базовый функционал для получения объекта по ID представленном в БД
     * @return ID сущности из БД
     * */
    int getId();

}
