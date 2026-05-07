package com.ruslk.pricetracker.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Double targetPrice;
    private Double currentPrice;

}
