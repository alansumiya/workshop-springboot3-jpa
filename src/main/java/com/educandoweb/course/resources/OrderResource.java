package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")  //caminho relacionado a entidade user na url
public class OrderResource {
	
	//Criando uma dependência para o service
	@Autowired  //spring faz a injeção de dependência
	private OrderService service;
	
	@GetMapping //responde a requisição get do meu http
	//referente ao método de userService que retorna todos os usuários do banco de dados
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();

		//retorna a variável em formato json
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  //indica que minha requisição vai aceitar o id dentro da url
	//o parâmetro que chega é pela url, para ser aceito precisa usar o @PathVariable
	//referente ao método de userService que retorna o usuário por id
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
