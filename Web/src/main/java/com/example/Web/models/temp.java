package com.example.Web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class temp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String trait_name;

    public temp(int id, String trait_name) {
        this.id = id;
        this.trait_name = trait_name;
    }

    public temp() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrait_name() {
        return trait_name;
    }

    public void setTrait_name(String trait_name) {
        this.trait_name = trait_name;
    }
}
