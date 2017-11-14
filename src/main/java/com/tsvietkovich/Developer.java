package com.tsvietkovich;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;
     private String name;
     private String language;
     private Integer age;
     private java.sql.Timestamp time;

    public Developer(String name, String language, Integer age) {
        this.name = name;
        this.language = language;
        this.age = age;
    }

    public Developer() {
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
