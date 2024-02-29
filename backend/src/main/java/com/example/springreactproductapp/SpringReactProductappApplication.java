package com.example.springreactproductapp;

import com.example.springreactproductapp.entity.ProductoEntity;
import com.example.springreactproductapp.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringReactProductappApplication implements CommandLineRunner {
	private final ProductoRepository productoRepository;

	public SpringReactProductappApplication(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringReactProductappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Datos iniciales
		Optional<ProductoEntity> first = productoRepository.findByNombre("A");
		if (first.isEmpty()) {
			productoRepository.save(ProductoEntity.builder()
					.nombre("A")
					.stock(5)
					.descripcion("first")
					.precio(2.0)
					.build());
		}

		Optional<ProductoEntity> second = productoRepository.findByNombre("B");
		if (second.isEmpty()) {
			productoRepository.save(ProductoEntity.builder()
					.nombre("B")
					.stock(4)
					.descripcion("second")
					.precio(4.0)
					.build());
		}

		Optional<ProductoEntity> third = productoRepository.findByNombre("C");
		if (third.isEmpty()) {
			productoRepository.save(ProductoEntity.builder()
					.nombre("C")
					.stock(3)
					.descripcion("third")
					.precio(6.0)
					.build());
		}

		Optional<ProductoEntity> fourth = productoRepository.findByNombre("D");
		if (fourth.isEmpty()) {
			productoRepository.save(ProductoEntity.builder()
					.nombre("D")
					.stock(2)
					.descripcion("fourth")
					.precio(8.0)
					.build());
		}
	}
}
