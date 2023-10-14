package com.ProjetoEscola.models.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor extends User{

	private static final long serialVersionUID = -1347533949773103524L;
	
	private String Nome;
	private boolean Diretor;
	
	@ManyToMany(mappedBy = "professor")
	private Set<Role> role = new HashSet<>();
	
	
	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Professor() {
	}
	
	public Professor(String nome, boolean diretor) {
		this.Nome = nome;
		this.Diretor = diretor;
	}
	
	@Column(name="NOME", length=100, nullable=true)
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	@Column(name="DIRETOR", length=1, nullable=true)
	public boolean isDiretor() {
		return Diretor;
	}
	
	public void setDiretor(boolean diretor) {
		Diretor = diretor;
	}

	@Override
	public String toString() {
		return "Professor [id=" + this.getId() + ", userName=" + this.getUserName() + ", email=" + this.getEmail() +
				", active=" + isActive() + ", Nome=" + Nome + ", Diretor=" + Diretor + ", Roles=" + role + " ]";
	}
	
	
	
}
