package com.example.springreactproductapp.payload;

import com.example.springreactproductapp.entity.ProductoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductoEstadisticaResponse {
    private ProductoEntity maximoProducto;
    private Double valorTotalInventario;
}
