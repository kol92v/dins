package com.kol92v.dins.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для класса {@link com.kol92v.dins.entity.Note} реализующий интерфейс {@link DTObj}
 * */

@Data
@Builder
public class NoteDTO implements DTObj {

    int id;

    String phoneNumber;

    int ownerId;
    
}
