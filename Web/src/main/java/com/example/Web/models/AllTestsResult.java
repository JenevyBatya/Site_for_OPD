package com.example.Web.models;

import javax.persistence.*;
//результаты теста после прохождения пользователем
@Entity
@Table(name = "allTestsResult")
public class AllTestsResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private FinishedUserTests finishedUserTests;

    private Double result_ms;

    public AllTestsResult(FinishedUserTests finishedUserTests, Double result_ms) {
        this.finishedUserTests = finishedUserTests;
        this.result_ms = result_ms;
    }

    public AllTestsResult() {

    }
}
