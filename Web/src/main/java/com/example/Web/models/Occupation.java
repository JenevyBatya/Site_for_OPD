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
    private int id;

    private String occupationName, description;

    @OneToMany(mappedBy = "occupation")
    private Set<ExpertsOpinion> expertsOpinions;
    public Occupation(String occupation_name,String description){
        this.occupationName=occupation_name;
        this.description=description;
    }

    public Occupation() {
    }

    public int getOccupation_id() {
        return id;
    }

    public void setOccupation_id(int occupation_id) {
        this.id = occupation_id;
    }

    public String getOccupation_name() {
        return occupationName;
    }

    public void setOccupation_name(String occupation_name) {
        this.occupationName = occupation_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
