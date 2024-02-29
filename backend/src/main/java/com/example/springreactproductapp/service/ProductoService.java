package com.example.springreactproductapp.service;

import com.example.springreactproductapp.entity.ProductoEntity;
import com.example.springreactproductapp.exception.BusinessException;
import com.example.springreactproductapp.payload.ProductoEstadisticaResponse;
import com.example.springreactproductapp.payload.ProductoRequest;
import com.example.springreactproductapp.payload.ProductoResponseCombinaciones;
import com.example.springreactproductapp.repository.ProductoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import java.util.*;

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
    public ProductoEstadisticaResponse calcularEstadisticas() {
        ProductoEntity maxInventoryProduct = productoRepository.findMaxInventoryProduct();
        Double totalInventoryValue = productoRepository.findTotalInventoryValue();
        return ProductoEstadisticaResponse.builder()
                .maximoProducto(maxInventoryProduct)
                .valorTotalInventario(totalInventoryValue)
                .build();
    }

    public ProductoResponseCombinaciones encontrarCombinaciones(Double precioMaximo) {
            List<ProductoEntity> productos = productoRepository.findByPrecioLessThanEqual(precioMaximo);
            List<List<Object>> generateCombinations = generateCombinations(productos, precioMaximo);
            return ProductoResponseCombinaciones.builder().maxCombinacionesProductos(generateCombinations).build();
    }

    private List<List<Object>> generateCombinations(List<ProductoEntity> products, Double maxSumValue) {
        if (products.size() < 2) {
            throw new BusinessException("No hay suficientes productos para generar combinaciones");
        }
        PriorityQueue<List<Object>> pq = new PriorityQueue<>(new CombinationComparator());
        int n = products.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Double sum = products.get(i).getPrecio() + products.get(j).getPrecio();
                if (sum <= maxSumValue) {
                    List<Object> combination = new ArrayList<>();
                    combination.add(products.get(i).getNombre());
                    combination.add(products.get(j).getNombre());
                    combination.add(sum);
                    pq.offer(combination);
                }
                if(n>=3){
                    for (int k = j + 1; k < n; k++) {
                        Double tripleSum = sum + products.get(k).getPrecio();
                        if (tripleSum <= maxSumValue) {
                            List<Object> combination = new ArrayList<>();
                            combination.add(products.get(i).getNombre());
                            combination.add(products.get(j).getNombre());
                            combination.add(products.get(k).getNombre());
                            combination.add(tripleSum);
                            pq.offer(combination);
                        }
                    }
                }
            }
        }


        List<List<Object>> result = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < 5) {
            result.add(pq.poll());
            count++;
        }

        return result;
    }

    static class CombinationComparator implements Comparator<List<Object>> {
        @Override
        public int compare(List<Object> a, List<Object> b) {
            double sumA = (double) a.get(a.size() - 1);
            double sumB = (double) b.get(b.size() - 1);
            return Double.compare(sumB, sumA);
        }
    }
}
