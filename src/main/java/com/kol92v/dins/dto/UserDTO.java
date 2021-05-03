package com.kol92v.dins.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для класса {@link com.kol92v.dins.entity.User} реализующий интерфейс {@link DTObj}
 * */

@Data
@Builder
public class UserDTO implements DTObj {

    private int id;

    private String userName;

}
