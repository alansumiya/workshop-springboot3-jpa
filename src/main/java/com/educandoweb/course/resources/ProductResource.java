package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")  //caminho relacionado a entidade user na url
public class ProductResource {
	
	//Criando uma dependência para o service
	@Autowired  //spring faz a injeção de dependência
	private ProductService service;
	
	@GetMapping //responde a requisição get do meu http
	//referente ao método de userService que retorna todos os usuários do banco de dados
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();

		//retorna a variável em formato json
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  //indica que minha requisição vai aceitar o id dentro da url
	//o parâmetro que chega é pela url, para ser aceito precisa usar o @PathVariable
	//referente ao método de userService que retorna o usuário por id
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
