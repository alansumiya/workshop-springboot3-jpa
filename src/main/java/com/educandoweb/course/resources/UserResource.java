package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController
@RequestMapping(value = "/users")  //caminho relacionado a entidade user na url
public class UserResource {
	
	@GetMapping //responde a requisição get do meu http
	public ResponseEntity<User> findAll(){
		//teste instanciando uma variável u com os dados
		User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		//retorna a variável em formato json
		return ResponseEntity.ok().body(u);
	}
}
