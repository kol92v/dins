package com.kol92v.dins.services;

import com.kol92v.dins.dao.UserJpaRepository;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CRUDServiceUserTest {

    @Autowired
    private CRUDServiceUser crudServiceUser;
    @Autowired
    private UserJpaRepository userJpaRepository;
    private User user;

    @BeforeEach
    void initUser() {
        user = User.builder()
                .userName("TestName_sdgkd1ldfg4")
                .build();
    }

    @AfterEach
    void deleteUserFromDB() {
        userJpaRepository.delete(user);
    }


    @Test
    void successfulSaveUserInDataBase() {
        User userBeforeSave = User.builder()
                .userName(user.getUserName())
                .build();
        crudServiceUser.save(user);
        int id = user.getId();
        User userFromDB = userJpaRepository.findById(id).get();
        assertEquals(userBeforeSave.getUserName(), userFromDB.getUserName());
    }

    @Test
    void throwingSaveEntityExceptionDueToRepeatableSave() {
        crudServiceUser.save(user);
        assertThrows(SaveEntityException.class, () -> crudServiceUser.save(user));
    }

    @Test
    void successfulUpdateUserInDataBase() {
        String updateName = "TestName_sd124lkgg4";
        userJpaRepository.save(user);
        User expectUser = User.builder()
                .id(user.getId())
                .userName(updateName)
                .build();
        User userForUpdate = User.builder()
                .id(user.getId())
                .userName(updateName)
                .build();
        User userAfterUpdate = crudServiceUser.update(userForUpdate);
        assertEquals(expectUser.getId(), userAfterUpdate.getId());
        assertEquals(expectUser.getUserName(), userAfterUpdate.getUserName());
    }

    @Test
    void throwingUpdateEntityExceptionDueToUpdateMissingUser() {
        assertThrows(UpdateEntityException.class, () -> crudServiceUser.update(user));
    }

    @Test
    void successfulDeleteUserFromDataBase() {
        int id = userJpaRepository.save(user).getId();
        crudServiceUser.delete(id);
        assertFalse(userJpaRepository.findById(id).isPresent());
    }


    @Test
    void successfulGetUserFromDataBaseByID() {
        int id = userJpaRepository.save(user).getId();
        assertDoesNotThrow(() -> crudServiceUser.getEntity(id));
        User userFormDB = crudServiceUser.getEntity(id);
        assertEquals(user.getId(), userFormDB.getId());
        assertEquals(user.getUserName(), userFormDB.getUserName());

    }

    @Test
    void throwingGetEntityExceptionDueToGetMissingUserByID() {
        int id = userJpaRepository.save(user).getId();
        userJpaRepository.deleteById(id);
        assertThrows(GetEntityException.class, () -> crudServiceUser.getEntity(id));
    }

    @Test
    void successfulGetUserBySubstringFromDataBase() {
        userJpaRepository.save(user);
        assertDoesNotThrow(() -> crudServiceUser.getEntityBySubstring(user.getUserName()));
    }

    @Test
    void throwingGetEntityExceptionDueToGetMissingUserBySubstring() {
        assertThrows(GetEntityException.class, () -> crudServiceUser.getEntityBySubstring(user.getUserName()));
    }

    @Test
    void throwingEmptySubstringExceptionDueToEmptyString() {
        assertThrows(EmptyStringException.class, () -> crudServiceUser.getEntityBySubstring(""));
    }

}