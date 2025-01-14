package com.example.diploma_thesis.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private int price;

    @Column(name="city")
    private String city;

    @Column(name="previewImageId")
    private Long previewImageId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}
