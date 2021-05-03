package com.kol92v.dins.dao;


import com.kol92v.dins.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    List<User> findByUserNameContains(String substring);

}
