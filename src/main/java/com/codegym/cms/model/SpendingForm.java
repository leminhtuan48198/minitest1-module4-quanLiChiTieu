package com.codegym.cms.model;

import org.springframework.web.multipart.MultipartFile;

public class SpendingForm {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private MultipartFile picture;

    public SpendingForm() {
    }

    public SpendingForm(Long id, String name, double price, String description, String category, MultipartFile picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.picture = picture;
    }

    public SpendingForm(Long id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
