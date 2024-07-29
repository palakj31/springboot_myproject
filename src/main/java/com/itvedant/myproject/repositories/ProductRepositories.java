package com.itvedant.myproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.itvedant.myproject.entities.Product;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Integer> {
    @RestResource(path ="namecontain")
    List<Product> findByNameContaining(String pattern);
}
