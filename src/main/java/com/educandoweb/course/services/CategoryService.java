package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registra como componente de serviço da spring
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	//método que retorna tudo que está no banco de dados
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	//método que retorna o usuário por id
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		Optional<Category> carro = repository.findById(id);
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
	
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
		
	}
}
