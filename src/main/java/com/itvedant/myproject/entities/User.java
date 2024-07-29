package com.itvedant.myproject.entities;


import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "First Name is requiured")
    @NotEmpty(message = "First Name is empty")
    private String firstName;

    @NotNull(message ="Last Name is required")
    @NotEmpty(message = "Last Name is empty")
    private String lastName;

    @NotNull
    @Email
    private String email;

   @Transient
    private String password;
   @Transient
    private String confirmPassword;

    @JoinColumn(name = "add_id")
    @OneToOne
    private Address userAddress;

    @ManyToMany(mappedBy = "users")
    private List<Product> userProduct;
    
}
