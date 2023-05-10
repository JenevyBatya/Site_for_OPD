package com.example.Web.models;

import com.example.Web.repo.OccupationRepository;

import javax.persistence.*;

import java.util.Optional;
import java.util.Set;

//Таблица всех профессий и описаний к ним
@Entity
public class Occupation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int occupation_id;

    private String occupation_name, description;

    @OneToMany(mappedBy = "occupation")
    private Set<ExpertsOpinion> expertsOpinions;
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
