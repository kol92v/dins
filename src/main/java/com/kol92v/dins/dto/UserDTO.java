package com.kol92v.dins.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для класса {@link com.kol92v.dins.entity.User} реализующий интерфейс {@link DTO}
 * */

@Data
@Builder
public class UserDTO implements DTO {

    private int id;

    private String userName;

}
