package com.ProjetoEscola.models.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {

	private Long id;
	private String Nome;
	private String userName;
	private String email;
	private String password;
	private String rePassword;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message = "O nome de usuário é obrigatório.")
	@NotNull(message = "O nome de usuário é obrigatório.")
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	@NotBlank(message = "O username de usuário é obrigatório.")
	@NotNull(message = "O username de usuário é obrigatório.")
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Email(message = "email inválido")
	@NotBlank(message = "O email é obrigatório.")
	@NotNull(message = "O email é obrigatório.")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank(message = "A senha é obrigatória.")
	@NotNull(message = "A senha é obrigatória.")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank(message = "A confirmação de senha é obrigatória.")
	@NotNull(message = "A confirmação de senha é obrigatória.")
	public String getRePassword() {
		return rePassword;
	}
	
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	@Override
	public String toString() {
		return "UserRequest [id=" + id + ", Nome=" + Nome + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", rePassword=" + rePassword + "]";
	}
	
}