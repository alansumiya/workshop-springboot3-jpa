package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //coloca algumas anotações do JPA para instruí-lo em converter os objetos para o modelo 
//relacional, sempre priorizando as especificações usando a biblioteca jakarta.persistence

@Table(name = "tb_user") //especifica o nome da tabela para não dar conflito com a classe user
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
		@Id //especifica que está é a chave primária
		@GeneratedValue(strategy = GenerationType.IDENTITY) //torna a variável auto-incrementável
		private Long id;
		private String name;
		private String email;
		private String phone;
		private String password;
		
		@JsonIgnore //como há uma associação entre order e user, um vai ficar chamando o outro
		//esse comando evita que entre em loop
		//associação com a entidade order chamada orders, que é para muitos, então é uma lista
		@OneToMany(mappedBy = "client") //a relação com a entidade order é de 1 para muitos
		//mapeado pelo atribuito client lá na entidade order
		private List<Order> orders = new ArrayList<>();
		
		public User() {
			
		}

		public User(Long id, String name, String email, String phone, String password) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public List<Order> getOrders() {
			return orders;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return Objects.equals(id, other.id);
		}

	
		
		
		
}
