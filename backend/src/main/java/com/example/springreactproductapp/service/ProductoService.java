package com.example.springreactproductapp.service;

import com.example.springreactproductapp.entity.ProductoEntity;
import com.example.springreactproductapp.payload.ProductoRequest;
import com.example.springreactproductapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    //Crear, Leer, Actualizar y Eliminar
    public ProductoEntity crearProducto(ProductoRequest productoRequest){
        ProductoEntity productoEntity = productoRequestToProductoEntity(productoRequest);
        return productoRepository.save(productoEntity);
    }
    private ProductoEntity productoRequestToProductoEntity(ProductoRequest productoRequest) {
        return ProductoEntity
                .builder()
                .nombre(productoRequest.getNombre())
                .descripcion(productoRequest.getDescripcion())
                .precio(productoRequest.getPrecio())
                .stock(productoRequest.getStock())
                .build();
    }
    public List<ProductoEntity> listarProductos() {
        List<ProductoEntity> productosEntities = productoRepository.findAll();
        return productosEntities.stream().toList();
    }
    public void eliminarProducto(Long idProducto) {
        ProductoEntity productoEntity = productoRepository.findById(idProducto)
                .orElseThrow(RuntimeException::new);

        productoRepository.delete(productoEntity);
    }
    public ProductoEntity actualizarProducto(Long idProducto,ProductoRequest productoRequest){
        ProductoEntity productoEntity = productoRepository.findById(idProducto)
                .orElseThrow(RuntimeException::new);
        if (!Objects.isNull(productoRequest.getNombre()) && !Objects.equals(productoEntity.getNombre(), productoRequest.getNombre())) {
            productoEntity.setNombre(productoRequest.getNombre());
        }
        if (!Objects.isNull(productoRequest.getDescripcion()) && !Objects.equals(productoEntity.getDescripcion(), productoRequest.getDescripcion())) {
            productoEntity.setDescripcion(productoRequest.getDescripcion());
        }
        if (!Objects.isNull(productoRequest.getPrecio()) && !Objects.equals(productoEntity.getPrecio(), productoRequest.getPrecio())) {
            productoEntity.setPrecio(productoRequest.getPrecio());
        }
        if (!Objects.isNull(productoRequest.getStock()) && !Objects.equals(productoEntity.getStock(), productoRequest.getStock())) {
            productoEntity.setStock(productoRequest.getStock());
        }
        return productoRepository.save(productoEntity);
    }
    public double calcularValorTotalInventario() {
        List<ProductoEntity> productos = productoRepository.findAll();
        double totalInventario = 0.0;
        for (ProductoEntity producto : productos) {
            totalInventario += producto.getPrecio() * producto.getStock();
        }
        return totalInventario;
    }

    public List<List<ProductoEntity>> encontrarCombinaciones(double valorMaximo) {

        List<List<ProductoEntity>> combinaciones = new ArrayList<>();
        // calcular las combinaciones y devolver una lista de listas con los productos y la suma de precios


        return combinaciones;
    }
}
