package com.kol92v.dins.services;

import com.kol92v.dins.entity.EntityDB;
import java.util.List;

/**
 * Функциональный интерфейс отвечающий за поиск сущностей типа {@link Entity}
 * в БД по переданной подстроке.
 * */

@FunctionalInterface
public interface EntitySubstringFinder<Entity extends EntityDB> {

    /**
     * Метод возвращает список {@link Entity} найденных по переданной подстроке
     * @param substring подстрока по которой осуществляется поиска с использованием DAO
     * в самой БД
     * @return возвращает список найденных сущностей
     * */
    List<Entity> getEntityBySubstring(String substring);

}
