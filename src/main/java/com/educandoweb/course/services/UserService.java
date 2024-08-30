package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		//vai tentar dar o get, se n tiver eu lanço uma exceção
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//método que vai salvar um usuário novo no repositório
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		//Criado uma variável optional para varrer o banco de dados
		Optional<User> user = repository.findById(id);
		//verifica se está presente o id que queremos apagar
		if (user.isPresent()) {
			try {
				repository.deleteById(id);
				//trata caso o usuário já está vinculado a um pedido
			} catch (DataIntegrityViolationException e) {
				throw new DataBaseException(e.getMessage());
			}
		} else {
			//se o id não estiver presente ele trata com o erro personalizado não presente
			throw new ResourceNotFoundException(id);
		}
	}
	
	public User update(Long id, User obj) {
		try {
			//ele não acessa de cara o dado, ele prepara o objeto monitorado pra vc mexer e depois
			//efetuar uma operação no banco de dados, é mais eficiente
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	//método que de fato faz a atualização no banco de dados
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
