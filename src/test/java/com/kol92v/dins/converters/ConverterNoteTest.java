package com.kol92v.dins.converters;

import com.kol92v.dins.dto.NoteDTO;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterNoteTest {

    private final ConverterNote converterNote = new ConverterNote();
    private static Note note;
    private static NoteDTO noteDTO;

    @BeforeAll
    static void initEntityDTO() {
        note = Note.builder()
                .id(10)
                .phoneNumber("123")
                .user(getUser())
                .build();
        noteDTO = NoteDTO.builder()
                .id(10)
                .ownerId(1)
                .phoneNumber("123")
                .build();
    }

    private static User getUser() {
        return User.builder()
                .id(1)
                .build();
    }

    @Test
    void equalExpectDTOtoActualDTOFromConverterEntityToDTO() {
        assertEquals(noteDTO, converterNote.convert(note));
    }

    @Test
    void equalExpectEntityToActualEntityFromConverterDTOtoEntity() {
        assertEquals(note, converterNote.convert(noteDTO));
    }
}