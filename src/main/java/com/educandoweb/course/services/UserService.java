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
	
	//método que vai salvar um usuário novo no repositório
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		//ele não acessa de cara o dado, ele prepara o objeto monitorado pra vc mexer e depois
		//efetuar uma operação no banco de dados, é mais eficiente
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	//método que de fato faz a atualização no banco de dados
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
