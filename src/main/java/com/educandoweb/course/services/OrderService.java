package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		Optional<Order> carro = repository.findById(id);
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
	
	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Order entity, Order obj) {
		entity.setClient(obj.getClient());
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
	}

	
}
