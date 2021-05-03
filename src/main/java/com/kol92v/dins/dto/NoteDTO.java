package com.kol92v.dins.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для класса {@link com.kol92v.dins.entity.Note} реализующий интерфейс {@link DTO}
 * */

@Data
@Builder
public class NoteDTO implements DTO {

    private int id;

    private String phoneNumber;

    private int ownerId;
    
}
