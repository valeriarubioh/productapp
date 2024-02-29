package com.example.springreactproductapp.repository;

import com.example.springreactproductapp.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    List<ProductoEntity> findByPrecioLessThanEqual(Double precioMaximo);

    @Query("SELECT SUM(p.precio * p.stock) FROM ProductoEntity p")
    Double findTotalInventoryValue();
    @Query("SELECT p FROM ProductoEntity p \n" +
            "WHERE p.stock * p.precio = (SELECT MAX(p2.stock * p2.precio) FROM ProductoEntity p2)")
    ProductoEntity findMaxInventoryProduct();
}
