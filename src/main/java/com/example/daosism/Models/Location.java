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
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Column(unique = true)
    private String address;

    private Integer CountCell;

    @NotBlank
    private String MainPerson;

    public Location(String name, String description, String address, int countCell, String mainPerson, int num) {
        this.setID(num);
        this.name = name;
        this.description = description;
        this.address = address;
        CountCell = countCell;
        MainPerson = mainPerson;
    }

    public Location(String name, String address, String description, Integer countCells, String mainPerson) {
        this.name = name;
        this.description = description;
        this.address = address;
        CountCell = countCells;
        MainPerson = mainPerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
