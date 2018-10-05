package com.company.example;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Integer id;
    private String fullName;
    private String[] visitedCountries;

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Person(java.lang.Integer id) {
        this.id = id;
    }
    
    private Person[] getAllChildren() {
      return null;
    }
    
    private List<Person[]> getSomeChildren() {
      return null;
    }
}
