package com.example.Web.models;

import javax.persistence.*;
//оценка экспертом профессии, добавленной пользователем
@Entity
@Table(name = "ExpertsOpinion")

public class ExpertsOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "adjective_id")
    private Adjective adjective;

    public ExpertsOpinion(User user, Occupation occupation, Adjective adjective) {
        this.occupation = occupation;
        this.user = user;
        this.adjective = adjective;
    }

    public ExpertsOpinion() {

    }
}
