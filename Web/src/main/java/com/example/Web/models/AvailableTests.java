package com.example.Web.models;

import javax.persistence.*;
//открытые экспертом тесты для пользователя
@Entity
public class AvailableTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testId")
    private Tests tests;
}
