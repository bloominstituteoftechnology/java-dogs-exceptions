package com.jasontyzzer.javadogs;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Dog {
    private @Id @GeneratedValue Long id;
    private String breed;
    private int weight;
    private boolean apartment;

    public Dog(){

    }

    public Dog(String breed, int weight, boolean apartment) {
        this.breed = breed;
        this.weight = weight;
        this.apartment = apartment;
    }
}
