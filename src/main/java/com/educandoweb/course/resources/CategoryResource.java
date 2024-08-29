package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")  //caminho relacionado a entidade user na url
public class CategoryResource {
	
	//Criando uma dependência para o service
	@Autowired  //spring faz a injeção de dependência
	private CategoryService service;
	
	@GetMapping //responde a requisição get do meu http
	//referente ao método de userService que retorna todos os usuários do banco de dados
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();

		//retorna a variável em formato json
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  //indica que minha requisição vai aceitar o id dentro da url
	//o parâmetro que chega é pela url, para ser aceito precisa usar o @PathVariable
	//referente ao método de userService que retorna o usuário por id
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
