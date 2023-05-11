package com.example.Web.models;

import javax.persistence.*;
import java.util.Set;

//всевозможные тесты
@Entity
//@Table(name = "Tests")
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testid;

    private String name, description;
    @OneToMany(mappedBy = "tests")
    private Set<AvailableTests> availableTestsSet;

    public int getTest_id() {
        return testid;
    }

    public void setTest_id(int test_id) {
        this.testid = test_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AvailableTests> getAvailableTestsSet() {
        return availableTestsSet;
    }

    public void setAvailableTestsSet(Set<AvailableTests> availableTestsSet) {
        this.availableTestsSet = availableTestsSet;
    }
}
