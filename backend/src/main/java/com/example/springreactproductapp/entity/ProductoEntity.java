package com.example.springreactproductapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "descripcion",nullable = false)
    private String descripcion;
    @Column(name = "precio",nullable = false)
    private Double precio;
    @Column(name = "stock",nullable = false)
    private Integer stock;
}
