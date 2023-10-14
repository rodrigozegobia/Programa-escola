package com.ProjetoEscola.models.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_ALUNO")
public class Aluno extends User{

	private static final long serialVersionUID = 807322937167415872L;
	
	private String Nome;
	
	@ManyToMany(mappedBy = "aluno")
	private Set<Role> role = new HashSet<>();
	
	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Aluno() {
	}

	public Aluno(String nome) {
		Nome = nome;
	}

	@Column(name="NOME", length=100, nullable=false)
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String toString() {
		return "Aluno [id=" + this.getId() + ", userName=" + this.getUserName() + ", email=" + this.getEmail() +
				", active=" + isActive() + ", Nome=" + Nome + ", Roles=" + role + " ]";
	}
	
	
	
}
