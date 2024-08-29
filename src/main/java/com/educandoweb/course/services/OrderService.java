package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service //Registra como componente de serviço da spring
public class OrderService {

	@Autowired
	private OrderRepository repository;

	//método que retorna tudo que está no banco de dados
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	//método que retorna o usuário por id
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
