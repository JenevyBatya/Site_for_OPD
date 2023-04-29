package com.example.Web.models;

import com.example.Web.repo.OccupationRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Optional;

@Entity
public class Occupation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int occupation_id;

    private String occupation_name, description;

    public Occupation(String occupation_name,String description){
        this.occupation_name=occupation_name;
        this.description=description;
    }

    public Occupation() {
    }

    public int getOccupation_id() {
        return occupation_id;
    }

    public void setOccupation_id(int occupation_id) {
        this.occupation_id = occupation_id;
    }

    public String getOccupation_name() {
        return occupation_name;
    }

    public void setOccupation_name(String occupation_name) {
        this.occupation_name = occupation_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
