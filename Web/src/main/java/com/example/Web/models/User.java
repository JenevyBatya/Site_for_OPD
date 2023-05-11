package com.example.Web.models;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String username, password,email,gender;
    @Column
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<AvailableTests> getAvailableTestsSet() {
        return availableTestsSet;
    }

    public void setAvailableTestsSet(Set<AvailableTests> availableTestsSet) {
        this.availableTestsSet = availableTestsSet;
    }

    private boolean active = true;
    @OneToMany(mappedBy = "userId")
    private Set<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    private Set<AvailableTests> availableTestsSet;

    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email=email;

    }

    public User() {
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }


    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRole> getRoles() {
        return userRoles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.userRoles = roles;
    }
}

//public enum Role {
//    USER,
//    ADMIN
//}
