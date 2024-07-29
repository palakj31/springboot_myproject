package com.itvedant.myproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itvedant.myproject.entities.User;


@Repository
public interface UserRepositories extends JpaRepository<User,Integer>{

}
    

