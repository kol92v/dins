package com.kol92v.dins.entity;

import lombok.*;
import javax.persistence.*;

/**
 * Данный класс представляет Entity из таблицы <strong>NOTES</strong>
 * */

@Data
@Builder
@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
public class Note implements EntityDB {

    /**
     * Поле представляющее ID сущности из таблицы
     * */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Поле по которому осуществляется связь с таблицей <strong>USERS</strong>
     * по колонке <strong>OWNER_ID</strong>
     * */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

}
