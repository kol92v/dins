package com.kol92v.dins.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;


/**
 * Данный класс представляет Entity из таблицы <strong>USERS</strong>
 * */

@Data
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements EntityDB {

    /**
     * Поле представляющее ID сущности из таблицы
     * */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    /**
     * Поле по которому осуществляется связь с таблицей <strong>NOTES</strong>
     * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Note> notes;

}
