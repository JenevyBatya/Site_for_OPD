package com.example.Web.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
//Список всех прилагательных ПВК
@Entity
public class Adjective {
    @Transient
    private List<String> chosen = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adjective_id;

    private String trait_name, category;
    public Adjective(String trait_name){
        this.trait_name=trait_name;

    }



    public Adjective() {

    }

    public int getAdjective_id() {
        return adjective_id;
    }

    public void setAdjective_id(int adjective_id) {
        this.adjective_id = adjective_id;
    }

    public String getTrait_name() {
        return trait_name;
    }

    public void setTrait_name(String trait_name) {
        this.trait_name = trait_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
