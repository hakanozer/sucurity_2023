package com.works.models;

import java.util.List;

@lombok.Data
public class Product {
    private long id;
    private String title;
    private String description;
    private long price;
    private double discountPercentage;
    private double rating;
    private long stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;
}