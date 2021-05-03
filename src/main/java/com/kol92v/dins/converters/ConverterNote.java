package com.kol92v.dins.converters;

import com.kol92v.dins.dto.DTObj;
import com.kol92v.dins.dto.NoteDTO;
import com.kol92v.dins.entity.EntityDB;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.entity.User;
import org.springframework.stereotype.Component;

/**
 * Данный класс реализует интерфейс {@link ConverterEntityDTO} и передает ему в параметры
 * {@link Note} и {@link NoteDTO} и реализует методы конвертации данных типов объектов,
 * такие как {@link ConverterEntityDTO#convert(DTObj)} и {@link ConverterEntityDTO#convert(EntityDB)}
 * */

@Component
public class ConverterNote implements ConverterEntityDTO<Note, NoteDTO> {

    /**
     * Метод конвертирует {@link Note} в {@link NoteDTO}
     * @param note объект типа {@link Note} для конвертации
     * @return объект типа {@link NoteDTO}
     * */
    @Override
    public NoteDTO convert(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .phoneNumber(note.getPhoneNumber())
                .ownerId(note.getUser().getId())
                .build();
    }

    /**
     * Метод конвертирует {@link NoteDTO} в {@link Note}
     * @param dto объект типа {@link NoteDTO} для конвертации
     * @return объект типа {@link Note}
     * */
    @Override
    public Note convert(NoteDTO dto) {
        User user = new User();
        user.setId(dto.getOwnerId());
        return Note.builder()
                .id(dto.getId())
                .phoneNumber(dto.getPhoneNumber())
                .user(user)
                .build();
    }

}
