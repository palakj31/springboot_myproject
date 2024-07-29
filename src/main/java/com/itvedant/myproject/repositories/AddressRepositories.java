package com.itvedant.myproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.myproject.entities.Address;

public interface AddressRepositories extends JpaRepository<Address, Integer> {
    
}
