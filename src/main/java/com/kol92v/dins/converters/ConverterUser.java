package com.kol92v.dins.converters;

import com.kol92v.dins.dto.DTObj;
import com.kol92v.dins.dto.NoteDTO;
import com.kol92v.dins.dto.UserDTO;
import com.kol92v.dins.entity.EntityDB;
import com.kol92v.dins.entity.Note;
import com.kol92v.dins.entity.User;
import org.springframework.stereotype.Component;

/**
 * Данный класс реализует интерфейс {@link ConverterEntityDTO} и передает ему в параметры
 * {@link User} и {@link UserDTO} и реализует методы конвертации данных типов объектов,
 * такие как {@link ConverterEntityDTO#convert(DTObj)} и {@link ConverterEntityDTO#convert(EntityDB)}
 * */

@Component
public class ConverterUser implements ConverterEntityDTO<User, UserDTO>{

    /**
     * Метод конвертирует {@link User} в {@link UserDTO}
     * @param user объект типа {@link User} для конвертации
     * @return объект типа {@link UserDTO}
     * */

    @Override
    public UserDTO convert(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build();
    }

    /**
     * Метод конвертирует {@link UserDTO} в {@link User}
     * @param dto объект типа {@link UserDTO} для конвертации
     * @return объект типа {@link User}
     * */

    @Override
    public User convert(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .build();
    }

}
