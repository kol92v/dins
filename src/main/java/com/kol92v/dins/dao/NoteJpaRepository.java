package com.kol92v.dins.dao;

import com.kol92v.dins.entity.Note;
import com.kol92v.dins.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * DAO для работы с Entity типа {@link Note} и ID типа {@link Integer}
 * */

public interface NoteJpaRepository extends JpaRepository<Note, Integer> {

    /**
     * Метод возвращает список всех {@link Note} у которых есть совпадение
     * в поле {@link Note#getPhoneNumber()} с подстрокой переданной в параметре
     * @param substring подстрока которая используется для поиска {@link Note} в БД
     * @return список типа {@link Note} найденных совпадаений, по переданному параметру в метод
     * */
    List<Note> findByPhoneNumberContains(String substring);


    /**
     * Метод возвращает список всех {@link Note} у которых есть совпадение
     * c полем {@link User#getId()} с ID переданным в параметре
     * @param id ownerID который используется для поиска {@link Note} в БД
     * @return список типа {@link Note} найденных совпадаений, по переданному параметру в метод
     * */
    List<Note> findByUser_Id(int id);

}
