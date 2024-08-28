package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration //indica que essa classe é de configuração
@Profile("test")  //configuração específica para o perfil de teste. Tem que ser o mesmo nome que
//colocou em application.properties, assim ele somente vai rodar se vc estiver no perfil de teste
public class TestConfig implements CommandLineRunner {

	@Autowired  //para que o spring consiga resolver essa dependência e associar uma instância do
	//userRepository no testConfig
	private UserRepository userRepository;

	//método exido pela classe commandLineRunner. Tudo que estiver nesse método vai ser executado
	//quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 

		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
