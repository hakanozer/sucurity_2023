package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long pid;

    @Size(min = 2, max = 100)
    @NotEmpty
    @NotNull
    private String title;

    @Max(1000000)
    @Min(1)
    @NotNull
    private Integer price;


}
