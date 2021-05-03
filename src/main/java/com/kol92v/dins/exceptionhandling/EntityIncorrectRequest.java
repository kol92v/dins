package com.kol92v.dins.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс представляющий объект который содержит сообщений из выловленного Exception
 * */

@AllArgsConstructor
@Setter
@Getter
public class EntityIncorrectRequest {
    private String info;
}
