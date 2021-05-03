package com.kol92v.dins.services;

import com.kol92v.dins.dao.NoteJpaRepository;
import com.kol92v.dins.dao.UserJpaRepository;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.entity.User;
import com.kol92v.dins.exceptionhandling.exception.EmptyStringException;
import com.kol92v.dins.exceptionhandling.exception.GetEntityException;
import com.kol92v.dins.exceptionhandling.exception.SaveEntityException;
import com.kol92v.dins.exceptionhandling.exception.UpdateEntityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CRUDServiceNoteTest {

    @Autowired
    private CRUDServiceNote crudServiceNote;
    @Autowired
    private NoteJpaRepository noteJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    private Note note;
    private User user;

    @BeforeEach
    void initNoteAndUser() {
        user = User.builder()
                .userName("TestName_sdgkd1ldfg4")
                .build();
        userJpaRepository.save(user);
        note = Note.builder()
                .user(User.builder().id(user.getId()).build())
                .phoneNumber("6541321-56465")
                .build();
    }

    @AfterEach
    void deleteNoteAndUser() {
        noteJpaRepository.delete(note);
        userJpaRepository.delete(user);
    }


    @Test
    void successfulSaveNoteInDataBase() {
        Note noteBeforeSave = Note.builder()
                .phoneNumber(note.getPhoneNumber())
                .user(User.builder().id(note.getUser().getId()).build())
                .build();
        crudServiceNote.save(note);
        int id = note.getId();
        Note noteFromDB = noteJpaRepository.findById(id).get();
        noteFromDB.setUser(User.builder().id(noteFromDB.getUser().getId()).build());
        assertEquals(noteBeforeSave.getPhoneNumber(), noteFromDB.getPhoneNumber());
        assertEquals(noteBeforeSave.getUser(), noteFromDB.getUser());
    }

    @Test
    void throwingSaveEntityExceptionDueToRepeatableSave() {
        crudServiceNote.save(note);
        assertThrows(SaveEntityException.class, () -> crudServiceNote.save(note));
    }

    @Test
    void successfulUpdateNoteInDataBaseWithoutUserForeignKeyColumn() {
        String updatePhoneNumber = "test4-79541-958";
        noteJpaRepository.save(note);
        Note expectNote = Note.builder()
                .id(note.getId())
                .user(note.getUser())
                .phoneNumber(updatePhoneNumber)
                .build();
        Note noteForUpdate = Note.builder()
                .id(note.getId())
                .user(note.getUser())
                .phoneNumber(updatePhoneNumber)
                .build();
        Note noteAfterUpdate = crudServiceNote.update(noteForUpdate);
        assertEquals(expectNote.getId(), noteAfterUpdate.getId());
        assertEquals(expectNote.getPhoneNumber(), noteAfterUpdate.getPhoneNumber());

    }

    @Test
    void successfulUpdateNoteInDataBaseWithUserForeignKeyColumn() {
        User updateUser = User.builder()
                .userName("TsetName_q3a2ikdf")
                .build();
        userJpaRepository.save(updateUser);
        noteJpaRepository.save(note);
        Note expectNote = Note.builder()
                .id(note.getId())
                .user(updateUser)
                .phoneNumber(note.getPhoneNumber())
                .build();
        Note noteForUpdate = Note.builder()
                .id(note.getId())
                .user(User.builder().id(updateUser.getId()).build())
                .phoneNumber(note.getPhoneNumber())
                .build();
        Note noteAfterUpdate = crudServiceNote.update(noteForUpdate);
        assertEquals(expectNote.getId(), noteAfterUpdate.getId());
        assertEquals(expectNote.getUser().getId(), noteAfterUpdate.getUser().getId());
        assertEquals(expectNote.getUser().getUserName(), noteAfterUpdate.getUser().getUserName());

        userJpaRepository.deleteById(updateUser.getId());
    }

    @Test
    void throwingUpdateEntityExceptionDueToUpdateMissingNote() {
        assertThrows(UpdateEntityException.class, () -> crudServiceNote.update(note));
    }

    @Test
    void successfulDeleteNoteFromDataBase() {
        int id = noteJpaRepository.save(note).getId();
        crudServiceNote.delete(id);
        assertFalse(noteJpaRepository.findById(id).isPresent());
    }

    @Test
    void successfulGetNoteFromDataBaseByID() {
        int id = noteJpaRepository.save(note).getId();
        assertDoesNotThrow(() -> crudServiceNote.getEntity(id));
        Note noteFromDB = crudServiceNote.getEntity(id);
        assertEquals(note.getId(), noteFromDB.getId());
        assertEquals(note.getPhoneNumber(), noteFromDB.getPhoneNumber());
        assertEquals(note.getUser().getId(), noteFromDB.getUser().getId());

    }

    @Test
    void throwingGetEntityExceptionDueToGetMissingNoteByID() {
        int id = noteJpaRepository.save(note).getId();
        noteJpaRepository.deleteById(id);
        assertThrows(GetEntityException.class, () -> crudServiceNote.getEntity(id));
    }

    @Test
    void successfulGetNoteBySubstringFromDataBase() {
        noteJpaRepository.save(note);
        assertDoesNotThrow(() -> crudServiceNote.getEntityBySubstring(note.getPhoneNumber()));
    }

    @Test
    void throwingGetEntityExceptionDueToGetMissingNoteBySubstring() {
        assertThrows(GetEntityException.class, () -> crudServiceNote.getEntityBySubstring(note.getPhoneNumber()));
    }

    @Test
    void throwingEmptySubstringExceptionDueToEmptyString() {
        assertThrows(EmptyStringException.class, () -> crudServiceNote.getEntityBySubstring(""));
        assertThrows(EmptyStringException.class, () -> crudServiceNote.getEntityBySubstring(" "));
    }

    @Test
    void successfulGetNotesByOwnerIdFromDataBase() {
        noteJpaRepository.save(note);
        int idOwner = note.getUser().getId();
        List<Note> notesByUserIDFromJpa = noteJpaRepository.findByUser_Id(idOwner);
        List<Note> notesByUserIdFromService = crudServiceNote.getNotesByOwnerId(idOwner);
        assertEquals(notesByUserIDFromJpa, notesByUserIdFromService);
    }

    @Test
    void throwingGetEntityExceptionDueToMissingNoteByOwnerId() {
        noteJpaRepository.save(note);
        int ownerId = note.getUser().getId();
        userJpaRepository.deleteById(ownerId);
        assertThrows(GetEntityException.class, () -> crudServiceNote.getNotesByOwnerId(ownerId));
    }

}
