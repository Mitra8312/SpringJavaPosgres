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
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    private Integer level;

    @NotBlank
    @Column(unique = true)
    private String hash;

    @NotBlank
    private String loc;

    @NotBlank
    private String product;

    public Cell(String name, int level, String hash, String loc, String product, int num) {
        this.setID(num);
        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public Cell(String name, Integer level, String hash, String loc, String product) {

        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
