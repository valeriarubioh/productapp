package com.example.springreactproductapp.repository;

import com.example.springreactproductapp.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

}
