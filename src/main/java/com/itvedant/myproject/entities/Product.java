package com.itvedant.myproject.entities;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Message should not be null")
    @NotEmpty(message = "Message should not be empty")
    @Length(min=3,max=50,message = "name should be of 3 to 20 char")
    private String name;

    @NotNull(message ="Price should not be null")
    @Min(value = 1, message = "Price should not be less than 1")
    @Max(value = 100000,message = "Price should not be greater than 100000 ")
    private Double price;

    @NotNull(message = "Description should not be null")
    @NotEmpty(message = "Description should not be empty")
    @Length(min=10,max =100,message = "Description should be of 10 to 100 char")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "UserOrder",
                joinColumns = @JoinColumn(name="product_id"),
                inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> users;
    

    private String imageUrl;


    
}
