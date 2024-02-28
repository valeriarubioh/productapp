package com.example.springreactproductapp.repository;

import com.example.springreactproductapp.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    List<ProductoEntity> findByPrecioLessThanEqual(Double precioMaximo);

}
