package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")  //caminho relacionado a entidade user na url
public class UserResource {
	
	//Criando uma dependência para o service
	@Autowired  //spring faz a injeção de dependência
	private UserService service;
	
	@GetMapping //responde a requisição get do meu http, serve para recuperar dados no banco de dados
	//referente ao método de userService que retorna todos os usuários do banco de dados
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();

		//retorna a variável em formato json
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  //indica que minha requisição vai aceitar o id dentro da url
	//o parâmetro que chega é pela url, para ser aceito precisa usar o @PathVariable
	//referente ao método de userService que retorna o usuário por id
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping //quando queremos inserir dados no banco
	//pra dizer que o obj vai chegar no modo json na hora de fazer a requisição e este vai ser
	//deserializado para o objeto user no java, precisa colocar o @requestBody
	public ResponseEntity<User> insert(@RequestBody User obj){ 
		 obj = service.insert(obj);
		 //comando para montar uma url com o id de um novo user criado, pois a resposta do servidor
		 //para coisas novas criadas é o 201, se não fizer isso ele retorna 200
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				   .buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
