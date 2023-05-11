package com.example.Web.models;

import javax.persistence.*;


@Entity
//@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;

    public UserRole(User user_id, Role role) {
        this.userId = user_id;
        this.role = role;
    }

    public UserRole() {

    }
    // getters and setters
}
