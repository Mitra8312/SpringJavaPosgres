package com.example.daosism.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    private String secondName;

    private int age;

    @NotBlank
    private String family;

    public Person(String name, String secondName, int age, String family, Integer num) {
        this.setID(num);
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.family = family;
    }

    public Person(String name, String secondname, Integer age, String family) {
        this.name = name;
        this.secondName = secondname;
        this.age = age;
        this.family = family;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
