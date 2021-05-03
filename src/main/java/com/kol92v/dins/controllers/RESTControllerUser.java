package com.kol92v.dins.controllers;

import com.kol92v.dins.converters.ConverterEntityDTO;
import com.kol92v.dins.dao.UserJpaRepository;
import com.kol92v.dins.dto.UserDTO;
import com.kol92v.dins.entity.User;
import com.kol92v.dins.services.CRUDService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Данный RestController наследуется от обобщенного класса {@link MainRestController},
 * передает ему в параметры {@link User}, {@link UserDTO}, {@link UserJpaRepository} для работы с этими типами.
 * В поле {@link MainRestController#crudService} передается реализация {@link com.kol92v.dins.services.CRUDServiceUser},
 * в поле {@link MainRestController#converterEntityDTO} передается {@link com.kol92v.dins.converters.ConverterUser}
 * @see com.kol92v.dins.services.CRUDServiceUser
 * @see com.kol92v.dins.converters.ConverterUser
 * */

@RestController
@RequestMapping("/users")
public class RESTControllerUser extends MainRestController<User, UserDTO, UserJpaRepository>{

    public RESTControllerUser(@Qualifier("CRUDServiceUser") CRUDService<User, UserJpaRepository> crudService,
                              @Qualifier("converterUser") ConverterEntityDTO<User, UserDTO> converterEntityDTO) {
        super(crudService, converterEntityDTO);
    }
}
