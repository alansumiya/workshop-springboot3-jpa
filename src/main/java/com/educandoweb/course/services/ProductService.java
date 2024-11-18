package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		Optional<Product> carro = repository.findById(id);
		if(carro.isPresent()) {
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataBaseException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Product update(Long id, Product obj) {
		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
		entity.setPrice(obj.getPrice());
		
	}
}
