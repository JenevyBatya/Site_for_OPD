package com.example.Web.models;

import javax.persistence.*;
//пройденные тесты пользователем
@Entity
@Table(name = "finishedUserTests")
public class FinishedUserTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int session_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testid")
    private Tests tests;

    public FinishedUserTests(User user, Tests tests) {
        this.user = user;
        this.tests = tests;
    }

    public FinishedUserTests() {

    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tests getTests() {
        return tests;
    }

    public void setTests(Tests tests) {
        this.tests = tests;
    }
}
