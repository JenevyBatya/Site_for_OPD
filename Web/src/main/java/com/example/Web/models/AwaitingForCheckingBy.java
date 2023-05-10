package com.example.Web.models;

import javax.persistence.*;

@Entity
@Table(name="awaiting_for_checking_by")
public class AwaitingForCheckingBy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "adjective_id")
    private Adjective adjective;
    @ManyToOne
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;


    public AwaitingForCheckingBy(User user,Occupation occupation, Adjective adjective) {
        this.occupation = occupation;
        this.adjective = adjective;
        this.user = user;
    }
}
