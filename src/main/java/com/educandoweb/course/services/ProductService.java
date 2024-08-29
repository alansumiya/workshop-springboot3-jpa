package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service //Registra como componente de serviço da spring
public class ProductService {

	@Autowired
	private ProductRepository repository;

	//método que retorna tudo que está no banco de dados
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	//método que retorna o usuário por id
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
