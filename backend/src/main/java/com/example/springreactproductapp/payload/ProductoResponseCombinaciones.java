package com.example.springreactproductapp.payload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ProductoResponseCombinaciones {
    private List<List<Object>> maxCombinacionesProductos;
}
