package com.itvedant.myproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.myproject.entities.Category;

@Repository
public interface CategoryRepositories extends CrudRepository<Category,Integer>{

    
}
