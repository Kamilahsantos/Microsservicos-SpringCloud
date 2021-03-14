package com.codaretalks.api.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    private long id;
    private String name;
    private String level;

    public Student(long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level", nullable = false)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



    public Student() {
    }




}
