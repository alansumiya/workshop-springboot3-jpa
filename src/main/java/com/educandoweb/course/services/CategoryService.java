package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

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
}
