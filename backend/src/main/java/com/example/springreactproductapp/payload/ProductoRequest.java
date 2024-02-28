package com.example.springreactproductapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotBlank(message = "Descripcion es requerida")
    private String descripcion;
    @NotNull(message = "Precio es requerido")
    private Double precio;
    @NotNull(message = "Cantidad en stock es requerido")
    private Integer stock;
}
