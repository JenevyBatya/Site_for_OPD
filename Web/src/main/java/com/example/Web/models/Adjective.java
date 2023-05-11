package com.example.Web.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Список всех прилагательных ПВК
@Entity
//@Table(name = "adjective")
public class Adjective {
    @Transient
    private List<String> chosen = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "traitName")
    private String traitName;
    @Column(name = "category")
    private String category;

    public Adjective(String traitName) {
        this.traitName = traitName;

    }


    public Adjective() {

    }

    public int getAdjective_id() {
        return id;
    }

    public void setAdjective_id(int adjective_id) {
        this.id = adjective_id;
    }

    public String gettraitName() {
        return traitName;
    }

    public void settraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
