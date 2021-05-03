package com.kol92v.dins.converters;

import com.kol92v.dins.dto.UserDTO;
import com.kol92v.dins.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterUserTest {

    private final ConverterUser converterUser = new ConverterUser();
    private static User user;
    private static UserDTO userDTO;

    @BeforeAll
    static void initEntityDTO() {
        user = User.builder()
                .id(1)
                .userName("TestName")
                .build();
        userDTO = UserDTO.builder()
                .id(1)
                .userName("TestName")
                .build();
    }
    @Test
    void equalExpectDTOtoActualDTOFromConverterEntityToDTO() {
        assertEquals(userDTO, converterUser.convert(user));
    }

    @Test
    void equalExpectEntityToActualEntityFromConverterDTOtoEntity() {
        assertEquals(user, converterUser.convert(userDTO));
    }

}