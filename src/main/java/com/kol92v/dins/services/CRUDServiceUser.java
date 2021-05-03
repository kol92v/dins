package com.kol92v.dins.services;

import com.kol92v.dins.dao.UserJpaRepository;
import com.kol92v.dins.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CRUDServiceUser extends CRUDService<User, UserJpaRepository> {

    public CRUDServiceUser(UserJpaRepository repository) {
        super(repository);
    }

    @Override
    public List<User> getEntityBySubstring(String substring) {
        return getEntityBySubstringFunctional(substring, repository::findByUserNameContains);
    }
}
