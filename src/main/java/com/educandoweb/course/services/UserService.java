package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service //Registra como componente de serviço da spring
public class UserService {

	@Autowired
	private UserRepository repository;

	//método que retorna tudo que está no banco de dados
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//método que retorna o usuário por id
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
