package com.example.billing_service.entities;

import com.example.billing_service.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private double price;

    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    @Transient
    private Product product;

}