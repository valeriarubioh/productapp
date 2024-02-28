package com.example.springreactproductapp.controller;

import com.example.springreactproductapp.entity.ProductoEntity;
import com.example.springreactproductapp.payload.ProductoRequest;
import com.example.springreactproductapp.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping
    public ResponseEntity<List<ProductoEntity>> obtenerProductos() {
        List<ProductoEntity> productos = productoService.listarProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductoEntity> crearProducto(@RequestBody @Valid ProductoRequest productoRequest) {
        return new ResponseEntity<>(productoService.crearProducto(productoRequest), HttpStatus.CREATED);
    }
    @DeleteMapping({"/{idProducto}"})
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.noContent().build();
    }
    @PutMapping({"/{idProducto}"})
    public ResponseEntity<ProductoEntity> actualizarProducto(@PathVariable Long idProducto, @RequestBody @Valid ProductoRequest productoRequest) {
        return new ResponseEntity<>(productoService.actualizarProducto(idProducto,productoRequest), HttpStatus.OK);
    }
}
