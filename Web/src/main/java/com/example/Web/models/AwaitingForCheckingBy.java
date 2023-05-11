package com.example.Web.models;

import javax.persistence.*;

@Entity
//@Table(name="awaiting_for_checking_by")
public class AwaitingForCheckingBy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "adjectiveId")
    private Adjective adjective;
    @ManyToOne
    @JoinColumn(name = "occupationId")
    private Occupation occupation;


    public AwaitingForCheckingBy(User user,Occupation occupation, Adjective adjective) {
        this.occupation = occupation;
        this.adjective = adjective;
        this.user = user;
    }

    public AwaitingForCheckingBy() {

    }
}
