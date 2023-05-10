package com.example.Web.models;

import javax.persistence.*;
//открытые экспертом тесты для пользователя
@Entity
public class AvailableTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testid")
    private Tests tests;
}
